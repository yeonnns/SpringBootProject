package com.test.mapper;

import java.util.ArrayList;

import com.test.dto.StockDto;

public interface CrawlingMapper {

	public int stockInsert(ArrayList<StockDto> list);
	public ArrayList<StockDto> stockList(StockDto stockDto);
	public String latestDate();
	public int insertTodayTop();
	public ArrayList<StockDto> selectWeekTop();

}
