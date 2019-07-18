package com.hydu.service;

import com.hydu.dao.SpitDao;
import com.hydu.entity.Spit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;
/**
 * Created by heyong
 * 2019/7/2
 */

@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询所有
     * @return
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 通过id查询某一条
     * @param id
     * @return
     */
    public Spit queryOne(String id){
        return spitDao.findById(id).get();
    }

    /**
     * 新增一条吐槽记录
     * @param spit
     */
    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if(!StringUtils.isBlank(spit.getParentid())){
            //如果存在上级id，评论
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
         spitDao.save(spit);
    }

    /**
     * 删除一条吐槽记录
     * @param id
     */
    public void delete(String id){
        spitDao.deleteById(id);
    }

    /**
     * 更新一条吐槽记录
     * @param spit
     */
    public void updateSpit(Spit spit){
        spitDao.save(spit);
    }

    /**
     *通过父级id查询吐槽列表
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentid, int page, int size){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return spitDao.findByParentid(parentid, pageRequest);
    }

    /**
     * 更新点赞数
     * @param id
     */
    public void updateThumbup(String id){
//        Spit spit=spitDao.findById(id).get();
//        spit.setThumbup(spit.getThumbup()+1);
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update=new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }

    /**
     * 更新分享数
     * @param id
     */
    public void updateShare(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("share",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }



    /**
     * 更新浏览数
     * @param id
     */
    public void updateVisits(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("visits",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}
