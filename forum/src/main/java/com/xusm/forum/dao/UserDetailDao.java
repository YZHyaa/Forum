package com.xusm.forum.dao;

import com.xusm.forum.entity.UserDetail;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Component
public interface UserDetailDao extends Mapper<UserDetail> {
}
