package com.hydu.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by heyong
 * 2019/7/31
 */
@Component
@FeignClient(value = "wanttosay-base" ,fallback = LabelClientImpl.class)
public interface LabelClient {

    @RequestMapping(value = "/label/getOne/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id);
}
