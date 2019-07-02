package com.hydu.controller;

import com.hydu.entity.Article;
import com.hydu.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Map;

/**
 * Created by heyong
 * 2019/7/1
 */

@RestController(value = "article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有的文章
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",articleService.findAll());
    }

    /**
     * 通过id查询某一篇文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result queryOne(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功",articleService.queryOne(id));
    }

    /**
     * 删除某一篇文章
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        articleService.deleteOne(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     * 修改该某一篇文章
     * @param article
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result updateOne(@RequestBody Article article,@PathVariable String id){
        article.setId(id);
        articleService.updateOne(article);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 新增一篇文章
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    /**
     * 分页查询+条件查询
     * @param serachMap
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageList(@RequestBody Map serachMap,@PathVariable int page ,@PathVariable int size){
        Page<Article> pageList= articleService.pageList(serachMap,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Article>(pageList.getTotalElements(),pageList.getContent()));
    }

    /**
     * 模糊查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result articleList(@RequestBody Map searchMap){
        articleService.articleList(searchMap);
        return new Result(true,StatusCode.OK,"查询成功");
    }

    /**
     * 修改审核状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateState/{id}",method = RequestMethod.POST)
    public Result updateState(@PathVariable String id){
        articleService.updateState(id);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 修改点赞数
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateThumbup/{id}",method = RequestMethod.POST)
    public Result updateThumbup(@PathVariable String id){
        articleService.updateThumbup(id);
        return new Result(true,StatusCode.OK,"修改成功");
    }
}
