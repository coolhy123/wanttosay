package com.hydu.controller;

import com.hydu.entity.Comment;
import com.hydu.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by heyong
 * 2019/7/4
 */

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 新增一条评论
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment){
        commentService.save(comment);
        return  new Result(true, StatusCode.OK,"提交成功");
    }

    /**
     * 通过文章id查找所有评论
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/article/{articleId}",method = RequestMethod.GET)
    public Result findByArticleid(@PathVariable String articleId){
        return  new Result(true,StatusCode.OK,"查询成功",commentService.findByArticleid(articleId));
    }

    /**
     * 删除某一评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        commentService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
