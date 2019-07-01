package com.hydu.dao;

import com.hydu.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by heyong
 * 2019/7/1
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article> {

    /**
     * 修改审核状态，SpringdataJPA做修改的时候必须加@Modifying
     * @param id
     */
    @Modifying
    @Query(value = "update tb_ARTICLE set state='1' where id=?",nativeQuery = true)
    public void updateState(String id);

    /**
     * 修改点赞数
     * @param id
     */
    @Modifying
    @Query(value = "update tb_ARTICLE set thumbup=thumbup+1 where id=?",nativeQuery = true)
    public void updateThumbup(String id);

}
