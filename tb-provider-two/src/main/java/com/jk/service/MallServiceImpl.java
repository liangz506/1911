package com.jk.service;

import com.jk.mapper.MallMapper;
import com.jk.model.MallBean;
import com.jk.model.MallTypeBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MallServiceImpl implements MallFeignService{

    @Resource
    private MallMapper mallMapper;

    @Override
    @RequestMapping("/findMallList")
    public List<MallBean> findMallList() {
        return mallMapper.findMallList();
    }

    @Override
    @RequestMapping("/findMallTypeList")
    public List<MallTypeBean> findMallTypeList() {
        return mallMapper.findMallTypeList();
    }

    @Override
    @RequestMapping("/saveMall")
    public void saveMall(@RequestBody MallBean mallBean) {
        mallMapper.saveMall(mallBean);
    }
}
