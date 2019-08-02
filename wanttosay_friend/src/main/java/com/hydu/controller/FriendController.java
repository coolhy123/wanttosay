package com.hydu.controller;

import com.hydu.service.FriendService;
import com.hydu.service.NoFriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by heyong
 * 2019/8/2
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private NoFriendService noFriendService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtUtil jwtUtil;


    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid,@PathVariable String type){
        String token = (String) request.getAttribute("claims_user");
        Claims claims=jwtUtil.parseJwt(token);
        if(claims==null){
            return  new Result(false, StatusCode.ERROR,"你无权访问");

        }
        //如果喜欢
        if("1".equals(type)){
            //添加好友
            int flag = friendService.addFriend(claims.getId(), friendid);
            if(flag==0){
                return new Result(false, StatusCode.ERROR, "不能重复添加好友");
            }
                return new Result(true, StatusCode.OK, "添加成功");
        }else{
            //不喜欢
            //向不喜欢列表中添加记录
            noFriendService.addNoFriend(claims.getId(),friendid);

        }

        return new Result(true,StatusCode.OK,"操作成功");
    }

    /**
     * 删除好友
     * @param friendid
     * @return
     */
    @RequestMapping(value="/{friendid}",method=RequestMethod.DELETE)
    public Result removeFriend(@PathVariable String friendid){
        String token = (String )request.getAttribute("claims_user");
        Claims  claims = jwtUtil.parseJwt(token);
        if(claims==null){
            return new Result(false,StatusCode.ERROR,"你无权访问");
        }
        friendService.deleteFriend(claims.getId(),friendid);
        return new Result(true,StatusCode.OK,"删除好友成功");
    }
}
