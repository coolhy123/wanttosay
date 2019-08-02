package com.hydu.controller;

import com.hydu.entity.User;
import com.hydu.service.UserService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heyong
 * 2019/7/30
 */


@RestController
@RequestMapping(value = "/user")
public class UserCotroller {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 查询所有的用户
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功", userService.findAll());
    }

    /**
     * 分页查询
     * @param webserach
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value="/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageList(@RequestBody Map webserach, @PathVariable int page, @PathVariable int size){
        Page<User> userPage=userService.pageList(webserach,page,size);
        return new Result(true,StatusCode.OK,"{查询成功",new PageResult<User>(userPage.getTotalElements(),userPage.getContent()));

    }

    @RequestMapping(value = "/incfans/{userid}/{x}",method = RequestMethod.POST)
    public void incFanscount(@PathVariable String userid,@PathVariable int x){
        userService.incFanscount(userid,x);
    }

    @RequestMapping(value = "/incfollow/{userid}/{x}",method = RequestMethod.POST)
    public void incFollowcount(@PathVariable String userid,@PathVariable int x){
        userService.incFollowcount(userid,x);
    }

    /**
     * 条件查询用户
     * @param webserach
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map webserach){
        List<User> userList=userService.findSearch(webserach);
        return new Result(true,StatusCode.OK,"查询成功",userList);
    }

    /**
     * 通过id查询某一个用户
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}",method = RequestMethod.POST)
    public Result selectOne(@PathVariable String id){
        User user=userService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",user);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public Result update(@RequestBody User user){
        userService.update(user);
        return new Result(true,StatusCode.OK,"更新成功");

    }

    /**
     * 删除用户（只有admin才有权限删除）
     * @param id
     * @return
     */
    @DeleteMapping(value="/delete/{id}")
    public Result delete(@PathVariable String id){
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");

    }

    /**
     * 验证码发送
     * @param mobile
     * @return
     */
    @RequestMapping(value="/sendSms/{mobile}",method = RequestMethod.POST)
    public Result sendSms(@PathVariable("mobile") String mobile){
        userService.sendSms(mobile);
        return new Result(true,StatusCode.OK,"短信发送成功");
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        user=userService.login(user.getMobile(),user.getPassword());
        if(user==null){
            return new Result(false,StatusCode.LOGINERROR,"登录失败");
        }
        String token = jwtUtil.createJwt(user.getId(),user.getNickname(),"user");
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("name",user.getNickname());
        map.put("role","user");

        return new Result(true,StatusCode.OK,"登录成功",map);
    }

    /**
     * z注册用户
     * @param user
     * @param code
     * @return
     */
    @RequestMapping(value="/regist/{code}",method = RequestMethod.POST)
    public Result  regist(@RequestBody User user,@PathVariable String code){
//            String checkCode = (String)redisTemplate.opsForValue().get("checkcode_"+user.getMobile());
//            if(checkCode.isEmpty()){
//                return new Result(false,StatusCode.ACCESSERROR,"请先获取验证码");
//            }
//            if (checkCode.equals(code)){
//                return new Result(false,StatusCode.ERROR,"请输入正确的验证码");
//            }
            userService.add(user);
            return new Result(true,StatusCode.OK,"注册成功");
    }

    /**
     * 更新粉丝数
     * @param userid
     * @param friendid
     * @param x
     * @return
     */
    @RequestMapping(value="/updatefanscountandfollowcount/{userid}",method = RequestMethod.PUT)
    public Result updatefanscountandfollowcount(@PathVariable String userid, @PathVariable String friendid, @PathVariable int x){
        userService.updatefanscountandfollowcount(x,userid,friendid);
        return new Result(true,StatusCode.OK,"更新成功");
    }



}
