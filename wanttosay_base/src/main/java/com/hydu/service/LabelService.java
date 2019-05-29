package com.hydu.service;

import com.hydu.dao.LabelDao;
import com.hydu.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    public List<Label> selectAll(){
        List<Label> list=new ArrayList<>();
        list=labelDao.findAll();
        return list;
    }
}
