package com.hydu.controller;

import com.hydu.entity.Recruit;
import com.hydu.service.RecruitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;
import java.util.List;

import java.util.Map;

/**
 * Created by heyong
 * 2019/6/24
 */

@RestController
@RequestMapping(value = "/recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;


    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有职位
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result queryAll(){
      return new Result(true, StatusCode.OK,"查询成功",  recruitService.queryAll());
    }



    @RequestMapping(value = "/search/recommend",method= RequestMethod.GET)
    public Result recommend(){
        List<Recruit> list = recruitService.findTop6ByStateOrderByCreatetime();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping(value = "/search/newlist",method= RequestMethod.GET)
    public Result newlist(){
        List<Recruit> list = recruitService.findTop6ByStateNotOrderByCreatetimeDesc();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }



    /**
     * 查询某个职位
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public Result queryOneById(@PathVariable String id){
            return new Result(true,StatusCode.OK,"查询成功",recruitService.queryOneByid(id));
        }

    /**
     * 修改某个职位
      * @param recruit
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result updateRecruit(@RequestBody Recruit recruit,@PathVariable String id){
            recruit.setId(id);
            recruitService.updateRecrit(recruit);
            return new Result(true,StatusCode.OK,"查询成功");
        }

    /**
     * 添加职位
     * @param recruit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result addRecruit (@RequestBody Recruit recruit){
            recruitService.saveRecruit(recruit);
            return new Result(true,StatusCode.OK,"添加成功");
        }

    /**
     * 分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{page}/size",method = RequestMethod.POST)
    public Result queryPageList(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Recruit> pageList = recruitService.queryPageRecruit(searchMap, page, size);
        return new Result(true,StatusCode.OK,"查询成功", new PageResult<Recruit> (pageList.getTotalElements(),pageList.getContent()));
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result queryList(@RequestBody Map searchMapp){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.findSearch(searchMapp));
    }
}
