package com.hydu.controller;

import com.hydu.entity.Admin;
import com.hydu.service.AdminService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by heyong
 * 2019/7/30
 */

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody Admin admin) {
        Admin adminlogin = adminService.login(admin);
        if (adminlogin == null) {
            return new Result(false, StatusCode.LOGINERROR, "登录失败");
        }
        //生成token
            String token = jwtUtil.createJwt(adminlogin.getId(), adminlogin.getLoginname(), "admin");
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("roles", "admin");
            map.put("name",adminlogin.getLoginname());
            return new Result(true,StatusCode.OK,"登录成功",map);
    }


    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Admin> adminList=adminService.findAll();
        return new Result(true,StatusCode.OK,"查询成功",adminList);
    }

    @RequestMapping(value = "/serach/{page}/{size}",method = RequestMethod.POST)
    public Result pageList(@RequestBody Map whereMap ,@PathVariable int page,@PathVariable int size){
        Page<Admin> adminPage=adminService.pageSearch(whereMap,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Admin>(adminPage.getTotalElements(),adminPage.getContent()));
    }

    @RequestMapping(value = "/serach",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map whereMap){
        List<Admin> adminList=adminService.findSearch(whereMap);
        return new Result(true,StatusCode.OK,"查询成功",adminList);
    }

    @RequestMapping(value = "/serach/{id}",method = RequestMethod.GET)
    public Result  findById(@PathVariable String id){
        Admin admin=adminService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",admin);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody Admin admin){
        adminService.add(admin);
        return new Result(true,StatusCode.OK,"新增成功");
    }
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result update(@RequestBody Admin admin){
        adminService.update(admin);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        adminService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
