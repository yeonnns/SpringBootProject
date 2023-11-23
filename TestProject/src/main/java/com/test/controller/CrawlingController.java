package com.test.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.test.dto.StockDto;
import com.test.service.CrawlingService;

@Component
@Controller
@RequestMapping("/crawling")
public class CrawlingController{
	
	@Autowired
	CrawlingService crawlingService;

	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	@RequestMapping(value = "/stockCrawl")
	public ModelAndView stockCrawl(StockDto stockDto) {
		ModelAndView mv = new ModelAndView();
		String latestDate = crawlingService.latestDate();
		if(stockDto.getCreateDate() == null) {
			stockDto.setCreateDate(latestDate);
			mv.addObject("latestDate", latestDate);
		}
		ArrayList<StockDto> stockList = crawlingService.stockList(stockDto);
		if(stockDto.getCreateDate() != null) {
			mv.addObject("latestDate", stockDto.getCreateDate());
		}
		mv.addObject("stockList", stockList);
		mv.setViewName("/crawling/stockCrawling");
		
		return mv;
	}
	
	@RequestMapping(value = "/startStockCrawl")
	public ModelAndView startStockCrawl(RedirectView rv) {
		ModelAndView mv = new ModelAndView();
		crawlingService.startStockCrawl();
		rv.setUrl("/crawling/stockCrawl");
		mv.setView(rv);
		return mv;
	}
	
	//csv
	//@ResponseBody 대신 ResponseEntity 로 한글 꺠짐 방지
	@RequestMapping(value = "/stockCrawlCsvDown")
	public ResponseEntity<String> stockCrawlCsvDown(HttpServletResponse response, StockDto stockDto) {
		HttpHeaders header = new HttpHeaders(); // http post 요청할때 보내는 데이터(body) 를 설명해주는 헤더
		header.add("Content-Type", "text/csv; charset=MS949"); // csv는 MS949 셋팅
		header.add("Content-Disposition", "attachment; filename=\"" + "stock_list.csv" + "\"");
		return new ResponseEntity<String>(crawlingService.stockCrawlCsvDown(stockDto), header, HttpStatus.CREATED);
	}
	//excel
	
	@RequestMapping(value = "/stockCrawlExcelDown")
	public void stockCrawlExcelDown(HttpServletResponse response, StockDto stockDto) {
	// HttpServletResponse 인자로 받아야 함 
		crawlingService.stockCrawlExcelDown(response, stockDto);
	}
}
