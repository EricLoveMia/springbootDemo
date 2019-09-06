package cn.eric.springbootdemo.dao;

import cn.eric.springbootdemo.domain.Blog;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: BlogRepository
 * @Description: TODO
 * @company lsj
 * @date 2019/6/27 9:47
 **/
public interface BlogRepository extends ElasticsearchRepository<Blog, String> {

    List<Blog> findByTitleLike(String keyword);

    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    List<Blog> findByTitleCustom(String keyword);
}
