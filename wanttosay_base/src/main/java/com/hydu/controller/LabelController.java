package com.hydu.controller;

import com.hydu.entity.Label;
import com.hydu.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 通过id删除某个标签
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId")String labelId){
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
     * @param labelId
     * @param label
     * @return
     */
    @RequestMapping(value = "{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId") String labelId,@RequestBody Label label){
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "/search" ,method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"查询成功");
    }

}
