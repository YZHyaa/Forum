package com.xusm.forum.service.Impl;

import com.xusm.forum.vo.SourceResponse;
import com.xusm.forum.vo.SourceResult;
import com.xusm.forum.constant.WebsiteRedisKey;
import com.xusm.forum.dao.SourceDao;
import com.xusm.forum.dao.SourceDetailDao;
import com.xusm.forum.entity.Source;
import com.xusm.forum.entity.SourceDetail;
import com.xusm.forum.service.SourceService;
import com.xusm.forum.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceDao sourceDao;

    @Autowired
    private SourceDetailDao sourceDetailDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WebsiteService websiteService;

    private static final String PREFIX_KEY = "source:id:";

//    @Override
//    public SourcePageResult<SourceResponse> querySourceList(Integer page, Integer row) {
//        this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_SOURCE);
//
//        PageHelper.startPage(page,row);
//        List<Source> sources = this.sourceDao.selectAll();
//        PageInfo<Source> sourcePageInfo = new PageInfo<>(sources);
//        List<Source> list = sourcePageInfo.getList();
//        List<SourceResponse> sourceDtos = list.stream().map(l->{
//            SourceResponse dto = new SourceResponse();
//            dto.setAuthorName(l.getAuthorName());
//            dto.setSourceClick(l.getSourceClick());
//            dto.setSourceCreate(l.getSourceCreate());
//            dto.setSourceId(l.getSourceId());
//            dto.setSourceTitle(l.getSourceTitle());
//            return dto;
//        }).collect(Collectors.toList());
//
//        return new SourcePageResult<SourceResponse>((int) sourcePageInfo.getTotal(),sourcePageInfo.getPages(),sourceDtos);
//    }

    @Override
    public SourceResult<SourceResponse> querySourceList() {
        this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_SOURCE);

        List<Source> sources = this.sourceDao.selectAll();
        int count = this.sourceDao.selectCount(null);
        List<SourceResponse> sourceResponses = sources.stream().map(l->{
            SourceResponse dto = new SourceResponse();
            dto.setAuthorName(l.getAuthorName());
            dto.setSourceClick(l.getSourceClick());
            dto.setSourceCreate(l.getSourceCreate());
            dto.setSourceId(l.getSourceId());
            dto.setSourceTitle(l.getSourceTitle());
            return dto;
        }).collect(Collectors.toList());

        return new SourceResult<>(count, sourceResponses);
    }

    @Override
    public Map<String,Object> querySourceDetail(Long id) {
        String key = PREFIX_KEY + id;
        if(!redisTemplate.hasKey(key)){
            this.redisTemplate.opsForValue().set(key,"1");
        }else {
            Integer count = Integer.parseInt(this.redisTemplate.opsForValue().get(key));
            count++;
            this.redisTemplate.opsForValue().set(key,count.toString());
        }

        SourceDetail sourceDetail = new SourceDetail();
        sourceDetail.setSourceId(id);
        SourceDetail detail = this.sourceDetailDao.selectOne(sourceDetail);
        Source source = sourceDao.selectByPrimaryKey(id);

        Map<String,Object> map = new HashMap<>();
        map.put("baiduyun",detail.getBaiduyun());
        map.put("code",detail.getCode());
        map.put("github",detail.getGithub());
        map.put("authorName",source.getAuthorName());
        map.put("sourceTitle",source.getSourceTitle());
        map.put("sourceCreate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(source.getSourceCreate()));

        return map;
    }

    @Override
    public void updateSourceClick(Long id,Integer count) {
        this.sourceDao.updateClick(id,count);
    }

    @Override
    public Boolean selectPay(Long id) {
        return this.sourceDao.selectByPrimaryKey(id).getPay();
    }
}
