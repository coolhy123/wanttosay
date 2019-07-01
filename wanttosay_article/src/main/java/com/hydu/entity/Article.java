package com.hydu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by heyong
 * 2019/7/1
 */

@Entity
@Table(name = "tb_article")
public class Article {
    @Id
    private String id ;//id

    private String cloumnid;//书栏id

    private String userid;//用户id

    private String title;//标题

    private String content;//内容

    private String image;//图片

    private Date createtime;//发表日期

    private Date updatetime;//修改日期

    private String ispublic;//是否工开

    private String istop;//是否置顶

    private int visits;//浏览量

    private int thumbup;//点赞数

    private int comment;//类型

    private String state;//审核状态 0未审核，1已审核

    private String annelid;//所属频道

    private String url;//url

    private String type;//类型


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCloumnid() {
        return cloumnid;
    }

    public void setCloumnid(String cloumnid) {
        this.cloumnid = cloumnid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public String getIstop() {
        return istop;
    }

    public void setIstop(String istop) {
        this.istop = istop;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getThumbup() {
        return thumbup;
    }

    public void setThumbup(int thumbup) {
        this.thumbup = thumbup;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAnnelid() {
        return annelid;
    }

    public void setAnnelid(String annelid) {
        this.annelid = annelid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
