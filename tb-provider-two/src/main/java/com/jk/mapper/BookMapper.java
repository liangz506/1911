package com.jk.mapper;

import com.jk.model.BookBean;
import com.jk.model.BookTypeBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select tb.*,tt.name as typeName from t_book tb left join t_type tt on tb.type_id = tt.id")
    List<BookBean> findBookList();

    @Select("select * from t_type")
    List<BookTypeBean> findTypeList();

    @Insert("insert into t_book(name,price,type_id,create_time,author) values(#{name},#{price},#{typeId},#{createTime},#{author})")
    void saveBook(BookBean bookBean);

    @Delete("delete from t_book where id = #{value}")
    void deleteBook(Integer id);

    @Select("select tb.*,tt.name as typeName from t_book tb left join t_type tt on tb.type_id = tt.id where tb.id = #{value}")
    BookBean findBookInfoById(Integer id);

    @Update("update t_book set name=#{name},price=#{price},type_id=#{typeId},create_time=#{createTime},author=#{author} where id = #{id}")
    void updateBook(BookBean bookBean);
}
