package com.jk.controller;

import com.jk.model.MallBean;
import com.jk.model.MallTypeBean;
import com.jk.service.MallFeignService;
import com.jk.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mall")
public class MallController {

    @Resource
    private MallFeignService mallFeignService;

    @RequestMapping("/toMallPage")
    public String toMallPage(){
        return "mall";
    }

    @RequestMapping("/findMallList")
    @ResponseBody
    public List<MallBean> findMallList(){
        return mallFeignService.findMallList();
    }

    @RequestMapping("toAddMallPage")
    public String toAddMallPage(){
        return "addmall";
    }

    @RequestMapping("/findMallTypeList")
    @ResponseBody
    public List<MallTypeBean> findMallTypeList(){
        return mallFeignService.findMallTypeList();
    }

    @RequestMapping("/saveMall")
    @ResponseBody
    public Boolean saveMall(MallBean mallBean){
        try {
            mallFeignService.saveMall(mallBean);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("upload")
    @ResponseBody
    public Map upload(MultipartFile img, HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        String fileUpload = FileUtil.FileUpload(img, request);
        result.put("img", fileUpload);
        return result;
    }
}
