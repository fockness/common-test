package com.example.demo;

import com.example.demo.cases.designpattern.strategy.bestpractices.StrategyServiceFactory;
import com.example.demo.cases.designpattern.strategy.bestpractices.StrategyType;
import com.example.demo.cases.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private StrategyServiceFactory strategyServiceFactory;

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testStrategy() {
		strategyServiceFactory.getMessageService(StrategyType.SMS).sendMessage("Hello world");
	}

	@Test
	public void testRedis(){
		redisUtil.set("aa", "aaa");
		System.out.println(redisUtil.get("aa"));
	}
}
