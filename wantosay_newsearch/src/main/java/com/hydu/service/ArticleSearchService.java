package com.hydu.service;

import com.hydu.dao.ArticleSearchDao;
import com.hydu.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by heyong
 * 2019/7/18
 */

@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao articleSearchDao;
    /**
     * 增加文章
     * @param article
     */
    public void save(Article article){
        articleSearchDao.save(article);
    }
}
