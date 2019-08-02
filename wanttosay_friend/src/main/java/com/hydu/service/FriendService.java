package com.hydu.service;

import com.hydu.client.UserClient;
import com.hydu.dao.FriendDao;
import com.hydu.dao.NoFriendDao;
import com.hydu.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;

/**
 * Created by heyong
 * 2019/8/2
 */

@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendService noFriendService;

    @Autowired
    private UserClient userClient;

    @Transactional
    public int addFriend(String userid,String friendid){
        //判断是否是好友
        if(friendDao.selectCount(userid,friendid)>0){
            return 0;
        }

        //如果不是好友
        Friend friend =new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);


        //判断对方是否喜欢你
        if(friendDao.selectCount(friendid,userid)>0){
            friendDao.updateLike(userid,friendid,"1");
            friendDao.updateLike(friendid,userid,"1");
            //增加自己的关注数
            userClient.incFollowcount(userid,1);
            //对方增加粉丝数
            userClient.incFanscount(friendid,1);

        }
        return 1;
    }


    @Transactional
    public void deleteFriend(String userid,String friendId){
        //从tb_friend中删除userid为自己的一条记录
        friendDao.deleteFriend(userid,friendId);
        //把userid为好友id，friendid为自己的记录的islike改为0
        friendDao.updateLike(friendId,userid,"0");
        //在不喜欢表tb_nofriend中添加一条记录
        noFriendService.addNoFriend(userid,friendId);

        //减少自己的关注数
        userClient.incFollowcount(userid,-1);
        //对方减少粉丝数
        userClient.incFanscount(friendId,-1);
    }

}
