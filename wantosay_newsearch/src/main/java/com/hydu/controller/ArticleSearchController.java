package com.hydu.controller;

import com.hydu.entity.Article;
import com.hydu.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by heyong
 * 2019/7/18
 */

@RestController(value = "articleSearch")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    @RequestMapping(method= RequestMethod.POST)
    public Result save(@RequestBody Article article) {
        articleSearchService.save(article);
        return new Result(true,StatusCode.OK, "操作成功");
    }

    public Result findByTitleLike(@PathVariable String keywords, @PathVariable int page, @PathVariable int size){
        Page<Article> pageList=articleSearchService.findByTitleLike(keywords,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Article>(pageList.getTotalElements(),pageList.getContent()));
    }
}
