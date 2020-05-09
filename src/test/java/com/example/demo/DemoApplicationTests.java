package com.example.demo;

import com.example.demo.cases.designpattern.strategy.bestpractices.StrategyServiceFactory;
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

	@Test
	public void contextLoads() {
	}

	@Test
	public void testStrategy() {
		//strategyServiceFactory.getMessageService(StrategyType.SMS).sendMessage("Hello world");


	}
}
