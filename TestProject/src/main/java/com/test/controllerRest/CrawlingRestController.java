package com.test.controllerRest;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.dto.StockDto;
import com.test.service.CrawlingService;

@Component
@Controller
@RequestMapping("/api/crawl")
public class CrawlingRestController{
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	@Autowired
	private CrawlingService crawService;
	
	// ArrayList
	@ResponseBody
	@RequestMapping(value = "/weekStockChart")
	public ArrayList<HashMap<String, Object>> weekHighChart(StockDto stockDto) {
		
		ArrayList<StockDto> WeekTop = crawService.selectWeekTop();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		for(StockDto stock :  WeekTop) {	
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("createDate", stock.getCreateDate());
				hashMap.put("stockNameH", stock.getStockNameH());
				hashMap.put("lastHighPrice", stock.getLastHighPrice());
				hashMap.put("lastHighPer", stock.getLastHighPer());
				hashMap.put("stockNameL", stock.getStockNameL());
				hashMap.put("lastLowPrice", stock.getLastLowPrice());
				hashMap.put("lastLowPer", stock.getLastLowPer());
				list.add(hashMap);
		}
		return list;
	}
	
	// JSONArray
	@ResponseBody
	@RequestMapping(value = "/weekStockChart2")
	public ArrayList<HashMap<String, Object>> weekHighChart2(StockDto stockDto) {
		
		ArrayList<StockDto> WeekTop = crawService.selectWeekTop();
		JSONArray jsonArr = new JSONArray(); // [] 배열생성
		
		for(StockDto stock :  WeekTop) {	
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("createDate", stock.getCreateDate());
			hashMap.put("stockNameH", stock.getStockNameH());
			hashMap.put("lastHighPrice", stock.getLastHighPrice());
			hashMap.put("lastHighPer", stock.getLastHighPer());
			hashMap.put("stockNameL", stock.getStockNameL());
			hashMap.put("lastLowPrice", stock.getLastLowPrice());
			hashMap.put("lastLowPer", stock.getLastLowPer());
			JSONObject jsonObj = new JSONObject(hashMap); //{}객체생성
			jsonArr.add(jsonObj);
		}
		return jsonArr;
	}
}
