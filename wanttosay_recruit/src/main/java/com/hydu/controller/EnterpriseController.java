package com.hydu.controller;

import com.hydu.entity.Enterprise;
import com.hydu.service.EnterpriseService;
import com.sun.org.apache.regexp.internal.RE;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by heyong
 * 2019/6/13
 */

@RestController
@RequestMapping(value = "/enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 热门企业
     * @return
     */
    @RequestMapping(value = "/search/hotlist",method = RequestMethod.GET)
    public Result hotlist(){
        List<Enterprise> hotlist=enterpriseService.hotEnterpriseList();
        return new Result(true, StatusCode.OK,"查询成功",hotlist);
    }

    /**
     * 查询所有企业
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result queryAll(){
        List<Enterprise> queryAlllist= enterpriseService.queryAll();
        return new Result(true,StatusCode.OK,"查询成功",queryAlllist);
    }

    /**
     * 增加企业
     * @param enterprise
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result addEnterprise(@RequestBody Enterprise enterprise){
        enterpriseService.addEnterprise(enterprise);
        return new Result(true,StatusCode.OK,"添加成功");

    }

    /**
     * 修改企业
     * @param enterprise
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Result updateEnterprise(@RequestBody Enterprise enterprise,@PathVariable String id ){
        enterprise.setId(id);
        enterpriseService.updateEnterprise(enterprise);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.queryOne(id));
    }

    /**
     * 删除热门企业
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteOne(@PathVariable String id ){
        enterpriseService.deleteById(id);
        return  new Result(true,StatusCode.OK,"删除成功" );
    }

    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Enterprise> pageList = enterpriseService.queryPageEnterprise(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Enterprise>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findSearch(searchMap));
    }



}
