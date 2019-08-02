package com.hydu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by heyong
 * 2019/8/2
 */

@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)
public class NoFriend implements Serializable {
    @Id
    private String userid;

    @Id
    private String friendid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }
}
