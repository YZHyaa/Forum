package com.xusm.forum.m1;

import com.xusm.forum.ForumApplication;
import com.xusm.forum.dao.ArticleDao;
import com.xusm.forum.entity.Article;
import com.xusm.forum.service.UserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForumApplication.class)
@WebAppConfiguration
public class ArticleMapper {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDetailService userDetailService;

    @Test
    public void m1(){
        for(int i=1;i<100;i++){
            Article article = new Article();
            article.setType(1);
            article.setTitle("数模"+i+"篇-"+i);
            article.setContent("这是数模第"+i+"篇。全球排名第"+i+"名的数模论文！！！");
            article.setAuthor(Long.valueOf(i%6)+25);
            article.setImages("http://39.105.136.112/image/20191114121201.thumb.700_0.jpeg");
            article.setCreated(new Date());
            article.setClick(Long.valueOf(i));

            this.articleDao.insertSelective(article);
            this.articleDao.updateSort(article.getId());
        }
    }
}
