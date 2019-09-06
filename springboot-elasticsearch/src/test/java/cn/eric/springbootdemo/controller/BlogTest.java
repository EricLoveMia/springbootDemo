package cn.eric.springbootdemo.controller;

import cn.eric.springbootdemo.dao.EsBlogRepository;
import cn.eric.springbootdemo.domain.EsBlog;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: BlogTest
 * @Description: TODO
 * @company lsj
 * @date 2019/7/2 17:45
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private EsBlogRepository blogRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        //构造MockMvc
    }


    @Test
    public void testBlogAdd() throws Exception {

        RequestBuilder request;

        EsBlog param = new EsBlog();
        param.setBlogId(9L);
        param.setCommentSize(95L);
        param.setContent("elasticsearch,学习");
        param.setTitle("elasticsearch学习");
        param.setTags("elasticsearch,学习");
        param.setSummary("elasticsearch,学习");
        String paraJson = JSONObject.toJSONString(param);

        // get 获取所有用户列表
        request = post("/esBlog/add").contentType(MediaType.APPLICATION_JSON).content(paraJson).accept(MediaType.APPLICATION_JSON);
        //mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("操作成功")));
        mvc.perform(request).andDo(print()).andReturn();
    }

    @Test
    public void testBlogSearch() throws Exception {

        RequestBuilder request;

        // get 获取所有用户列表
        request = get("/esBlog/search").contentType(MediaType.APPLICATION_JSON)
                //.param("title","spring")
                //.param("summary","spring")
                .param("contentStr","spring")
                .accept(MediaType.APPLICATION_JSON);
        //mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("操作成功")));
        mvc.perform(request).andDo(print()).andReturn();
    }

    @Test
    public void testAggregation() throws Exception {
        // 搜索写博客写的最多得用户

        List<String> userNameList = new ArrayList<>();

        // 创建查询条件 设置查询所有
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();

        // 构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        // 设置queryBuilder
        nativeSearchQueryBuilder.withQuery(matchAllQuery);

        // 设置搜索类型 默认QUERY_THEN_FETCH，参考https://blog.csdn.net/wulex/article/details/7108104
        // 指定索引的类型，只先从各分片中查询匹配的文档，再重新排序和排名，取前size个文档
        nativeSearchQueryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);

        // 指定索引库和文档类型
        // 指定要查询的索引库的名称和类型，其实就是我们文档@Document中设置的indedName和type
        nativeSearchQueryBuilder.withIndices("esblog").withTypes("esblog");

        // 指定聚合函数,本例中以某个字段分组聚合为例（可根据你自己的聚合查询需求设置）
        // 该聚合函数解释：计算该字段(假设为username)在所有文档中的出现频次，并按照降序排名（常用于某个字段的热度排名）
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("myjuhe").field("blogId"));

        AggregatedPage<EsBlog> esBlogs = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), EsBlog.class);
        //
        Aggregations aggregations = esBlogs.getAggregations();

        // 获取指定名称得聚合
        //Aggregations agg = aggregations.get("popular_brand");
        LongTerms agg = aggregations.get("myjuhe");

        List<LongTerms.Bucket> buckets = agg.getBuckets();

        for (LongTerms.Bucket bucket : buckets) {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
        }

    }

}
