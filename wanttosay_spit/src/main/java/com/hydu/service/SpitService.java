package com.hydu.service;

import com.hydu.dao.SpitDao;
import com.hydu.entity.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;
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



    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit queryOne(String id){
        return spitDao.findById(id).get();
    }

    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
         spitDao.save(spit);
    }

    public void delete(String id){
        spitDao.deleteById(id);
    }

    public void updateSpit(Spit spit){
        spitDao.save(spit);
    }

    /**
     *
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentid, int page, int size){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return spitDao.findByParentid(parentid, pageRequest);
    }
}
