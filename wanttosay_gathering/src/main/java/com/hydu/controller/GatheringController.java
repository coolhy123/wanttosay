package com.hydu.controller;

import com.hydu.entity.Gathering;
import com.hydu.service.GatheringService;
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
 * 2019/7/2
 */

@RestController(value = "/gathering")
public class GatheringController {
    @Autowired
    private GatheringService gatheringService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有活动
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",gatheringService.findAll());
    }

    /**
     * 通过id查询某个活动
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result queryOne(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功",gatheringService.queryOne(id));
    }

    /**
     * 新增一个活动
     * @param gathering
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Gathering gathering){
        gatheringService.save(gathering);
        return new Result(true, StatusCode.OK,"新增成功");
    }

    /**
     * 删除某个活动
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        gatheringService.detele(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     * 修改某个活动
     * @param gathering
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result updateGathering(@RequestBody Gathering gathering,@PathVariable String id){
        gathering.setId(id);
        gatheringService.updataGathering(gathering);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    /**
     * 模糊查询
     * @param serachMap
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result gatheringList(@RequestBody Map serachMap){
        return new Result(true, StatusCode.OK,"查询成功",gatheringService.gatheringList(serachMap));
    }

    /**
     * 模糊查询+分页
     * @param serachMap
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.GET)
    public Result pageList(@RequestBody Map serachMap,@PathVariable int page,@PathVariable int size){
        Page<Gathering> pageList=gatheringService.pageList(serachMap,page,size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Gathering>(pageList.getTotalElements(),pageList.getContent()));
    }



}
