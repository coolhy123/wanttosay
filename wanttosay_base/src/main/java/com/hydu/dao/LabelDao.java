package com.hydu.dao;

import com.hydu.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 标签接口层
 * create by heyong on 2019/5/29
 */
public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {


}
