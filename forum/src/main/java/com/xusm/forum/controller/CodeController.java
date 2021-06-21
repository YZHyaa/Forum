package com.xusm.forum.controller;

import com.xusm.forum.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendCode(@RequestParam("phone") String phone){
        if(codeService.sendCode(phone)){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/check")
    public ResponseEntity<Void> check(@RequestParam("phone")String phone,@RequestParam("code")String code){
        if(!this.codeService.checkCacheCode(phone,code)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
