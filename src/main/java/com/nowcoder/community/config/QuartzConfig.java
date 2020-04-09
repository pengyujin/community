package com.nowcoder.community.config;

import com.nowcoder.community.quartz.AlphaJob;
import com.nowcoder.community.quartz.PostScoreRefreshJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

// 配置 -> 数据库 -> 调用，只用一次，以后去数据库找数据
@Configuration
public class QuartzConfig {
    
    // BeanFactory 容器的顶层接口
    // FactoryBean 可简化Bean的实例化过程：
    // 1. 通过FactoryBean封装了Bean的实例化过程
    // 2.将FactoryBean装配到Spring容器里
    // 3.将FactoryBean注入给其他的Bean
    // 4. 该Bean得到的是FactoryBean所管理的对象实例
    
    // 配置JobDetail
//    @Bean
    public JobDetailFactoryBean alphaJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(AlphaJob.class);
        factoryBean.setName("alphaJob");
        factoryBean.setGroup("alphaJobGroup");
        factoryBean.setDurability(true);// 任务是否持久保存
        factoryBean.setRequestsRecovery(true);// 任务是否可恢复
        return factoryBean;
    }
    
    
    // 配置Trigger(SimpleTriggerFactoryBean, CronTriggerFactoryBean 复杂的Trigger)
//    @Bean
    public SimpleTriggerFactoryBean alphaTrigger(JobDetail alphaJobDetail) {// 通过名字来匹配不同的JobDetail，上一个方法
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setJobDetail(alphaJobDetail);
        simpleTriggerFactoryBean.setName("alphaTrigger");
        simpleTriggerFactoryBean.setGroup("alphaTriggerGroup");
        simpleTriggerFactoryBean.setRepeatInterval(3000); //任务执行的频率
        simpleTriggerFactoryBean.setJobDataMap(new JobDataMap());// 指定对象给Trigger底层存储任务的状态
        return simpleTriggerFactoryBean;
    }
    
    // 刷新帖子分数的定时任务
    @Bean
    public JobDetailFactoryBean postScoreRefreshJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(PostScoreRefreshJob.class);
        factoryBean.setName("postScoreRefreshJob");
        factoryBean.setGroup("communityJobGroup");
        factoryBean.setDurability(true);// 任务是否持久保存
        factoryBean.setRequestsRecovery(true);// 任务是否可恢复
        return factoryBean;
    }


    // 配置Trigger(SimpleTriggerFactoryBean, CronTriggerFactoryBean 复杂的Trigger)
    @Bean
    public SimpleTriggerFactoryBean postScoreRefreshJobTrigger(JobDetail postScoreRefreshJobDetail) {// 通过名字来匹配不同的JobDetail，上一个方法
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setJobDetail(postScoreRefreshJobDetail);
        simpleTriggerFactoryBean.setName("postScoreRefreshTrigger");
        simpleTriggerFactoryBean.setGroup("communityTriggerGroup");
        simpleTriggerFactoryBean.setRepeatInterval(1000 * 60 * 5); //任务执行的频率,5分钟刷新一次，便于测试
        simpleTriggerFactoryBean.setJobDataMap(new JobDataMap());// 指定对象给Trigger底层存储任务的状态
        return simpleTriggerFactoryBean;
    }
}
