package com.xusm.forum.dao;

import com.xusm.forum.entity.ArticleRank;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
@Component
public interface ArticleRankDao extends Mapper<ArticleRank> {

    @Select("SELECT art_id FROM article_rank WHERE rank_type=#{type} ORDER BY rank_sort DESC LIMIT #{size}")
    public List<Long> queryRankList(@Param("type") Integer type, @Param("size") Integer size);
}
