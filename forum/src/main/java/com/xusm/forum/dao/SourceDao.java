package com.xusm.forum.dao;

import com.xusm.forum.entity.Source;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Component
public interface SourceDao extends Mapper<Source> {

    @Select("UPDATE source SET source_click = source_click+#{count} WHERE source_id=#{id}")
    void updateClick(@Param("id") Long id, @Param("count") Integer count);
}
