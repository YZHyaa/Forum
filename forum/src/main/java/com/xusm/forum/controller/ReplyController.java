package com.xusm.forum.controller;

import com.xusm.forum.vo.ReplyRequest;
import com.xusm.forum.entity.Reply;
import com.xusm.forum.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping("{commentId}")
    public ResponseEntity<List<Reply>> queryReplyByCommentId(@PathVariable("commentId") Long id){
        List<Reply> replies = this.replyService.queryReplyByCommentId(id);
        return ResponseEntity.ok(replies);
    }

    @PostMapping
    public ResponseEntity<Void> insertReply(@RequestBody ReplyRequest replyRequest){
        if(this.replyService.insertReply(replyRequest)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }
}
