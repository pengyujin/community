package com.nowcoder.community.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class AlphaService {

    private static final Logger logger = LoggerFactory.getLogger(AlphaService.class);
    
    public AlphaService() {
        System.out.println("实例化AlphaService");
    }
    
    // spring容器启动的时候会检测到这个类用@Service注解，会执行相应的方法@PostConstruct、@PreDestroy
//    @PostConstruct
    public void init() {
        System.out.println("初始化AlphaService");
    }
    
//    @PreDestroy
    public void destory() {
        System.out.println("销毁AlphaService");
    }
    
    // 测试spring线程池快速使用
//    @Async //让该方法在多线程环境下，被异步的调用，开启这个注解，在ThreadPoolConfig配置类中开启
    public void execute1() {
        logger.debug("execute1");
    }

//    @Scheduled(initialDelay = 10000, fixedRate = 1000)
    public void execute2() {
        logger.debug("execute2");
    }
}
