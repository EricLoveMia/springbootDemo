package cn.eric.springbootdemo.controller;

import cn.eric.springbootdemo.controller.dto.R;
import cn.eric.springbootdemo.dao.PhoneModelRepository;
import cn.eric.springbootdemo.domain.PhoneModel;
import cn.eric.springbootdemo.util.DateUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: PhoneController
 * @Description: TODO
 * @company lsj
 * @date 2019/7/2 13:42
 **/
@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Resource
    private PhoneModelRepository phoneModelRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    /**
     * 全文搜索
     *
     * @param keyword 关键字
     * @param page    当前页，从0开始
     * @param size    每页大小
     * @return {@link R} 接收到的数据格式为json
     */
    @GetMapping("/full")
    public Mono<R> full(String keyword, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        // System.out.println(new Date() + " => " + keyword);

        // 校验参数
        if (StringUtils.isEmpty(page)) {
            page = 0; // if page is null, page = 0
        }
        if (StringUtils.isEmpty(size)) {
            size = 10; // if size is null, size default 10
        }
        // 构造分页类
        Pageable pageable = PageRequest.of(page, size);

        // 构造查询 NativeSearchQueryBuilder
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable);
        if (!StringUtils.isEmpty(keyword)) {
            // keyword must not null
            searchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(keyword));
        }

    /*
    SearchQuery
    这个很关键，这是搜索条件的入口，
    elasticsearchTemplate 会 使用它 进行搜索
     */
        SearchQuery searchQuery = searchQueryBuilder.build();

        // page search
        Page<PhoneModel> phoneModelPage = elasticsearchTemplate.queryForPage(searchQuery, PhoneModel.class);

        // return
        return Mono.just(R.ok(phoneModelPage));
    }


    /**
     * 高级搜索，根据字段进行搜索
     *
     * @param name         名称
     * @param color        颜色
     * @param sellingPoint 卖点
     * @param price        价格
     * @param start        开始时间(格式：yyyy-MM-dd HH:mm:ss)
     * @param end          结束时间(格式：yyyy-MM-dd HH:mm:ss)
     * @param page         当前页，从0开始
     * @param size         每页大小
     * @return {@link R}
     */
    @GetMapping("/_search")
    public Mono<R> search(String name, @RequestParam(required = false) String color, @RequestParam(required = false) String sellingPoint, @RequestParam(required = false) String price, @RequestParam(required = false) String start, @RequestParam(required = false) String end, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {

        // 校验参数
        if (StringUtils.isEmpty(page) || page < 0) {
            page = 0; // if page is null, page = 0
        }

        if (StringUtils.isEmpty(size) || size < 0) {
            size = 10; // if size is null, size default 10
        }
        // 构造分页对象
        Pageable pageable = PageRequest.of(page, size);

        // BoolQueryBuilder (Elasticsearch Query)
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (!StringUtils.isEmpty(name)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("name", name));
        }

        if (!StringUtils.isEmpty(color)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("colors", color));
        }

        if (!StringUtils.isEmpty(color)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("sellingPoints", sellingPoint));
        }

        if (!StringUtils.isEmpty(price)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("price", price));
        }

        if (!StringUtils.isEmpty(start)) {
            Date startTime = DateUtil.parse(start, DateUtil.PATTERN_YYYY_MM_DD_HHMMSS);
            boolQueryBuilder.must(QueryBuilders.rangeQuery("createTime").gt(startTime.getTime()));
        }

        if (!StringUtils.isEmpty(end)) {
            Date endTime = DateUtil.parse(end, DateUtil.PATTERN_YYYY_MM_DD_HHMMSS);
            boolQueryBuilder.must(QueryBuilders.rangeQuery("createTime").lt(endTime.getTime()));
        }

        // BoolQueryBuilder (Spring Query)
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(boolQueryBuilder).build();

        // page search
        Page<PhoneModel> phoneModelPage = elasticsearchTemplate.queryForPage(searchQuery, PhoneModel.class);

        // return
        return Mono.just(R.ok(phoneModelPage));
    }

    @DeleteMapping("/delete")
    public R deleteById() {
        phoneModelRepository.deleteAll();
        return R.ok();
    }


}
