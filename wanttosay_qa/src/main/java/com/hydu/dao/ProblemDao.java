package com.hydu.dao;

import com.hydu.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by heyong
 * 2019/6/27
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem> {
    /**
     * 最新问题列表
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid=?) ORDER BY replytime DESC", nativeQuery = true )
    public Page<Problem> newsList(String labelid, Pageable pageable);

    /**
     * 最热门的问题
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "SELECT  *  FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid=?) ORDER BY reply DESC",nativeQuery = true )
    public Page<Problem> hostList(String labelid, Pageable pageable);


    @Query(value = "SELECT  *  FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid=? ) and reply =0",nativeQuery = true )
    public Page<Problem> waitList(String labelid, Pageable pageable);




}
