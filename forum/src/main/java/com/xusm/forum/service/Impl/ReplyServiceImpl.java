package com.xusm.forum.service.Impl;

import com.xusm.forum.constant.CommnetType;
import com.xusm.forum.constant.FunctionType;
import com.xusm.forum.constant.ReplyType;
import com.xusm.forum.config.interceptor.security.CommentRoleInterceptor;
import com.xusm.forum.config.interceptor.info.UvInterceptor;
import com.xusm.forum.dao.ArticleDao;
import com.xusm.forum.dao.CommentDao;
import com.xusm.forum.dao.FunctionDao;
import com.xusm.forum.dao.ReplyDao;
import com.xusm.forum.vo.ReplyRequest;
import com.xusm.forum.entity.Function;
import com.xusm.forum.entity.Reply;
import com.xusm.forum.service.ReplyService;
import com.xusm.forum.service.UserDetailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    public List<Reply> queryReplyByCommentId(Long id) {
//        Example example = new Example(Reply.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("commentId",id);
//        criteria.andEqualTo("repState", CommnetType.Ok);
//        example.setOrderByClause("rep_create"+" "+"desc");
//        List<Reply> replies = this.replyDao.selectByExample(example);
        List<Reply> replies = this.replyDao.queryReplyList(id, CommnetType.Ok);

        return replies;
    }

    @Override
    @Transactional
    public Boolean insertReply(ReplyRequest replyRequest) {
        if(StringUtils.isBlank(replyRequest.getContent())){
            return false;
        }

        // 如果是对评论的回复
        if(replyRequest.getType() == ReplyType.TO_COMMENT){
            // 构建 Reply
            Reply reply = new Reply();
            reply.setCommentId(replyRequest.getCommentId());
            reply.setRepContent(replyRequest.getContent());
            Function fun = new Function();
            fun.setFunName("评论权限");
            if(this.functionDao.selectOne(fun).getFunState() == FunctionType.COMMENT_FOR_ALL && CommentRoleInterceptor.getUserId() == null ){
                reply.setRepAuthor(Long.valueOf(00));
                reply.setRepAuthorName("游客_"+ StringUtils.substring(UvInterceptor.getVisitId(),0,8));
            }else{
                reply.setRepAuthor(CommentRoleInterceptor.getUserId());
                reply.setRepAuthorName(this.userDetailService.selectUsernameById(CommentRoleInterceptor.getUserId()));
            }
            reply.setRepCreate(new Date());
            reply.setRepType(ReplyType.TO_COMMENT);

            this.replyDao.insertSelective(reply);

            this.articleDao.updateCommentCount(replyRequest.getArticleId());
            this.commentDao.updateRepCount(replyRequest.getCommentId());
        // 如果是对回复的回复
        }else{
            Reply reply = new Reply();
            reply.setCommentId(replyRequest.getCommentId());
            reply.setRepContent(replyRequest.getContent());
            Function fun = new Function();
            fun.setFunName("评论权限");
            if(this.functionDao.selectOne(fun).getFunState() == FunctionType.COMMENT_FOR_ALL && CommentRoleInterceptor.getUserId() == null ){
                reply.setRepAuthor(Long.valueOf(000));
                reply.setRepAuthorName("游客_"+ StringUtils.substring(UvInterceptor.getVisitId(),0,8));
            }else{
                reply.setRepAuthor(CommentRoleInterceptor.getUserId());
                reply.setRepAuthorName(this.userDetailService.selectUsernameById(CommentRoleInterceptor.getUserId()));
            }
            reply.setRepCreate(new Date());

            reply.setRepType(ReplyType.TO_REPLY);
            reply.setToRepAuthor(replyRequest.getToRepAuthor());
            reply.setToRepAuthorName(replyRequest.getToRepAuthorName());

            this.replyDao.insertSelective(reply);

            this.articleDao.updateCommentCount(replyRequest.getArticleId());
            this.commentDao.updateRepCount(replyRequest.getCommentId());
        }

        return true;
    }
}
