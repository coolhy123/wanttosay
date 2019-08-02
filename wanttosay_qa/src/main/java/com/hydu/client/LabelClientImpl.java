package com.hydu.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by heyong
 * 2019/8/2
 */

@Component
public class LabelClientImpl implements LabelClient {
    public Result findById(@PathVariable("id") String id){
        return new Result(false, StatusCode.ERROR,"熔断器启动了");
    }
}
