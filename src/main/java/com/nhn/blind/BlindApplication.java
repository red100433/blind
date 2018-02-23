package com.nhn.blind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author daeyun.jang
 *
 */
@SpringBootApplication
public class BlindApplication {

	/**
	 * netty worker Thread를 1개로 만들고 테스트 진행(부하 테스트 100개 요청할 때, 동기적으로 타는지 테스트)
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BlindApplication.class, args);
	}
}
