package com.hydu;

import com.hydu.entity.Enterprise;
import com.hydu.entity.Recruit;
import com.hydu.service.EnterpriseService;
import com.hydu.service.RecruitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
/**
 * Created by heyong
 * 2019/6/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnterpriseTest {
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    public  RecruitService recruitService;
    @Test
    public void contextLoads() {
        List <Enterprise> enterpriseList=enterpriseService.queryAll();


//        for (int i=0;i<enterpriseList.size();i++){
//            System.out.println("===============查询所有===================");
//            System.out.println(enterpriseList.get(i).getId()+"\t"+enterpriseList.get(i).getName());
//
//        System.out.println("==================================");
//        }


        System.out.println("=================查询一个=================");

        Enterprise enterprise=enterpriseService.queryOne("2");
        System.out.println(enterprise.getId()+"\t"+enterprise.getName());
        System.out.println("==================================");

        List <Recruit> recruitList=recruitService.queryAll();
        for (Recruit recrust:recruitList) {
            System.out.println(recrust.getId()+"\t"+recrust.getJobname());

        }





    }

}
