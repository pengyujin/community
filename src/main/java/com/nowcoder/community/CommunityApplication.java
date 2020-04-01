package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommunityApplication {
		
	// 管理bean的生命周期，初始化方法
	@PostConstruct
	public void init() {
		// 解决netty启动冲突的问题，redis和elasticsearch
		// see Netty4Util.setAvailableProcessors()
		System.setProperty("es.set.netty.runtime.available.processors", "false");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
