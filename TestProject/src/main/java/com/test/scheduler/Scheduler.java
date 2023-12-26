package com.test.scheduler;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.service.CrawlingService;
import com.test.service.WeatherService;

@Component
public class Scheduler {

	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	@Autowired
	CrawlingService crawlingService;
	
	@Autowired
	WeatherService weatherService;
	
	// 15:35 stock 조회
	//@Scheduled(cron = "0 35 15 * * *")
	public void insertStockList() throws Exception {
		logger.info("#########stockList scheduler start");
		crawlingService.startStockCrawl();
		crawlingService.insertTodayTop();
		logger.info("#########stockList scheduler finish");
	}

	//@Scheduled(cron = "0 0 10 * * *")
	public void fineDustInsert() throws Exception {
		logger.info("#########fineDust scheduler start");
		weatherService.fineDustInsert();
		logger.info("#########fineDust scheduler finish");
	}
	
}
