package com.xusm.forum.service.Impl;

import com.xusm.forum.constant.redis.WebsiteRedisKey;
import com.xusm.forum.config.interceptor.info.UvInterceptor;
import com.xusm.forum.config.prop.JwtProperties;
import com.xusm.forum.dao.UserDetailDao;
import com.xusm.forum.dao.UserInfoDao;
import com.xusm.forum.entity.UserDetail;
import com.xusm.forum.entity.UserInfo;
import com.xusm.forum.service.CodeService;
import com.xusm.forum.service.UserService;
import com.xusm.forum.service.WebsiteService;
import com.xusm.forum.utils.CodecUtils;
import com.xusm.forum.utils.JwtUtils;
import com.xusm.forum.vo.UserRegisterRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Resource
    private JwtProperties jwtProperties;

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private CodeService codeService;

    @Autowired
    private WebsiteService websiteService;

    @Override
    public Boolean checkUserPhone(String phone) {

        // this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_LOGIN);

        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(phone);
        return this.userInfoDao.select(userInfo).size() == 0;
    }

    @Override
    @Transactional
    public Boolean register(UserRegisterRequest request) {

        this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_REGISTER);

        String phone = request.getPhone();
        String code = request.getCode();
        String password = request.getPassword();

        if(!this.codeService.checkCacheCode(phone,code)){
            return false;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setCreated(new Date());
        String salt = CodecUtils.generateSalt();
        userInfo.setSalt(salt);
        userInfo.setPassword(CodecUtils.md5Hex(password,salt));
        userInfo.setPhone(phone);
        this.userInfoDao.insertSelective(userInfo);

        UserDetail userDetail = new UserDetail();
        userDetail.setUsername("xusm_"+ StringUtils.substring(UvInterceptor.getVisitId(),0,8));
        userDetail.setUserId(userInfo.getId());
        userDetail.setPhone(phone);
        this.userDetailDao.insertSelective(userDetail);

        return true;
    }

    @Override
    public String generateToken(String phone) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userInfoDao.selectOne(new UserInfo(phone)).getId());

        try {
            String token = JwtUtils.generateToken(userInfo,jwtProperties.getPrivateKey(),this.jwtProperties.getExpire()*60);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String accredit(String phone, String password) {

        this.websiteService.insertVisit(WebsiteRedisKey.PREFIX_KEY_LOGIN);

        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(phone);
        UserInfo info = this.userInfoDao.selectOne(userInfo);
        if(info == null || !StringUtils.equals(info.getPassword(),CodecUtils.md5Hex(password,info.getSalt()))){
            return null;
        }
        String token = null;
        try {
            token = JwtUtils.generateToken(new UserInfo(info.getId()),jwtProperties.getPrivateKey(),jwtProperties.getExpire()*60);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void reset(String phone, String password) {
        UserInfo userInfo = this.userInfoDao.selectOne(new UserInfo(phone));
        userInfo.setPassword(CodecUtils.md5Hex(password,userInfo.getSalt()));
        this.userInfoDao.updateByPrimaryKey(userInfo);
    }
}
