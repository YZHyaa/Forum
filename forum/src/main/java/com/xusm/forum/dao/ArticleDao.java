package com.xusm.forum.dao;

import com.xusm.forum.vo.RankListResponse;
import com.xusm.forum.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
@Component
public interface ArticleDao extends Mapper<Article> {

    List<Article> selectArticleList(@Param("type") Integer type, @Param("ishot") Boolean ishot, @Param("key") String key, @Param("year") String year);

    @Select("SELECT count(1) FROM article WHERE DATEDIFF(created,NOW())=0 AND state=1 AND type=#{type}")
    Integer queryTodyCount(@Param("type") Integer type);

    @Select("SELECT count(1) FROM article WHERE DATEDIFF(created,NOW())=-1 AND state=1 AND type=#{type}")
    Integer queryYesterdayCount(@Param("type") Integer type);

    @Select("SELECT id,title FROM `article` WHERE DATEDIFF(created,NOW())=0 AND click>#{rankRule} AND state=1 ORDER BY click DESC")
    List<RankListResponse> queryTodayRankList(@Param("rankRule") Integer rankRule);

    @Select("SELECT id,title FROM `article` WHERE DATEDIFF(created,NOW())<=0 AND DATEDIFF(created,NOW())>#{days} AND click>#{rankRule} AND state=1 ORDER BY click DESC")
    List<RankListResponse> queryLastRankList(@Param("days") Integer days, @Param("rankRule") Integer rankRule);

    @Update("UPDATE article SET alike=alike+1 WHERE id=#{id}")
    void updateLike(@Param("id") Long id);

    @Update("UPDATE article SET comment_count=comment_count+1 WHERE id=#{id}")
    void updateCommentCount(@Param("id") Long id);

    @Update("UPDATE article set click=click+#{count} WHERE id=#{id}")
    void updateClick(@Param("id") Long id, @Param("count") Integer count);

//    @Update("UPDATE article set click=click+5 WHERE id=#{id}")
//    public void updateClick(@Param("id") Long id);

    @Select("select YEAR(created) year from article WHERE type=#{type} GROUP BY year ORDER BY year DESC")
    List<String> queryYears(@Param("type") Integer type);

    @Update("UPDATE article SET sort=#{id} WHERE id=#{id}")
    void updateSort(@Param("id") Long id);
}
