package com.nhn.blind;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * 부하테스트 100개 Thread를 만들어서 한번에 요청
 * 
 * @author daeyun.jang
 *
 */
@Slf4j
public class LoadTest {
	static AtomicInteger counter = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		ExecutorService es = Executors.newFixedThreadPool(100);

		RestTemplate rt = new RestTemplate();
		String url = "http://localhost:8080/view/30";

		StopWatch main = new StopWatch();
		main.start();

		CyclicBarrier barrier = new CyclicBarrier(101);

		for (int i = 0; i < 100; i++) {
			es.submit(() -> {
				int idx = counter.addAndGet(1);

				barrier.await();

				log.info("Thread : {}", idx);

				StopWatch sw = new StopWatch();
				sw.start();

				String res = rt.getForObject(url, String.class, idx);

				sw.stop();
				log.info("Elapsed : {} {} / {}", idx, sw.getTotalTimeSeconds(), res);

				return null; // callable interface는 return이 있고 예외처리 선언이 가능
			});
		}

		barrier.await();

		es.shutdown();
		es.awaitTermination(100, TimeUnit.SECONDS);

		main.stop();
		log.info("Total : {}", main.getTotalTimeSeconds());
	}
}