package com.xusm.forum.service;

import com.xusm.forum.vo.ArticleResponse;
import com.xusm.forum.vo.PageResult;
import com.xusm.forum.vo.ArticleRequest;
import com.xusm.forum.vo.RankListResponse;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    PageResult queryArticleList(Integer type, boolean ishot, Integer page, Integer row, String key, String year);

    List<RankListResponse> queryRankList(Integer date);

    void post(ArticleRequest articleRequest);

    ArticleResponse queryArticle(Long id);
}
