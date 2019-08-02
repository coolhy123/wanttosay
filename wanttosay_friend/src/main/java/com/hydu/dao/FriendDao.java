package com.hydu.dao;

import com.hydu.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by heyong
 * 2019/8/2
 */
public interface FriendDao extends JpaRepository<Friend,String > {

    /**
     * 根据用户ID与被关注用户ID查询记录个数
     * f.userid=?1  1表示第一个参数
     * f.friendid=?2 2表示第二个参数
     * @param userid
     * @param friendid
     * @return
     */
    @Query("select count(f) from Friend f where f.userid=?1 and f.friendid=?2")
    public int selectCount(String userid,String friendid );

    /**
     * 更新为互相喜欢
     * nativeQuery  为false，表名就必须用实体类名  为true则使用表名
     * @param userid
     * @param friendid
     * @param islike
     */
    @Modifying
    @Query(value = "update Friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
    public void updateLike(String userid,String friendid,String islike);

    @Modifying
    @Query(value = "delete from Friend f where f.userid=?1 and f.friendid=?2")
    public void deleteFriend(String suerid,String friendid);

}
