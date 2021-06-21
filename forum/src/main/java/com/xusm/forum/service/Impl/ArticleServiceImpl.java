package com.xusm.forum.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xusm.forum.constant.redis.ArticleRedisKey;
import com.xusm.forum.constant.ArticleType;
import com.xusm.forum.constant.DateType;
import com.xusm.forum.constant.redis.WebsiteRedisKey;
import com.xusm.forum.vo.*;
import com.xusm.forum.config.interceptor.security.LoginInterceptor;
import com.xusm.forum.dao.ArticleDao;
import com.xusm.forum.entity.Article;
import com.xusm.forum.service.ArticleService;
import com.xusm.forum.service.CommentService;
import com.xusm.forum.service.UserDetailService;
import com.xusm.forum.service.WebsiteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Rankervice rankervice;

    @Autowired
    private WebsiteService websiteService;

    @Override
    public PageResult queryArticleList(Integer type, boolean ishot, Integer page, Integer row, String key, String year) {
        if(type == ArticleType.ARTICLE_SM){
            this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_SM);
        }else if(type == ArticleType.ARTICLE_JS){
            this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_JS);
        }


        if(StringUtils.isBlank(year)){
            String nowYear = new SimpleDateFormat("yyyy").format(new Date());
            year = nowYear;
        }

//        if (type == ArticleType.ARTICLE_SM && !ishot && page == 1 && row == 7 && key == null && year.equals(nowYear)) {
//            String pageJson = redisTemplate.opsForValue().get(ArticleRedisKey.INDEX_PAGE_KEY);
//            if (StringUtils.isNotBlank(pageJson)) {
//                PageResult pageResult = JSON.parseObject(pageJson, PageResult.class);
//                return pageResult;
//            }
//
//        }

        PageHelper.startPage(page,row);
        List<Article> articles = this.articleDao.selectArticleList(type,ishot,key,year);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        List<Article> art = pageInfo.getList();
        List<ArticleListResponse> articleDtos = art.stream().map(l->{
            ArticleListResponse dto = new ArticleListResponse();
            dto.setIstop(l.getSort() > 0 ? true : false);
            dto.setAuthorName(l.getUserDetail().getUsername());
            dto.setAuthorHead(l.getUserDetail().getHead());
            dto.setClick(l.getClick());
            dto.setCommentCount(l.getCommentCount());
            dto.setCreate(l.getCreated());
            dto.setAlike(l.getAlike());
            dto.setId(l.getId());
            dto.setTitle(l.getTitle());
            return dto;
        }).collect(Collectors.toList());

        Map<String,Object> map = new HashMap<>();
        map.put("today",this.articleDao.queryTodyCount(type));
        map.put("yesterday",this.articleDao.queryYesterdayCount(type));
        map.put("years",this.articleDao.queryYears(type));
        map.put("total", (int) pageInfo.getTotal());

        PageResult pageResult = new PageResult(pageInfo.getPages(), articleDtos, map);

//        if (type == ArticleType.ARTICLE_SM && !ishot && page == 1 && row == 7 && key == null && year.equals(nowYear)) {
//            this.redisTemplate.opsForValue().set(ArticleRedisKey.INDEX_PAGE_KEY, JSON.toJSONString(pageResult));
//        }

        return pageResult;
    }


    @Override
    public List<RankListResponse> queryRankList(Integer date) {

        List<Long> rankList;
        if (date == DateType.TODAY) {
            rankList = rankervice.queryTodayRankList();
        } else if (date == DateType.WEEK){
            rankList = rankervice.queryWeekRankList();
        } else {
            rankList = rankervice.queryMonthRankList();
        }

        List<RankListResponse> result = rankList.stream().map(r -> {
            RankListResponse rankListResponseDto = new RankListResponse();
            rankListResponseDto.setId(r);
            rankListResponseDto.setTitle(queryArticleTitle(r));
            return rankListResponseDto;
        }).collect(Collectors.toList());

        return result;
    }

    private String queryArticleTitle(Long id){
        return this.articleDao.selectByPrimaryKey(id).getTitle();
    }

    @Override
    @Transactional
    public void post(ArticleRequest articleRequest) {
        if(StringUtils.isBlank(articleRequest.getContent())){
            return;
        }
        Article article = new Article();
        article.setType(articleRequest.getType());
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setAuthor(LoginInterceptor.getUserId());
        article.setCreated(new Date());
        article.setImages(articleRequest.getImages());

        this.articleDao.insertSelective(article);

//        if (articleRequest.getType() == ArticleType.ARTICLE_SM) {
//            this.redisTemplate.delete(ArticleRedisKey.INDEX_PAGE_KEY);
//        }
    }

    @Override
    public ArticleResponse queryArticle(Long id) {

        Article article = this.articleDao.selectByPrimaryKey(id);
        Long clickCount;

        String clickCountKey = ArticleRedisKey.COUNT_PREFIX_KEY + id;
        if(!redisTemplate.hasKey(clickCountKey)){
            clickCount = article.getClick();
            redisTemplate.opsForValue().set(clickCountKey, String.valueOf(clickCount));
        }else{
            clickCount = Long.parseLong(redisTemplate.opsForValue().get(clickCountKey));
            clickCount++;
            redisTemplate.opsForValue().set(clickCountKey,clickCount.toString());
        }

        rankervice.increby(id);

        ArticleResponse response = new ArticleResponse();
        response.setArtId(article.getId());
        response.setCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(article.getCreated()));
        response.setClick(clickCount);// 从 redis 中取值放入
        response.setCommentCount(article.getCommentCount());
        response.setContent(article.getContent());
        response.setAlike(article.getAlike());
        response.setImages(article.getImages());
        response.setTitle(article.getTitle());
        response.setAuthorName((String) this.userDetailService.queryUserInfo(article.getAuthor()).get("username"));
        response.setAuthorHead((String) this.userDetailService.queryUserInfo(article.getAuthor()).get("head"));
        response.setComments(commentService.queryCommnetLists(id));

        return response;
    }
}
