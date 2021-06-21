package com.xusm.forum.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    /**
     * 文章点击量
     */
    @Bean
    public JobDetail articleClickTask(){
        return JobBuilder.newJob(ArticleClickTask.class).withIdentity("articleClickTask").storeDurably().build();
    }
    @Bean
    public Trigger articleClickTaskTrigger(){
        return TriggerBuilder.newTrigger().forJob(articleClickTask())
                .withIdentity("articleClickTask")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 59 23 * * ? *"))
                .build();
    }

    /**
     * 热点文章列表
     */
    @Bean
    public JobDetail articleRankTask(){
        return JobBuilder.newJob(ArticleRankTask.class).withIdentity("articleRankTask").storeDurably().build();
    }
    @Bean
    public Trigger articleRankTaskTrigger(){
        return TriggerBuilder.newTrigger().forJob(articleClickTask())
                .withIdentity("articleRankTask")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 59 23 * * ? *"))
                .build();
    }

    /**
     * 资源点击量
     */
    @Bean
    public JobDetail sourceClickTask(){
        return JobBuilder.newJob(SourceClickTask.class).withIdentity("sourceClickTask").storeDurably().build();
    }
    @Bean
    public Trigger sourceClickTaskTrigger(){
        return TriggerBuilder.newTrigger().forJob(sourceClickTask())
                .withIdentity("sourceClickTask")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 7-23 * * ?"))
                .build();
    }


    /**
     * 每日网站统计
     */
    @Bean
    public JobDetail websiteTask(){
        return JobBuilder.newJob(WebsiteTask.class).withIdentity("websiteTask").storeDurably().build();
    }
    @Bean
    public Trigger websiteTaskTrigger(){
        return TriggerBuilder.newTrigger().forJob(websiteTask())
                .withIdentity("websiteTask")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 59 23 * * ? *"))
                .build();
    }

    /**
     * 每小时PV、UV、IP
     */
    @Bean
    public JobDetail websiteByHourTask(){
        return JobBuilder.newJob(WebsiteByHourTask.class).withIdentity("websiteByHourTask").storeDurably().build();
    }
    @Bean
    public Trigger websiteByHourTaskTrigger(){
        return TriggerBuilder.newTrigger().forJob(websiteByHourTask())
                .withIdentity("websiteByHourTask")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 59 23 * * ? *"))
                .build();
    }


//    /**
//     * 点赞用户
//     */
//    @Bean
//    public JobDetail likeAddrTask(){
//        return JobBuilder.newJob(LikeAddrTask.class).withIdentity("likeAddrTask").storeDurably().build();
//    }
//
//    @Bean
//    public Trigger likeAddrTaskTrigger(){
//        return TriggerBuilder.newTrigger().forJob(likeAddrTask())
//                .withIdentity("likeAddrTask")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 59 23 * * ? *"))
//                .build();
//    }

}
