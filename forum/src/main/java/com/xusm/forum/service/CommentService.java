package com.xusm.forum.service;

import com.xusm.forum.vo.CommentRequest;
import com.xusm.forum.entity.Comment;

import java.util.List;

public interface CommentService {

    Boolean insertComment(CommentRequest commentRequest);

    List<Comment> queryCommnetLists(Long articleId);
}
