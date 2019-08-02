package com.hydu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by heyong
 * 2019/8/2
 */
@Component
@FeignClient("wanttosay-user")
public interface UserClient {
    /**
     * 更新粉丝数
     * @param userid
     * @param x
     */
    @RequestMapping(value="/user/incfans/{userid}/{x}",method= RequestMethod.POST)
    public void incFanscount (@PathVariable("userid") String userid, @PathVariable("x") int x);

    /**
     * 增加关注数
     * @param userid
     * @param x
     */
    @RequestMapping(value="/user/incfollow/{userid}/{x}",method= RequestMethod.POST)
    public void incFollowcount (@PathVariable("userid") String userid, @PathVariable("x") int x);
}
