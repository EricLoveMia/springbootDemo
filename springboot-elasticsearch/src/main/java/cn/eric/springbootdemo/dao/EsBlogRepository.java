package cn.eric.springbootdemo.dao;

import cn.eric.springbootdemo.domain.Blog;
import cn.eric.springbootdemo.domain.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {

    List<EsBlog> findByTitleLike(String keyword);

    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    List<EsBlog> findByTitleCustom(String keyword);

    //下面是我们根据 spring data jpa 的命名规范额外创建的两个查询方法

    /**
     * 模糊查询(去重)，根据标题，简介，描述和标签查询（含有即可）Containing
     *
     * @param title
     * @param Summary
     * @param content
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(String title, String Summary, String content, String tags, Pageable pageable);

    /**
     * 根据 Blog 的id 查询 EsBlog
     *
     * @param blogId
     * @return
     */
    EsBlog findByBlogId(Long blogId);

}
