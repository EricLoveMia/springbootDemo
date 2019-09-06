package cn.eric.springbootdemo.controller;

import cn.eric.springbootdemo.controller.dto.R;
import cn.eric.springbootdemo.dao.EsBlogRepository;
import cn.eric.springbootdemo.domain.Blog;
import cn.eric.springbootdemo.domain.EsBlog;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: EsBlogController
 * @Description: TODO
 * @company lsj
 * @date 2019/7/2 16:56
 **/
@RestController
@RequestMapping("/esBlog")
public class EsBlogController {

    @Autowired
    private EsBlogRepository blogRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @PostMapping("/add")
    public R add(@RequestBody EsBlog blog) {
        blogRepository.save(blog);
        return R.ok();
    }

    @RequestMapping("/search")
    public R elasticSerchTest(String title,@RequestParam(required = false) String summary
            ,@RequestParam(required = false) Long blogId,@RequestParam(required = false) String contentStr) {

        Map<String,Object> resultMap = new HashMap<>();
        //1.创建QueryBuilder(即设置查询条件)这儿创建的是组合查询(也叫多条件查询),后面会介绍更多的查询方法
        /*组合查询BoolQueryBuilder
         * must(QueryBuilders)   :AND
         * mustNot(QueryBuilders):NOT
         * should:               :OR
         */
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //builder下有must、should以及mustNot 相当于sql中的and、or以及not

        //设置模糊搜索,博客的简诉中有学习两个字
        //builder.must(QueryBuilders.fuzzyQuery("summary", summary));
        // matchQuery 分词查询，采用默认的分词器  termQuery 不分词从查询，但是只能查一个汉字 或者一个英语单词
//        if(!StringUtils.isEmpty(summary)) {
//            builder.must(QueryBuilders.matchQuery("summary", summary));
//        }

        // .queryStringQuery("fieldValue").field("fieldName");//左右模糊
        if(!StringUtils.isEmpty(summary)) {
            builder.must(QueryBuilders.queryStringQuery(summary).field("summary"));
        }

        //设置要查询博客的标题中含有关键字
        //builder.must(new QueryStringQueryBuilder("title").field(title));
//        if(!StringUtils.isEmpty(title)) {
//            builder.must(QueryBuilders.matchQuery("title", title));
//        }

        if(!StringUtils.isEmpty(title)) {
            // 前缀查询
            builder.must(QueryBuilders.prefixQuery("title", title));
        }

        //  fuzzyQuery("hotelName", "tel").fuzziness(Fuzziness.ONE); 分词模糊查询 fuzziness 的含义是检索的term 前后增加或减少n个单词的匹配查询
        if(!StringUtils.isEmpty(contentStr)){
            builder.must(QueryBuilders.fuzzyQuery("content", contentStr).fuzziness(Fuzziness.ONE));
        }

        if(blogId != null){
            builder.must(QueryBuilders.termQuery("blogId",blogId));
        }
        //按照博客的评论数的排序是依次降低
        FieldSortBuilder sort = SortBuilders.fieldSort("commentSize").order(SortOrder.DESC);

        //设置分页(从第一页开始，一页显示10条)
        //注意开始是从0开始，有点类似sql中的方法limit 的查询
        PageRequest page = PageRequest.of(0, 10);

        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //3.执行方法1
        Page<EsBlog> pageList = blogRepository.search(query);

        //4.获取总条数(用于前端分页)
        int total = (int) pageList.getTotalElements();
        resultMap.put("total",total);
        //5.获取查询到的数据内容（返回给前端）
        List<EsBlog> content = pageList.getContent();
        resultMap.put("data",content);

        return R.ok(resultMap);
    }


    @RequestMapping("/search2")
    public R elasticSerch2(String title,String summary) {

        Map<String,Object> resultMap = new HashMap<>();
        //1.创建QueryBuilder(即设置查询条件)这儿创建的是组合查询(也叫多条件查询),后面会介绍更多的查询方法
        /*组合查询BoolQueryBuilder
         * must(QueryBuilders)   :AND
         * mustNot(QueryBuilders):NOT
         * should:               :OR
         */
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //builder下有must、should以及mustNot 相当于sql中的and、or以及not

        //设置模糊搜索,博客的简述中有学习两个字
        builder.must(QueryBuilders.fuzzyQuery("summary", summary));

        //设置要查询博客的标题中含有关键字
        builder.must(new QueryStringQueryBuilder("title").field(title));

        //按照博客的评论数的排序是依次降低
        FieldSortBuilder sort = SortBuilders.fieldSort("commentSize").order(SortOrder.DESC);

        //设置分页(从第一页开始，一页显示10条)
        //注意开始是从0开始，有点类似sql中的方法limit 的查询
        PageRequest page = PageRequest.of(0, 10);

        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //3.执行方法1
        //Page<EsBlog> pageList = blogRepository.search(query);
        //执行方法2：注意，这儿执行的时候还有个方法那就是使用elasticsearchTemplate
        //执行方法2的时候需要加上注解
        List<EsBlog> blogList = elasticsearchTemplate.queryForList(query, EsBlog.class);

        //4.获取总条数(用于前端分页)
        //int total = (int) pageList.getTotalElements();
        //resultMap.put("total",total);
        //5.获取查询到的数据内容（返回给前端）
        //List<EsBlog> content = pageList.getContent();
        resultMap.put("data",blogList);

        return R.ok(resultMap);
    }
}
