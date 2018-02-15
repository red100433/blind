package com.nhn.blind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlindApplication {

	public static void main(String[] args) {
		
		System.setProperty("reactor.ipc.netty.workerCount", "1");
		System.setProperty("reactor.ipc.netty.pool.maxConnections", "2000");
		SpringApplication.run(BlindApplication.class, args);
	}
}
