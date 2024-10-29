package com.topkey.schedule;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@SpringBootTest
class AppTests {

	@Test
	void contextLoads() {

	}

	public static void main(String[] args) throws InterruptedException {
		Flux<Integer> flux = Flux.range(1, 5).publishOn(Schedulers.parallel()).map(i -> {
			System.out.println("Mapping on thread: " + Thread.currentThread().getName());
			return i * 2;
		}).publishOn(Schedulers.parallel()).filter(i -> {
			System.out.println("Filtering on thread: " + Thread.currentThread().getName());
			return i % 2 == 0;
		}).publishOn(Schedulers.parallel());

		flux.subscribe(i -> System.out.println("Received " + i + " on thread: " + Thread.currentThread().getName()));

// 添加等待機制以確保程序執行完成
		Thread.sleep(1000);
	}

}
