package cn.eric.springbootdemo.controller;

import cn.eric.springbootdemo.controller.dto.R;
import cn.eric.springbootdemo.dao.BlogRepository;
import cn.eric.springbootdemo.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;


/**
 * @author Eric
 * @version 1.0
 * @ClassName: BlogController
 * @Description: TODO
 * @company lsj
 * @date 2019/6/27 9:47
 **/
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/search/title")
    public R searchTitle(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return R.error();
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery(keyword)).build();
        List<Blog> list = elasticsearchTemplate.queryForList(searchQuery, Blog.class);
        return R.ok(list);
    }

    @PostMapping("/addIndex/")
    public R addIndex(){
        return null;
    }

    @PostMapping("/add")
    public String add(@RequestBody Blog blog) {
        blogRepository.save(blog);
        return "success";
    }

    @GetMapping("/get/{id}")
    public String getById(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return "error";
        }
        Optional<Blog> blogModelOptional = blogRepository.findById(id);
        if (blogModelOptional.isPresent()) {
            Blog blogModel = blogModelOptional.get();
            return blogModel.toString();
        }
        return "success";
    }

    @GetMapping("/get")
    public R getAll() {
        Iterable<Blog> iterable = blogRepository.findAll();
        List<Blog> list = new ArrayList<>();
        iterable.forEach(list::add);
        return R.ok(list);
    }

    @PostMapping("/update")
    public R updateById(@RequestBody Blog blogModel) {
        String id = blogModel.getId();
        if (StringUtils.isEmpty(id)) {
            return R.error();
        }
        Blog blog = blogRepository.findById(id).get();
        blog.setTitle(blogModel.getTitle());
        blog.setTime(blogModel.getTime());
        blogRepository.save(blog);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deleteById(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return R.error();
        }
        blogRepository.deleteById(id);
        return R.ok();
    }

    @DeleteMapping("/delete")
    public R deleteById() {
        blogRepository.deleteAll();
        return R.ok();
    }

    @GetMapping("/rep/search/title")
    public R repSearchTitle(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return R.error();
        }
        return R.ok(blogRepository.findByTitleLike(keyword));
    }

    @GetMapping("/rep/exactSearch/title")
    public R repSearchTitleExact(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return R.error();
        }
        return R.ok(blogRepository.findByTitleCustom(keyword));
    }
}