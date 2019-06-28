package com.hydu.controller;

import com.hydu.dao.ProblemDao;
import com.hydu.entity.Problem;
import com.hydu.service.ProblemService;
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
 * 2019/6/28
 */

@RestController(value = "/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        List<Problem> list=problemService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    /**
     * 查询某一个问题
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result queryOne(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findProblemById(id));
    }

    /**
     * 删除某个问题
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}",method =RequestMethod.DELETE )
    public Result deleteOne(@PathVariable String id){
        problemService.deleteProblem(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 修改某个问题
     * @param id
     * @param problem
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Result updateProblem(@PathVariable String id, @RequestBody Problem problem){
        problem.setId(id);
        problemService.updateProblem(problem);
        return new Result(true,StatusCode.OK,"修改成功");
    }
    /**
     * 新增问题
     *
     * @param problem
     * @return
     */
    public Result save(@RequestBody Problem problem){
         problemService.save(problem);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    /**
     * 分页查询+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageList(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Problem> pageList=problemService.problemPage(searchMap,page,size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     *条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result pageList(@RequestBody Map searchMap ){
        return new Result(true,StatusCode.OK,"查询成功",problemService.problemList(searchMap));
    }

    /**
     * 最新问题
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/newsList/{page}/{size}",method = RequestMethod.POST)
    public Result newsList(@PathVariable String labelid , @PathVariable int page, @PathVariable int size){
        Page<Problem> pageList =problemService.newsList(labelid,page,size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 最火问题
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/hostList/{page}/{size}",method = RequestMethod.POST)
    public Result hostList(@PathVariable String labelid , @PathVariable int page, @PathVariable int size){
        Page<Problem> pageList =problemService.hostList(labelid,page,size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 等待回答的问题
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/waitList/{page}/{size}",method = RequestMethod.POST)
    public Result waitList(@PathVariable String labelid , @PathVariable int page, @PathVariable int size){
        Page<Problem> pageList =problemService.newsList(labelid,page,size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
    }
}
