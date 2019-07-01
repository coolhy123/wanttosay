package com.hydu.service;

import com.hydu.dao.ProblemDao;
import com.hydu.entity.Problem;
import entity.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
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

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;

/**
 * Created by heyong
 * 2019/6/27
 */

@Service
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;

    @Autowired
    private IdWorker idWorker;


    /**
     * 查询所有
     * @return
     */
    public List<Problem> findAll(){
        return problemDao.findAll();
    }

    /**
     * 通过id查询一个问题
     * @param id
     * @return
     */
    public Problem findProblemById(String id){
        return problemDao.findById(id).get();
    }

    /**
     * 新增一个问题
     * @param problem
     */
    public void save(Problem problem){
        problem.setId(idWorker.nextId()+"");
         problemDao.save(problem);
    }

    /**
     * 修改问题
     * @param problem
     */
    public void updateProblem(Problem problem){
        problemDao.save(problem);
    }

    public void deleteProblem(String  id){
        problemDao.deleteById(id);
    }

    /**
     * 条件查询+page
     * @param serachMap
     * @param page
     * @param size
     * @return
     */
    public Page<Problem> problemPage(Map serachMap,int page,int size){
        Specification<Problem> specification=createSpecification(serachMap);
        PageRequest pageRequest= PageRequest.of(page-1,size);
        return problemDao.findAll(specification,pageRequest);
    }

    /**
     * 条件查询
     * @param serachMap
     * @return
     */
    public List<Problem> problemList(Map serachMap){
        Specification<Problem> specification=createSpecification(serachMap);
        return problemDao.findAll(specification);
    }


    /**
     * 最新问题列表
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    public Page<Problem> newsList(String labelid, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return problemDao.newsList(labelid, pageable);
    }


    /**
     * 最火问题
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    public Page<Problem> hostList(String labelid, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return problemDao.hostList(labelid,pageable);
    }


    /**
     * 等待回答的问题
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    public Page<Problem> waitList(String labelid, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return problemDao.waitList(labelid,pageable);
    }


    private Specification<Problem> createSpecification(Map searchMap) {
        return new  Specification<Problem>(){
            @Nullable
            @Override
            public Predicate toPredicate(javax.persistence.criteria.Root<Problem> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicateList =new ArrayList<>();
                //id
                if (!StringUtils.isBlank((String)searchMap.get("id"))){
                    predicateList.add(cb.like(root.get("id").as(String.class),"%"+searchMap.get("id")+"%"));
                }
                //标题
                if (!StringUtils.isBlank((String)searchMap.get("title"))){
                    predicateList.add(cb.like(root.get("title").as(String.class),"%"+searchMap.get("title")+"%"));
                }
                //内容
                if (!StringUtils.isBlank((String)searchMap.get("content"))){
                    predicateList.add(cb.like(root.get("content").as(String.class),"%"+searchMap.get("content")+"%"));
                }
                //用户id
                if (!StringUtils.isBlank((String)searchMap.get("userid"))){
                    predicateList.add(cb.like(root.get("userid").as(String.class),"%"+searchMap.get("userid")+"%"));
                }
                //昵称
                if (!StringUtils.isBlank((String)searchMap.get("nickname"))){
                    predicateList.add(cb.like(root.get("nickname").as(String.class),"%"+searchMap.get("nickname")+"%"));
                }
                //是否解决
                if (!StringUtils.isBlank((String)searchMap.get("solve"))){
                    predicateList.add(cb.like(root.get("solve").as(String.class),"%"+searchMap.get("solve")+"%"));
                }
                //回复人名称
                if (!StringUtils.isBlank((String)searchMap.get("replyname"))){
                    predicateList.add(cb.like(root.get("replyname").as(String.class),"%"+searchMap.get("replyname")+"%"));
                }

                //大于等于开始时间
                if (!StringUtils.isBlank((String)searchMap.get("replyname"))){
                    predicateList.add(cb.greaterThanOrEqualTo(root.get(""),searchMap.get("replyname")+""));
                }

                //小于等于结束时间
                if (!StringUtils.isBlank((String)searchMap.get("replyname"))){
                    predicateList.add(cb.lessThanOrEqualTo(root.get(""),searchMap.get("replyname")+""));
                }


                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }

        };
    }
}
