package com.xusm.forum.dao;

import com.xusm.forum.entity.Admin;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Component
public interface AdminDao extends Mapper<Admin> {
}
