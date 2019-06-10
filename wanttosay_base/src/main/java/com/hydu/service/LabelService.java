package com.hydu.service;

import com.hydu.dao.LabelDao;
import com.hydu.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;
    public List<Label> selectAll(){
        List<Label> list=new ArrayList<>();
        list=labelDao.findAll();
        return list;
    }

    public void deleteOne(String  labelId){
        labelDao.deleteById(labelId);
    }

    public void insertOne(Label label){
        label.setId(idWorker.nextId()+"");
       labelDao.save(label);

    }

    public void update(Label label){
        labelDao.save(label);
    }

    public Label getOne(String labelId){
      return   labelDao.findById(labelId).get();
    }

    public List<Label> findSearch(Label label) {
       return labelDao.findAll(new Specification<Label>() {
           /**
            *
            * @param root 跟对象，也就是要把条件装到哪个对象中，where 类名 leable.getid
            * @param query  封装的都是查询关键字  比如group by  order by 等
            * @param cb 用来封装条件对象的，如果直接返回，表示不需要任何条件
            * @return
            */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new一个集合，存放所有的条件
                List<Predicate> list=new ArrayList<>();
                if (label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate predocate=  cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");//  label.getLabelname like %...%
                    list.add(predocate);
                }
                if(label.getState()!=null && !"".equals(label.getState())){
                    Predicate predocate=  cb.like(root.get("state").as(String.class),label.getState());//  label.getState = 1
                    list.add(predocate);
                }
                //new一个数组作为最终返回的条件
                Predicate[] parr=new Predicate[list.size()];//空值
                parr=list.toArray(parr);
                return cb.and(parr);
            }
        });
    }

    public Page<Label> pageQuery(Label label,int page,int size){
        Pageable pageable = PageRequest.of(page-1,size);//从第0页开始
        return labelDao.findAll(new Specification<Label>(){
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //new一个集合，存放所有的条件
                List<Predicate> list=new ArrayList<>();
                if (label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate predocate=  cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");//  label.getLabelname like %...%
                    list.add(predocate);
                }
                if(label.getState()!=null && !"".equals(label.getState())){
                    Predicate predocate=  cb.like(root.get("state").as(String.class),label.getState());//  label.getState = 1
                    list.add(predocate);
                }
                //new一个数组作为最终返回的条件
                Predicate[] parr=new Predicate[list.size()];//空值
                parr=list.toArray(parr);
                return cb.and(parr);
            }
        },pageable);

    }
}
