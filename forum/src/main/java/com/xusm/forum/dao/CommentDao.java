package com.xusm.forum.dao;

import com.xusm.forum.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
@Component
public interface CommentDao extends Mapper<Comment> {

    @Update("UPDATE `comment` SET com_like=com_like+1 WHERE id=#{id}")
    void updateComLike(@Param("id") Long id);

    @Update("UPDATE `comment` SET rep_count=rep_count+1 WHERE id=#{id}")
    void updateRepCount(@Param("id") Long id);

    List<Comment> queryCommnentList(@Param("id") Long id, @Param("state") Integer state);

    @Update("UPDATE `comment` SET com_author_name=#{username} WHERE com_author=#{uId}")
    void updateAuthorName(@Param("uId") Long uId, @Param("username") String username);
}
