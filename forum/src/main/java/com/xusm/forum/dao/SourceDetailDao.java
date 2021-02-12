package com.xusm.forum.dao;

import com.xusm.forum.entity.SourceDetail;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Component
public interface SourceDetailDao extends Mapper<SourceDetail> {
}
