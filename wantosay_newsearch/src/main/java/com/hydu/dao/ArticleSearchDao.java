package com.hydu.dao;

import com.hydu.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by heyong
 * 2019/7/18
 */


public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
}
