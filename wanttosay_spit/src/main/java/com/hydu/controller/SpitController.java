package com.hydu.controller;

import com.hydu.entity.Spit;
import com.hydu.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Map;

/**
 * Created by heyong
 * 2019/7/2
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    @Autowired
    private IdWorker idWorker;


    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *
     * @return
     */
    @RequestMapping("/spit")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/spit")
    public Result queryOne(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功",spitService.queryOne(id));
    }

    /**
     *
     * @param spit
     * @return
     */
    @RequestMapping("/spit")
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"新增成功");
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/spit")
    public Result delete(@PathVariable String id){
        spitService.delete(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     *
     * @param spit
     * @param id
     * @return
     */
    public Result updataSpit(@RequestBody Spit spit,@PathVariable String id){
        spit.set_id(id);
        spitService.updateSpit(spit);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    /**
     *
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Result findByParentid(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit>  pageList = spitService.findByParentid(parentid,page,size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Spit>(pageList.getTotalElements(),pageList.getContent()));
    }
}
