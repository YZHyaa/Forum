package com.xusm.forum.controller;

import com.xusm.forum.vo.SourceResponse;
import com.xusm.forum.vo.SourceResult;
import com.xusm.forum.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("source")
public class SourceController {

    @Autowired
    private SourceService sourceService;

//    @GetMapping("list")
//    public ResponseEntity<SourcePageResult<SourceResponse>> querySourceList(
//            @RequestParam(value = "page",defaultValue = "1") Integer page,
//            @RequestParam(value = "row",defaultValue = "7") Integer row
//            ){
//        SourcePageResult<SourceResponse> sourcePageResult = this.sourceService.querySourceList(page,row);
//        if(CollectionUtils.isEmpty(sourcePageResult.getSource())){
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(sourcePageResult);
//    }

    @GetMapping("list")
    public ResponseEntity<SourceResult<SourceResponse>> querySourceList(){
        SourceResult<SourceResponse> sourceDtoSourceResult = this.sourceService.querySourceList();
        if(CollectionUtils.isEmpty(sourceDtoSourceResult.getSource())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sourceDtoSourceResult);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<Map<String,Object>> querySourceDetail(@PathVariable("id") Long id){
        if(this.sourceService.selectPay(id)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(this.sourceService.querySourceDetail(id));
    }
 }
