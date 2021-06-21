package com.xusm.forum.controller;

import com.xusm.forum.vo.ArticleResponse;
import com.xusm.forum.vo.PageResult;
import com.xusm.forum.vo.ArticleRequest;
import com.xusm.forum.vo.RankListResponse;
import com.xusm.forum.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/list")
    public ResponseEntity<PageResult> queryArticleList(
            @RequestParam(value = "type",defaultValue = "1") Integer type,
            @RequestParam(value = "ishot",defaultValue = "false")Boolean ishot,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "row",defaultValue = "7")Integer row,
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "year",required = false)String year
    ){
        PageResult result = this.articleService.queryArticleList(type,ishot,page,row,key,year);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/ranklist")
    public ResponseEntity<List<RankListResponse>> queryRankList(@RequestParam(name = "date",defaultValue = "1")Integer date){
        return ResponseEntity.ok(this.articleService.queryRankList(date));
    }

    @PostMapping("/post")
    public ResponseEntity<Void> post(@RequestBody ArticleRequest articleRequest){
        this.articleService.post(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> queryArticle(@PathVariable("id")Long id){
        return ResponseEntity.ok(this.articleService.queryArticle(id));
    }
}
