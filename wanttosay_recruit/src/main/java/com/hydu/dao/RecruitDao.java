package com.hydu.dao;

import com.hydu.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.*;
/**
 * 招聘信息接口
 * Created by heyong
 * 2019/6/12
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit> {
    /**
     * 查询最新的招聘信息前六条
     * @param state
     * @return
     */
    public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);


    public List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);

}
