package com.nhn.blind.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
//@EnableWebFlux
public class AppConfig {
	
	/**
	 * Service에서 Async처리 할 Thread 생성
	 */
//	@Bean
//	public ThreadPoolTaskExecutor myThreadPool() {
//		ThreadPoolTaskExecutor tp = new ThreadPoolTaskExecutor();
//		tp.setCorePoolSize(100);
//		tp.setQueueCapacity(100);
//		tp.setMaxPoolSize(200);
//		tp.initialize();
//		return tp;
//	}
}
