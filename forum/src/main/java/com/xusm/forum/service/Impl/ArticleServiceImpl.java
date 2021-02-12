package com.xusm.forum.service.Impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xusm.forum.constant.ArticleRedisKey;
import com.xusm.forum.constant.ArticleType;
import com.xusm.forum.constant.WebsiteRedisKey;
import com.xusm.forum.vo.PageResult;
import com.xusm.forum.config.interceptor.security.LoginInterceptor;
import com.xusm.forum.dao.ArticleDao;
import com.xusm.forum.dao.ArticleRankDao;
import com.xusm.forum.dao.FunctionDao;
import com.xusm.forum.vo.ArticleRequest;
import com.xusm.forum.vo.ArticleListResponse;
import com.xusm.forum.vo.RankListResponse;
import com.xusm.forum.entity.Article;
import com.xusm.forum.entity.Function;
import com.xusm.forum.service.ArticleService;
import com.xusm.forum.service.CommentService;
import com.xusm.forum.service.UserDetailService;
import com.xusm.forum.service.WebsiteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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
    private ArticleRankDao articleRankDao;

    @Autowired
    private WebsiteService websiteService;

    @Autowired
    private FunctionDao functionDao;

    @Override
    public PageResult queryArticleList(Integer type, boolean ishot, Integer page, Integer row, String key, String year) {
        if(type == ArticleType.ARTICLE_SM){
            this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_SM);
        }else if(type == ArticleType.ARTICLE_JS){
            this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_JS);
        }


        String nowYear = new SimpleDateFormat("yyyy").format(new Date());
        if(StringUtils.isBlank(year)){
            year = nowYear;
        }

        if (type == ArticleType.ARTICLE_SM && !ishot && page == 1 && row == 7 && key == null && year.equals(nowYear)) {
            String pageJson = redisTemplate.opsForValue().get(ArticleRedisKey.INDEX_PAGE_KEY);
            if (StringUtils.isNotBlank(pageJson)) {
                PageResult pageResult = JSON.parseObject(pageJson, PageResult.class);
                return pageResult;
            }

        }

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

        if (type == ArticleType.ARTICLE_SM && !ishot && page == 1 && row == 7 && key == null && year.equals(nowYear)) {
            this.redisTemplate.opsForValue().set(ArticleRedisKey.INDEX_PAGE_KEY, JSON.toJSONString(pageResult));
        }

        return pageResult;
    }

    @Override
    public List<RankListResponse> queryRankList(Integer date) {
        List<RankListResponse> dtoList = null;
        Function function = new Function();
        function.setFunName("热搜标准");
        Integer rankRule = this.functionDao.selectOne(function).getFunState();
        switch (date){
            case 1 :
                dtoList = this.articleDao.queryTodayRankList(rankRule);
                break;
            case 2:
                dtoList = this.articleDao.queryLastRankList(-7,rankRule);
                break;
            case 3:
                dtoList = this.articleDao.queryLastRankList(-30,rankRule);
        }

        ArrayList<RankListResponse> sizeList = new ArrayList<>();
        if(dtoList.size()>5){
            for(int i=0;i<5;i++){
                sizeList.add(dtoList.get(i));
            }
        }else if(dtoList.size() < 5){
            List<Long> rankList = this.articleRankDao.queryRankList(date, 5 - dtoList.size());
            for(int i=0;i<dtoList.size();i++){
                sizeList.add(dtoList.get(i));
            }
            List<RankListResponse> plus = rankList.stream().map(r -> {
                RankListResponse rankListResponseDto = new RankListResponse();
                rankListResponseDto.setId(r);
                rankListResponseDto.setTitle(queryArticleTitle(r));
                return rankListResponseDto;
            }).collect(Collectors.toList());
            sizeList.addAll(plus);
        }else {
            for(int i=0;i<5;i++){
                sizeList.add(dtoList.get(i));
            }
        }

        return sizeList;
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

        if (articleRequest.getType() == ArticleType.ARTICLE_SM) {
            this.redisTemplate.delete(ArticleRedisKey.INDEX_PAGE_KEY);
        }
    }

    @Override
    public Map<String, Object> queryArticle(Long id) {

        String key = ArticleRedisKey.COUNT_PREFIX_KEY + id;

        if(!redisTemplate.hasKey(key)){
            redisTemplate.opsForValue().set(key,"1");
        }else{
            Integer count= Integer.parseInt(redisTemplate.opsForValue().get(key));
            count++;
            redisTemplate.opsForValue().set(key,count.toString());
        }

        Map<String,Object> map = new HashMap<>();
        Article article = this.articleDao.selectByPrimaryKey(id);
        map.put("artId",article.getId());
        map.put("create",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(article.getCreated()));
        map.put("click",article.getClick());
        map.put("commentCount",article.getCommentCount());
        map.put("content",article.getContent());
        map.put("alike",article.getAlike());
        map.put("images",article.getImages());
        map.put("title",article.getTitle());
        map.put("authorName",this.userDetailService.queryUserInfo(article.getAuthor()).get("username"));
        map.put("authorHead",this.userDetailService.queryUserInfo(article.getAuthor()).get("head"));
        map.put("comments",commentService.queryCommnetLists(id));

        return map;
    }
}
