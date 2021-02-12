package com.xusm.forum.service.Impl;

import com.xusm.forum.constant.WebsiteRedisKey;
import com.xusm.forum.config.interceptor.security.LoginInterceptor;
import com.xusm.forum.dao.CommentDao;
import com.xusm.forum.dao.ReplyDao;
import com.xusm.forum.dao.UserDetailDao;
import com.xusm.forum.dao.UserInfoDao;
import com.xusm.forum.entity.UserDetail;
import com.xusm.forum.service.UserDetailService;
import com.xusm.forum.service.WebsiteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private WebsiteService websiteService;

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private CommentDao commentDao;

    @Override
    public UserDetail queryUserDetail() {

        this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_INFO);

        return this.userDetailDao.selectByPrimaryKey(LoginInterceptor.getUserId());
    }

    @Override
    @Transactional
    public Boolean updateUserDetail(UserDetail userDetail) {
        if(StringUtils.isBlank(userDetail.getUsername()) || StringUtils.isNotBlank(userDetail.getPhone())){
            return false;
        }

        Long uId = LoginInterceptor.getUserId();
        UserDetail userDetail1 = this.userDetailDao.selectByPrimaryKey(uId);

        if(StringUtils.isBlank(userDetail.getHead())){
            userDetail.setHead(userDetail1.getHead());
        }

        String username = userDetail.getUsername();
        if(!StringUtils.equals(username,userDetail1.getUsername())){
            this.replyDao.updateRepauthorName(uId,username);
            this.replyDao.updateToRepauthorName(uId,username);
            this.commentDao.updateAuthorName(uId,username);
        }

        String phone = this.userInfoDao.selectByPrimaryKey(uId).getPhone();
        userDetail.setUserId(uId);
        userDetail.setPhone(phone);
        this.userDetailDao.updateByPrimaryKey(userDetail);

        return true;
    }

    @Override
    public String selectUsernameById(Long id) {

        return this.userDetailDao.selectByPrimaryKey(id).getUsername();
    }

    @Override
    public Map<String, Object> queryUserInfo(Long id) {
        Map<String,Object> infoMap = new HashMap<>();
        UserDetail userDetail = this.userDetailDao.selectByPrimaryKey(id);
        infoMap.put("username",userDetail.getUsername());
        infoMap.put("head",userDetail.getHead());
        return infoMap;
    }

}
