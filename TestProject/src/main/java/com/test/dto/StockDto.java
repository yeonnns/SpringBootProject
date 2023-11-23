package com.test.dto;

import lombok.Data;

@Data
public class StockDto {

	// 크롤링
	private String createDate;
	private	String stockName;
	private String presentPrice;	// 현재가
	private String prevDayDiff; 	// 전일비
	private String fluctuationRate; // 등락률
	private String farValue; 		// 액면가
	private String tradingValue;	// 거래량
	private String highPrice;
	private String lowPrice;
	private String marketCap; 		// marketCapitalization 시가총액
	// roa roe 차이 크면 부채비율 높다
	private String roe; 			// return_on_equity 자기자본수익률(당기순이익 / 자기자본)
	private String roa; 			// return_on_assets 총자산수익률(당기순이익 / 자기자본 + 부채)
	
	// top 뽑기
	private String stockNameH;
	private String lastHighPrice;
	private String lastHighPer;
	private String stockNameL;
	private String lastLowPrice;
	private String lastLowPer;
}
