package com.xusm.forum.service.Impl;

import com.xusm.forum.constant.CommnetType;
import com.xusm.forum.constant.FunctionType;
import com.xusm.forum.config.interceptor.security.CommentRoleInterceptor;
import com.xusm.forum.config.interceptor.info.UvInterceptor;
import com.xusm.forum.dao.ArticleDao;
import com.xusm.forum.dao.CommentDao;
import com.xusm.forum.dao.FunctionDao;
import com.xusm.forum.vo.CommentRequest;
import com.xusm.forum.entity.Comment;
import com.xusm.forum.entity.Function;
import com.xusm.forum.service.CommentService;
import com.xusm.forum.service.UserDetailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private FunctionDao functionDao;

    @Override
    @Transactional
    public Boolean insertComment(CommentRequest commentRequest) {
            if(StringUtils.isBlank(commentRequest.getContent())){
                return false;
            }

            // 创建评论
            Comment comment = new Comment();
            comment.setArtId(commentRequest.getArticleId());
            // 根据设置的评论权限，判断当前用户能否评论
            Function fun = new Function();
            fun.setFunName("评论权限");
            // 获取到 UserId
            Long uId = CommentRoleInterceptor.getUserId();
            // 如果是所有人都能评论 && 当前用户未登录
            if(this.functionDao.selectOne(fun).getFunState() == FunctionType.COMMENT_FOR_ALL && uId == null){
                // 将评论的用户设置为游客
                comment.setComAuthor(Long.valueOf(000));
                comment.setComAuthorName("游客_"+ StringUtils.substring(UvInterceptor.getVisitId(),0,8));
            // 只有登录后才能评论
            }else{
                comment.setComAuthor(uId);
                comment.setComAuthorName(this.userDetailService.selectUsernameById(uId));
            }
            comment.setComContent(commentRequest.getContent());
            comment.setComCreate(new Date());

            // 将评论添加到数据库
            this.commentDao.insertSelective(comment);
            // commentCount++
            this.articleDao.updateCommentCount(commentRequest.getArticleId());

            return true;
    }

    @Override
    public List<Comment> queryCommnetLists(Long articleId) {
//        Example example = new Example(Comment.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("artId",articleId);
//        criteria.andEqualTo("comState", CommnetType.Ok);
//        example.setOrderByClause("com_create"+" "+"desc");

        return this.commentDao.queryCommnentList(articleId, CommnetType.Ok);
    }

}
