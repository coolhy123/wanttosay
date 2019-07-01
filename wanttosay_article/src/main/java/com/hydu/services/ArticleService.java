package com.hydu.services;

import com.hydu.dao.ArticleDao;
import com.hydu.entity.Article;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by heyong
 * 2019/7/1
 */

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有文章
     * @return
     */
    public List<Article> findAll(){
        return articleDao.findAll();
    }

    /**
     * 查询一篇文章
     * @param id
     * @return
     */
    public Article queryOne(String id){
     //从缓存中取这篇文章
        Article article=(Article)redisTemplate.opsForValue().get("article_"+id);
        //如果缓存为空，从数据库查询，并存到缓存,设置过期时间为1天
     if(article==null){
         article = articleDao.findById(id).get();
         redisTemplate.opsForValue().set("article_"+id,article,1,TimeUnit.DAYS);
     }
        return article;
    }

    /**
     * 更新文章
     * @param article
     */
    public void updateOne(Article article){
        redisTemplate.delete( "article_" + article.getId() );//删除缓存
        articleDao.save(article);
    }

    /**
     * 删除文章
     * @param id
     */
    public void deleteOne(String id){
        redisTemplate.delete( "article_" + id );//删除缓存
        articleDao.deleteById(id);
    }

    /**
     * 新增文章
     * @param article
     */
    public void save(Article  article){
        article.setId(idWorker.nextId()+"");
        articleDao.save(article);
    }

    /**
     * 更新审核状态
     * @param id
     */
    public void updateState(String id){
        articleDao.updateState(id);
    }

    /**
     * 更新点赞数
     * @param id
     */
    public void updateThumbup(String id){
        articleDao.updateState(id);
    }

    /**
     * 条件查询+分页
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<Article> pageList(Map searchMap,int page,int size){
        Specification<Article> specification = specification(searchMap);
        PageRequest pageRequest=PageRequest.of(page-1,size);
        return articleDao.findAll(specification,pageRequest);

    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    public List<Article> articleList(Map searchMap){
        Specification<Article> specification = specification(searchMap);
        return articleDao.findAll(specification);

    }

    public Specification<Article> specification(Map searchMap){
        return new Specification<Article>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList=new ArrayList<>();
                if(!StringUtils.isBlank((String)searchMap.get("id"))){
                    predicateList.add(cb.like(root.get("id").as(String.class),"%"+searchMap.get("id")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("cloumnid"))){
                    predicateList.add(cb.like(root.get("cloumnid").as(String.class),"%"+searchMap.get("cloumnid")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("userid"))){
                    predicateList.add(cb.like(root.get("userid").as(String.class),"%"+searchMap.get("userid")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("title"))){
                    predicateList.add(cb.like(root.get("title").as(String.class),"%"+searchMap.get("title")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("content"))){
                    predicateList.add(cb.like(root.get("content").as(String.class),"%"+searchMap.get("content")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("ispublic"))){
                    predicateList.add(cb.like(root.get("ispublic").as(String.class),"%"+searchMap.get("ispublic")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("istop"))){
                    predicateList.add(cb.like(root.get("istop").as(String.class),"%"+searchMap.get("istop")+"%"));
                }

                if(!StringUtils.isBlank((String)searchMap.get("comment"))){
                    predicateList.add(cb.like(root.get("comment").as(String.class),"%"+searchMap.get("comment")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("state"))){
                    predicateList.add(cb.like(root.get("state").as(String.class),"%"+searchMap.get("state")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("annelid"))){
                    predicateList.add(cb.like(root.get("annelid").as(String.class),"%"+searchMap.get("annelid")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("type"))){
                    predicateList.add(cb.like(root.get("type").as(String.class),"%"+searchMap.get("type")+"%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };
    }

}
