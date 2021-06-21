package com.xusm.forum.controller;

import com.xusm.forum.vo.LikeRequest;
import com.xusm.forum.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PutMapping("/like")
    public ResponseEntity<Void> like(@RequestBody LikeRequest likeRequest){
        if(this.likeService.like(likeRequest)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
