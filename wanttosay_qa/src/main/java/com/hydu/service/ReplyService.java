package com.hydu.service;

import com.hydu.dao.ReplyDao;
import com.hydu.entity.Reply;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有回答
     * @return
     */
    public List<Reply> findAll(){
        return replyDao.findAll();
    }

    /**
     * 通过id查询某一条回答
     * @param id
     * @return
     */
    public Reply findOne(String id){
        return replyDao.findById(id).get();
    }

    /**
     * 新增一条回答
     * @param reply
     */
    public void save(Reply reply){
        reply.setId(idWorker.nextId()+"");
    }

    /**
     * 更新回答
     * @param reply
     */
    public void updateReply(Reply reply){
        replyDao.save(reply);
    }

    /**
     * 删除一个回答
     * @param id
     */
    public void deleteOne(String id){
        replyDao.deleteById(id);
    }

    /**
     * 条件查询+page
     * @param serachMap
     * @param page
     * @param size
     * @return
     */
    public Page<Reply> pageList(Map serachMap,int page,int size){
        Specification<Reply> specification=specification(serachMap);
        PageRequest pageRequest=PageRequest.of(page+1,size);
        return  replyDao.findAll(specification,pageRequest);
    }


    /**
     * 条件查询
     * @param serachMap
     * @return
     */
    public List<Reply> replyList(Map serachMap){
        Specification<Reply> specification=specification(serachMap);

        return  replyDao.findAll(specification);
    }

    public Specification<Reply>  specification(Map searchMap){
        return new Specification<Reply>() {
            @Override
            public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList=new ArrayList<>();

                //回答者的id
                if(StringUtils.isBlank((String)searchMap.get("id"))){
                    predicateList.add(cb.like(root.get("id").as(String.class),"%"+searchMap.get("id")+"%"));
                }
                //问题id
                if(StringUtils.isBlank((String)searchMap.get("problemid"))){
                    predicateList.add(cb.like(root.get("problemid").as(String.class),"%"+searchMap.get("problemid")+"%"));
                }
                //内容
                if(StringUtils.isBlank((String)searchMap.get("content"))){
                    predicateList.add(cb.like(root.get("content").as(String.class),"%"+searchMap.get("content")+"%"));
                }
                ////userid
                if(StringUtils.isBlank((String)searchMap.get("userid"))){
                    predicateList.add(cb.like(root.get("userid").as(String.class),"%"+searchMap.get("userid")+"%"));
                }
                ////昵称
                if(StringUtils.isBlank((String)searchMap.get("nickname"))){
                    predicateList.add(cb.like(root.get("nickname").as(String.class),"%"+searchMap.get("nickname")+"%"));
                }
                 return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
