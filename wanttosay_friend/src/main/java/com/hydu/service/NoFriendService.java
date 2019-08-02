package com.hydu.service;

import com.hydu.dao.NoFriendDao;
import com.hydu.entity.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by heyong
 * 2019/8/2
 */

@Service
public class NoFriendService {
    @Autowired
    private NoFriendDao noFriendDao;

    public void addNoFriend(String userid,String friendid){
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
