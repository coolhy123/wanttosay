package com.hydu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by heyong
 * 2019/6/12
 */

@Entity
@Table(name = "tb_enterprise")
public class Enterprise{
    @Id
    private String id;//id
    private String name;//企业名字
    private String summary;//企业简介
    private String address;//企业地址
    private String labels; // 标签
    private String coordinate; // 坐标
    private String ishot;//是否热门
    private String logo;//logo
    private Integer jobcount;//
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getJobcount() {
        return jobcount;
    }

    public void setJobcount(Integer jobcount) {
        this.jobcount = jobcount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
