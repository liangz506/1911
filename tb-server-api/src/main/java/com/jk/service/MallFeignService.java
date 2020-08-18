package com.jk.service;

import com.jk.model.MallBean;
import com.jk.model.MallTypeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "tb-provider-two")
public interface MallFeignService {

    @RequestMapping("/findMallList")
    List<MallBean> findMallList();

    @RequestMapping("/findMallTypeList")
    List<MallTypeBean> findMallTypeList();

    @RequestMapping("/saveMall")
    void saveMall(MallBean mallBean);
}
