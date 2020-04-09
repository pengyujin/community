package com.nowcoder.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

// 测试spring线程池的配置类
@Configuration
@EnableScheduling
@EnableAsync
public class ThreadPoolConfig {
    
}
