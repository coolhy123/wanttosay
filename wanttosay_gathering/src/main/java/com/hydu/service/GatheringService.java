package com.hydu.service;

import com.hydu.dao.GatheringDao;
import com.hydu.entity.Gathering;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by heyong
 * 2019/7/2
 */

@Service
public class GatheringService {
    @Autowired
    private GatheringDao gatheringDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有活动
     * @return
     */
    public List<Gathering> findAll(){
        return gatheringDao.findAll();
    }

    /**
     * 查询某个活动通过id
     * @param id
     * @return
     */
    @Cacheable(value = "gathering",key="#id")
    public Gathering queryOne(String id){
        return gatheringDao.findById(id).get();
    }

    /**
     * 添加活动
     * @param gathering
     */
    public void save(Gathering gathering){
        gathering.setId(idWorker.nextId()+"");
        gatheringDao.save(gathering);
    }

    /**
     * 删除活动
     * @param id
     */
    @CacheEvict(value = "gathering" ,key = "#23id")
    public void detele(String id){
        gatheringDao.deleteById(id);
    }

    /**
     * 修改活动
     * @param gathering
     */
    @CacheEvict(value = "gathering" ,key = "#gathering.id")
    public void updataGathering(Gathering gathering){
        gatheringDao.save(gathering);
    }

    /**
     * 分页查询+模糊查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<Gathering> pageList(Map searchMap, int page,int size ){
        Specification<Gathering> specification=specification(searchMap);
        PageRequest pageRequest=PageRequest.of(page-1,size);
        return gatheringDao.findAll(specification,pageRequest);
    }

    /**
     * 模糊查询
     * @param serachMap
     * @return
     */
    public List<Gathering> gatheringList(Map serachMap){
        Specification<Gathering> specification=specification(serachMap);
        return gatheringDao.findAll(specification);
    }


    public Specification<Gathering> specification(Map searchMap){
        return new Specification<Gathering>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Gathering> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList=new ArrayList<>();
                if(!StringUtils.isBlank((String)searchMap.get("id"))){
                    predicateList.add(cb.like(root.get("id").as(String.class),"%"+searchMap.get("id")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("name"))){
                    predicateList.add(cb.like(root.get("name").as(String.class),"%"+searchMap.get("name")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("summary"))){
                    predicateList.add(cb.like(root.get("summary").as(String.class),"%"+searchMap.get("summary")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("detail"))){
                    predicateList.add(cb.like(root.get("detail").as(String.class),"%"+searchMap.get("detail")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("sponsor"))){
                    predicateList.add(cb.like(root.get("sponsor").as(String.class),"%"+searchMap.get("sponsor")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("image"))){
                    predicateList.add(cb.like(root.get("image").as(String.class),"%"+searchMap.get("image")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("address"))){
                    predicateList.add(cb.like(root.get("address").as(String.class),"%"+searchMap.get("address")+"%"));
                }
                if(!StringUtils.isBlank((String)searchMap.get("city"))){
                    predicateList.add(cb.like(root.get("city").as(String.class),"%"+searchMap.get("city")+"%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
