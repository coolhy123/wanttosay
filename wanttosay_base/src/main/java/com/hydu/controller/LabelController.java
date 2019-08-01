package com.hydu.controller;

import com.hydu.entity.Label;
import com.hydu.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询所有标签
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
       List<Label> list= labelService.selectAll();

        return new Result(true,StatusCode.OK,"查询成功",list);
    }
    @RequestMapping(value = "/getOne/{id}",method = RequestMethod.GET)
    public  Result selectOne(@PathVariable("id") String id){
        System.out.println("负载均衡");
        return new Result(true,StatusCode.OK,"查询成功",labelService.getOne(id));
    }
    /**
     * 通过id删除某个标签
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId){
        labelService.deleteOne(labelId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 添加标签
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.insertOne(label);
        return new Result(true,StatusCode.OK,"添加成功");

    }

    /**
     * 修改某个标签
     * @param id
     * @param label
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable String id,@RequestBody Label label){
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "/search" ,method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"查询成功");
    }


    @RequestMapping(value = "/search/{page}/{size}" , method = RequestMethod.POST)
    public Result pageQuery(@RequestBody Label label ,@PathVariable int page, @PathVariable int size){
        Page<Label> pageDate =labelService.pageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(pageDate.getTotalElements(),pageDate.getContent()));
    }
}
