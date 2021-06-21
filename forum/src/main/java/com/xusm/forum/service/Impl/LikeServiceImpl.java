package com.xusm.forum.service.Impl;

import com.xusm.forum.constant.redis.LikeRedisKey;
import com.xusm.forum.constant.LikeType;
import com.xusm.forum.config.interceptor.security.LoginInterceptor;
import com.xusm.forum.dao.ArticleDao;
import com.xusm.forum.dao.CommentDao;
import com.xusm.forum.dao.ReplyDao;
import com.xusm.forum.vo.LikeRequest;
import com.xusm.forum.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Transactional
    public Boolean like(LikeRequest likeRequest) {

        Integer type = likeRequest.getType();
        if(type == LikeType.ARTICLE_LIKE ){
            if(doLike(LikeRedisKey.PREFIX_KEY_ARTICLE+ likeRequest.getToId())){
                this.articleDao.updateLike(likeRequest.getToId());
                return true;
            }
        }else if(type == LikeType.COMMENT_LIKE ){
            if(doLike(LikeRedisKey.PREFIX_KEY_COMMENT + likeRequest.getToId())){
                this.commentDao.updateComLike(likeRequest.getToId());
                return true;
            }
        }else {
            if(doLike(LikeRedisKey.PREFIX_KEY_REPLY + likeRequest.getToId())){
                this.replyDao.updateRepLike(likeRequest.getToId());
                return true;
            }
        }

        return false;
    }

    private Boolean doLike(String key){
        if(!this.redisTemplate.hasKey(key)){
            Long userId = LoginInterceptor.getUserId();
            this.redisTemplate.opsForSet().add(key,String.valueOf(userId));
            return true;
        }else {
            if(this.redisTemplate.opsForSet().isMember(key,String.valueOf(LoginInterceptor.getUserId()))){
                return false;
            }else{
                this.redisTemplate.opsForSet().add(key,String.valueOf(LoginInterceptor.getUserId()));
                return true;
            }
        }
    }
}
