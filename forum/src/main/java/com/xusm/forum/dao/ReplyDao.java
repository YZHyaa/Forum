package com.xusm.forum.dao;

import com.xusm.forum.entity.Reply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
@Component
public interface ReplyDao extends Mapper<Reply> {

    @Update("UPDATE reply SET rep_like=rep_like+1 WHERE rep_id=#{repId}")
    void updateRepLike(@Param("repId") Long repId);

    List<Reply> queryReplyList(@Param("commentId") Long commentId, @Param("state") Integer state);

    @Update("UPDATE reply SET rep_author_name=#{username} WHERE rep_author=#{uId}")
    void updateRepauthorName(@Param("uId") Long uId, @Param("username") String username);

    @Update("UPDATE reply SET to_rep_author_name=#{username} WHERE to_rep_author=#{uId}")
    void updateToRepauthorName(@Param("uId") Long uId, @Param("username") String username);
}
