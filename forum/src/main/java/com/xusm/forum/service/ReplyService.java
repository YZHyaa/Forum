package com.xusm.forum.service;

import com.xusm.forum.vo.ReplyRequest;
import com.xusm.forum.entity.Reply;

import java.util.List;

public interface ReplyService {
    Boolean insertReply(ReplyRequest replyRequest);

    List<Reply> queryReplyByCommentId(Long id);
}
