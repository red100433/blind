package com.nhn.blind.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Service에서 Async처리 할 Thread Pool 설정
 * @author daeyun.jang
 *
 */
@Configuration
public class AppConfig {

	@Bean
	public ThreadPoolTaskExecutor myBoardThreadPool() {
		ThreadPoolTaskExecutor tp = new ThreadPoolTaskExecutor();
		tp.setCorePoolSize(100);
		tp.setQueueCapacity(100);
		tp.setMaxPoolSize(200);
		tp.setThreadNamePrefix("BoardThread Pool-");
		tp.initialize();
		return tp;
	}

	@Bean
	public ThreadPoolTaskExecutor myCommentThreadPool() {
		ThreadPoolTaskExecutor tp = new ThreadPoolTaskExecutor();
		tp.setCorePoolSize(100);
		tp.setQueueCapacity(100);
		tp.setMaxPoolSize(200);
		tp.setThreadNamePrefix("CommentThread Pool-");
		tp.initialize();
		return tp;
	}

}
