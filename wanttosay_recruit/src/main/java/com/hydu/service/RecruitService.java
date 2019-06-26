package com.hydu.service;

import com.hydu.dao.RecruitDao;

import com.hydu.entity.Enterprise;
import com.hydu.entity.Recruit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by heyong
 * 2019/6/24
 */

@Service
@Transactional
public class RecruitService {
    @Autowired
    private RecruitDao recruitDao;
    @Autowired
    private IdWorker idWorker;


    /**
     * 查询所有的招聘信息
     *
     * @return
     */
    public List<Recruit> queryAll(){
        return recruitDao.findAll();
    }

    /**
     * 新增一条招聘信息
     * @param recruit
     */
    public void saveRecruit(Recruit recruit){
        recruit.setId(idWorker.nextId()+"");
         recruitDao.save(recruit);
    }

    /**
     * 修改一条招聘信息
     * @param recruit
     */
    public void updateRecrit(Recruit recruit){
        recruitDao.save(recruit);
    }

    /**
     * 新增一条招聘信息
     * @param id
     * @return
     */
    public Recruit queryOneByid(String id){
        return recruitDao.findById(id).get();
    }

    public void deleteOneById(String id){
        recruitDao.deleteById(id);
    }

    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Recruit> queryPageRecruit(Map whereMap, int page, int size){
        Specification<Recruit> specification=createSpecification(whereMap);
        PageRequest pageRequest=PageRequest.of(page-1,size);

        return recruitDao.findAll(specification,pageRequest);
    }


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<Recruit> findSearch(Map whereMap) {
        Specification<Recruit> specification = createSpecification(whereMap);
        return recruitDao.findAll(specification);
    }

    public List<Recruit> findTop6ByStateOrderByCreatetime(){
        return recruitDao.findTop6ByStateOrderByCreatetimeDesc("2");
    }

    public List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(){
        return recruitDao.findTop6ByStateNotOrderByCreatetimeDesc("0");
    }

    private Specification<Recruit> createSpecification(Map searchMap) {
        return new Specification<Recruit>(){

            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList =new ArrayList<>();
                if(StringUtils.isBlank((String)searchMap.get("id"))){
                    predicateList.add(cb.like(root.get("id").as(String.class),"%"+searchMap.get("id")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("jobname"))){
                    predicateList.add(cb.like(root.get("jobname").as(String.class),"%"+searchMap.get("jobname")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("salary"))){
                    predicateList.add(cb.like(root.get("salary").as(String.class),"%"+searchMap.get("salary")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("education"))){
                    predicateList.add(cb.like(root.get("education").as(String.class),"%"+searchMap.get("education")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("address"))){
                    predicateList.add(cb.like(root.get("address").as(String.class),"%"+searchMap.get("address")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("eid"))){
                    predicateList.add(cb.like(root.get("eid").as(String.class),"%"+searchMap.get("eid")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("type"))){
                    predicateList.add(cb.like(root.get("type").as(String.class),"%"+searchMap.get("type")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("url"))){
                    predicateList.add(cb.like(root.get("url").as(String.class),"%"+searchMap.get("url")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("label"))){
                    predicateList.add(cb.like(root.get("label").as(String.class),"%"+searchMap.get("label")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("content1"))){
                    predicateList.add(cb.like(root.get("content1").as(String.class),"%"+searchMap.get("content1")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("content2"))){
                    predicateList.add(cb.like(root.get("content2").as(String.class),"%"+searchMap.get("content2")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("rcondition"))){
                    predicateList.add(cb.like(root.get("rcondition").as(String.class),"%"+searchMap.get("rcondition")+"%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }


        };
    }
}
