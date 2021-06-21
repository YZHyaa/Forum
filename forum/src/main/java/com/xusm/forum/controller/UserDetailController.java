package com.xusm.forum.controller;

import com.xusm.forum.entity.UserDetail;
import com.xusm.forum.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/detail")
    public ResponseEntity<UserDetail> queryUserDetail(){
        return ResponseEntity.ok(this.userDetailService.queryUserDetail());
    }

    @PutMapping("/detail")
    public ResponseEntity<Void> updateUserDetail(@RequestBody UserDetail userDetail){
        if(this.userDetailService.updateUserDetail(userDetail)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
