package com.hydu.dao;

import com.hydu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	public User findByMobile(String mobile);

    /**
     * 更新粉丝数
     * @param x
     * @param friendid
     */
	@Modifying
	@Query(value = "update tb_user set fanscount=fanscount+? where id=?", nativeQuery = true)
    public void updatefanscount(int x, String friendid);

    @Modifying
    @Query(value = "update tb_user set followcount=followcount+? where id=?", nativeQuery = true)
    public void updatefollowcount(int x, String userid);

    /**
     * 更新粉丝数
     * @param userid
     * @param x
     */
    @Modifying
    @Query(value = "update User u set u.fanscount=u.fanscount+?2 where u.id=?1")
    public void incFanscount(String userid,int x);

    /**
     * 更新关注数
     * @param x
     * @param userid
     */
    @Modifying
    @Query("update User u set u.followcount=u.followcount+?2 where u.id=?1")
    public void incFollowcount(String userid,int x);
}
