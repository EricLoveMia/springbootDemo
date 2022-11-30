package cn.eric.springbootdemo.controller;

import com.loveprogrammer.springboot.elasticsearch.dao.PlayersRepository;
import com.loveprogrammer.springboot.elasticsearch.domain.Players;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.*;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: PlayersTest
 * @Description: TODO
 * @company lsj
 * @date 2019/7/8 15:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayersTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private PlayersRepository playersRepository;

    @Test
    public void deleteAll() throws Exception {
        playersRepository.deleteAll();
    }

    @Test
    public void testAdd() throws Exception {

        Players players1 = new Players("james", 33, 3000, "cav", "sf");
        Players players2 = new Players("irving", 25, 2000, "cav", "pg");
        Players players3 = new Players("curry", 29, 1000, "war", "pg");
        Players players10 = new Players("dulant", 29, 3000, "war", "sf");
        Players players4 = new Players("thompson", 26, 2000, "war", "sg");
        Players players5 = new Players("green", 26, 2000, "war", "pf");
        Players players6 = new Players("garnett", 40, 1000, "tim", "pf");
        Players players7 = new Players("towns", 21, 500, "tim", "c");
        Players players8 = new Players("lavin", 21, 300, "tim", "sg");
        Players players9 = new Players("wigins", 20, 500, "tim", "sf");

        playersRepository.save(players1);
        playersRepository.save(players2);
        playersRepository.save(players3);
        playersRepository.save(players4);
        playersRepository.save(players5);
        playersRepository.save(players6);
        playersRepository.save(players7);
        playersRepository.save(players8);
        playersRepository.save(players9);
        playersRepository.save(players10);
    }

    /**
     * 例如要计算每个球队的球员数，如果使用SQL语句，应表达如下：
     * select team, count(*) as player_count from player group by team;
     * ES的java api：
     * TermsBuilder teamAgg= AggregationBuilders.terms("player_count ").field("team");
     **/
    @Test
    public void countGroupBy() throws Exception {

        // 查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 创建查询条件 设置查询所有
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        queryBuilder.withQuery(matchAllQuery);
        queryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
        queryBuilder.withIndices("players").withTypes("players");
        /** */
        queryBuilder.addAggregation(AggregationBuilders.terms("player_count").field("team"));
        AggregatedPage<Players> players = elasticsearchTemplate.queryForPage(queryBuilder.build(), Players.class);

        StringTerms player_count = players.getAggregations().get("player_count");

        List<StringTerms.Bucket> buckets = player_count.getBuckets();
        for (StringTerms.Bucket bucket : buckets) {
            System.out.println(bucket.getKeyAsString() + ":" + bucket.getDocCount());
            //System.out.println(bucket.getDocCount());
        }
    }

    /**
     * 计算每个球队每个位置的球员数，如果使用SQL语句，应表达如下：
     * select team, position, count(*) as pos_count from player group by team, position;
     * ES的java api：
     * TermsBuilder teamAgg= AggregationBuilders.terms("player_count ").field("team");
     * TermsBuilder posAgg= AggregationBuilders.terms("pos_count").field("position");
     * sbuilder.addAggregation(teamAgg.subAggregation(posAgg));
     **/
    @Test
    public void countGroupBy2() throws Exception {
        // 查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 创建查询条件 设置查询所有
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        queryBuilder.withQuery(matchAllQuery);
        queryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
        queryBuilder.withIndices("players").withTypes("players");
        /** */
        TermsAggregationBuilder teamAgg = AggregationBuilders.terms("player_count").field("team");
        TermsAggregationBuilder posAgg = AggregationBuilders.terms("pos_count").field("position");
        queryBuilder.addAggregation(teamAgg.subAggregation(posAgg));
        AggregatedPage<Players> players = elasticsearchTemplate.queryForPage(queryBuilder.build(), Players.class);

        StringTerms player_count = players.getAggregations().get("player_count");

        List<StringTerms.Bucket> buckets = player_count.getBuckets();

        for (StringTerms.Bucket bucket : buckets) {
            StringTerms pos_count = bucket.getAggregations().get("pos_count");
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());

            List<StringTerms.Bucket> posBuckets = pos_count.getBuckets();
            for (StringTerms.Bucket pBucket : posBuckets) {
                System.out.println(pBucket.getKeyAsString());
                System.out.println(pBucket.getDocCount());
            }
        }
    }


    /**
     * max/min/sum/avg
     * 例如要计算每个球队年龄最大/最小/总/平均的球员年龄，如果使用SQL语句，应表达如下：
     * select team, max(age) as max_age from player group by team;
     * ES的java api：
     * TermsBuilder teamAgg= AggregationBuilders.terms("player_count ").field("team");
     * MaxBuilder ageAgg= AggregationBuilders.max("max_age").field("age");
     * sbuilder.addAggregation(teamAgg.subAggregation(ageAgg));
     **/
    @Test
    public void sumGroupBy() throws Exception {
        // 查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 创建查询条件 设置查询所有
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        queryBuilder.withQuery(matchAllQuery);
        queryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
        queryBuilder.withIndices("players").withTypes("players");
        /** */
        TermsAggregationBuilder teamAgg = AggregationBuilders.terms("player_count").field("team");
        MaxAggregationBuilder aggAgg = AggregationBuilders.max("age_max").field("age");
        queryBuilder.addAggregation(teamAgg.subAggregation(aggAgg));
        AggregatedPage<Players> players = elasticsearchTemplate.queryForPage(queryBuilder.build(), Players.class);

        StringTerms player_count = players.getAggregations().get("player_count");

        List<StringTerms.Bucket> buckets = player_count.getBuckets();

        for (StringTerms.Bucket bucket : buckets) {
            InternalMax age_max = bucket.getAggregations().get("age_max");
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
            System.out.println(age_max.getValue());
        }
    }


    /**
     * 对多个field求max/min/sum/avg
     * 例如要计算每个球队球员的平均年龄，同时又要计算总年薪，如果使用SQL语句，应表达如下：
     * select team, avg(age)as avg_age, sum(salary) as total_salary from player group by team;
     * <p>
     * ES的java api：
     * TermsBuilder teamAgg= AggregationBuilders.terms("team");
     * AvgBuilder ageAgg= AggregationBuilders.avg("avg_age").field("age");
     * SumBuilder salaryAgg= AggregationBuilders.avg("total_salary ").field("salary");
     * sbuilder.addAggregation(teamAgg.subAggregation(ageAgg).subAggregation(salaryAgg));
     * SearchResponse response = sbuilder.execute().actionGet();
     **/
    @Test
    public void maxSumGroupBy() throws Exception {
        // 查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 创建查询条件 设置查询所有
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        queryBuilder.withQuery(matchAllQuery);
        queryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
        queryBuilder.withIndices("players").withTypes("players");
        /** */
        TermsAggregationBuilder teamAgg = AggregationBuilders.terms("player_count").field("team");
        MaxAggregationBuilder aggAgg = AggregationBuilders.max("age_max").field("age");
        SumAggregationBuilder salaryAgg = AggregationBuilders.sum("total_salary").field("salary");
        queryBuilder.addAggregation(teamAgg.subAggregation(aggAgg).subAggregation(salaryAgg));
        AggregatedPage<Players> players = elasticsearchTemplate.queryForPage(queryBuilder.build(), Players.class);

        StringTerms player_count = players.getAggregations().get("player_count");

        List<StringTerms.Bucket> buckets = player_count.getBuckets();

        for (StringTerms.Bucket bucket : buckets) {
            InternalSum total_salary = bucket.getAggregations().get("total_salary");
            InternalMax age_max = bucket.getAggregations().get("age_max");

            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
            System.out.println("年龄最大：" + age_max.getValue());
            System.out.println("薪资总额：" + total_salary.getValue());
        }
    }

    /**
     * 聚合后对Aggregation结果排序
     * 例如要计算每个球队总年薪，并按照总年薪倒序排列，如果使用SQL语句，应表达如下：
     * select team, sum(salary) as total_salary from player group by team order by total_salary desc;
     * ES的java api：
     * <p>
     * TermsBuilder teamAgg= AggregationBuilders.terms("team").order(Order.aggregation("total_salary ", false);
     * SumBuilder salaryAgg= AggregationBuilders.avg("total_salary ").field("salary");
     * sbuilder.addAggregation(teamAgg.subAggregation(salaryAgg));
     * <p>
     * 需要特别注意的是，排序是在TermAggregation处执行的，Order.aggregation函数的第一个参数是aggregation的名字，第二个参数是boolean型，true表示正序，false表示倒序。
     * <p>
     * Aggregation结果条数的问题
     * 默认情况下，search执行后，仅返回10条聚合结果，如果想反悔更多的结果，需要在构建TermsBuilder 时指定size：
     * <p>
     * TermsBuilder teamAgg= AggregationBuilders.terms("team").size(15);
     **/
    @Test
    public void sumOrderGroupBy() throws Exception {
        // 查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 创建查询条件 设置查询所有
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        queryBuilder.withQuery(matchAllQuery);
        queryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
        queryBuilder.withIndices("players").withTypes("players");
        /** */
        TermsAggregationBuilder teamAgg = AggregationBuilders.terms("player_count").field("team").order(BucketOrder.aggregation("total_salary", true));
        // MaxAggregationBuilder aggAgg = AggregationBuilders.max("age_max").field("age");
        SumAggregationBuilder salaryAgg = AggregationBuilders.sum("total_salary").field("salary");
        queryBuilder.addAggregation(teamAgg.subAggregation(salaryAgg));
        AggregatedPage<Players> players = elasticsearchTemplate.queryForPage(queryBuilder.build(), Players.class);

        StringTerms player_count = players.getAggregations().get("player_count");

        List<StringTerms.Bucket> buckets = player_count.getBuckets();

        for (StringTerms.Bucket bucket : buckets) {
            InternalSum total_salary = bucket.getAggregations().get("total_salary");
            //InternalMax age_max = bucket.getAggregations().get("age_max");

            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
            //System.out.println("年龄最大：" + age_max.getValue());
            System.out.println("薪资总额：" + total_salary.getValue());
        }

    }
}
