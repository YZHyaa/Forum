package com.xusm.forum.dao;

import com.xusm.forum.entity.Function;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Component
public interface FunctionDao extends Mapper<Function> {
}
