package com.xusm.forum.dao;

import com.xusm.forum.entity.UserInfo;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Component
public interface UserInfoDao extends Mapper<UserInfo> {
}
