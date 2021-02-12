package com.xusm.forum.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail articleClickTask(){
        return JobBuilder.newJob(ArticleClickTask.class).withIdentity("articleClickTask").storeDurably().build();
    }

    @Bean
    public Trigger articleClickTaskTrigger(){
        return TriggerBuilder.newTrigger().forJob(articleClickTask())
                .withIdentity("articleClickTask")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 7-23 * * ?"))
                .build();
    }

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

}
