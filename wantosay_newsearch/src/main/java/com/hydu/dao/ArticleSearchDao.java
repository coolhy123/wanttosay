package com.hydu.dao;

import com.hydu.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.*;
/**
 * Created by heyong
 * 2019/7/18
 */


public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {

    /**
     * 检索
     * @param tiltle
     * @param content
     * @param pageable
     * @return
     */
    public Page<Article> findByTitleOrContentLike(String tiltle, String content, Pageable pageable);



}
