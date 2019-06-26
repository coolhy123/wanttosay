package com.hydu.service;

import com.hydu.dao.EnterpriseDao;
import com.hydu.entity.Enterprise;
import entity.PageResult;
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
import java.util.*;

/**
 * Created by heyong
 * 2019/6/12
 */

@Service
@Transactional
public class EnterpriseService {

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private IdWorker idWorker;


    /**
     * 查询所有的
     * @return
     */
    public List<Enterprise> queryAll(){
        return enterpriseDao.findAll();
    }



    /**\
     * 新增一个Enterprise
     * @param enterprise
     */
    public void addEnterprise(Enterprise enterprise){
        enterprise.setId(idWorker.nextId()+"");
        enterpriseDao.save(enterprise);
    }

    /**
     * 修改企业信息
     * @param enterprise
     */
    public void updateEnterprise(Enterprise enterprise){
        enterpriseDao.save(enterprise);
    }

    /**
     * 根据查询一个企业
     * @param id
     * @return
     */
    public Enterprise queryOne(String id){
        return enterpriseDao.findById(id).get();
    }


    /**
     * 删除
     * @param id
     */
    public void deleteById(String id) {
        enterpriseDao.deleteById(id);
    }

    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Enterprise> queryPageEnterprise(Map whereMap, int page, int size ){
        Specification<Enterprise> specification=createSpecification(whereMap);
        PageRequest pageRequest=PageRequest.of(page-1,size);

        return enterpriseDao.findAll(specification,pageRequest);
    }


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<Enterprise> findSearch(Map whereMap) {
        Specification<Enterprise> specification = createSpecification(whereMap);
        return enterpriseDao.findAll(specification);
    }


    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<Enterprise> createSpecification(Map searchMap) {
        return new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicateList =new ArrayList<>();
                //  id
                if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                    predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // // 企业名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                //企业地址
                if(searchMap.get("address")!=null && !"".equals(searchMap.get("address"))){
                    predicateList.add(cb.like(root.get("address").as(String.class),"%"+(String)searchMap.get("address")+"%"));
                }
                //summary 企业简介
                if(searchMap.get("summary")!=null && !"".equals(searchMap.get("summary"))){
                    predicateList.add(cb.like(root.get("summary").as(String.class),"%"+searchMap.get("summary")+"%"));
                }
                //标签
                if(StringUtils.isBlank((String)searchMap.get("labels"))){
                    predicateList.add(cb.like(root.get("labels").as(String.class),"%"+searchMap.get("labels")+"%"));
                }
                // // 坐标
                if(StringUtils.isBlank((String)searchMap.get("coordinate"))){
                    predicateList.add(cb.like(root.get("coordinate").as(String.class),"%"+searchMap.get("coordinate")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("ishot"))){
                    predicateList.add(cb.like(root.get("ishot").as(String.class),"%"+searchMap.get("ishot")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("logo"))){
                    predicateList.add(cb.like(root.get("logo").as(String.class),"%"+searchMap.get("logo")+"%"));
                }
                if(StringUtils.isBlank((String)searchMap.get("url"))){
                    predicateList.add(cb.like(root.get("url").as(String.class),"%"+searchMap.get("url")+"%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));//Predicate必须给定长度
            }
        };

    }

    /**
     * 热门企业
     */
    public List<Enterprise> hotEnterpriseList(){
        return enterpriseDao.findByIshot("1");//热门企业为1
    }
}
