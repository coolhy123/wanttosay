package com.hydu.controller;

import com.hydu.entity.Spit;
import com.hydu.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *查询所有吐槽
     * @return
     */
    @RequestMapping(value = "/spit",method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    /**
     *查询某一条吐槽记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/spit/{id}",method = RequestMethod.GET)
    public Result queryOne(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功",spitService.queryOne(id));
    }

    /**
     *新增一条吐槽记录
     * @param spit
     * @return
     */
    @RequestMapping(value = "/spit",method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"新增成功");
    }

    /**
     *删除一条吐槽记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/spit",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        spitService.delete(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     *修改一条吐槽记录
     * @param spit
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT )
    public Result updataSpit(@RequestBody Spit spit,@PathVariable String id){
        spit.set_id(id);
        spitService.updateSpit(spit);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    /**
     *根据上级ID查询吐槽列表
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value="/comment/{parentid}/{page}/{size}",method=RequestMethod.PUT )
    public Result findByParentid(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit>  pageList = spitService.findByParentid(parentid,page,size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Spit>(pageList.getTotalElements(),pageList.getContent()));
    }

    /**
     * 控制重复点赞
     * @param id
     * @return
     */
    @RequestMapping(value="/thumbup/{id}",method=RequestMethod.PUT )
     public Result updateThumbup(@PathVariable String id){
        String userid="2023";

            if(redisTemplate.opsForValue().get("thumbup_"+userid+"_"+id)!=null){
                return new Result(true,StatusCode.OK,"你已经点过赞了");
            }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("thumbup_"+userid+"_"+id,"1");
        return new Result(true,StatusCode.OK,"点赞成功");
    }




}
