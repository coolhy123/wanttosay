package com.hydu.controller;

import com.hydu.entity.Reply;
import com.hydu.service.ReplyService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Map;

@RestController(value = "/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有回答
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",replyService.findAll());
    }

    /**
     * 通过id查询某个回答
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findOne(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",replyService.findOne(id));
    }

    /**
     * 新增回答
     * @param reply
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody  Reply reply){
        replyService.save(reply);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    /**
     * 删除回答
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteOne(@PathVariable  String  id){
        replyService.deleteOne(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 修改回答
     * @param reply
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result updateReply(@RequestBody Reply reply,@PathVariable String id){
        reply.setId(id);
        replyService.updateReply(reply);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    public Result replyList(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功", replyService.replyList(searchMap));
    }

    /**
     * 条件查询+分页
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Result pageList(@RequestBody Map searchMap,@PathVariable  int page,@PathVariable int size){
        Page<Reply> pageList=replyService.pageList(searchMap,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Reply>(pageList.getTotalElements(),pageList.getContent()));
    }
}
