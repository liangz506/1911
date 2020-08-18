package com.jk.mapper;

import com.jk.model.MallBean;
import com.jk.model.MallTypeBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallMapper {

    @Select("select tm.*,tt.name as mallName from t_mall tm left join t_mall_type tt on tm.type_id = tt.id")
    List<MallBean> findMallList();

    @Select("select * from t_mall_type")
    List<MallTypeBean> findMallTypeList();

    @Insert("insert into t_mall(title,price,img,type_id,detail,create_time) values(#{title},#{price},#{img},#{typeId},#{detail},#{createTime})")
    void saveMall(MallBean mallBean);
}
