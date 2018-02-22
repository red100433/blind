package com.nhn.blind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlindApplication {

	/**
	 * netty worker Thread를 1개로 만들고 테스트 진행(부하 테스트 100개 요청할 때, 동기적으로 타는지 테스트)
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("reactor.ipc.netty.workerCount", "1");
		System.setProperty("reactor.ipc.netty.pool.maxConnections", "2000");
		SpringApplication.run(BlindApplication.class, args);
	}
}
