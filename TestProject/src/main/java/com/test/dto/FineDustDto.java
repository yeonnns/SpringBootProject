package com.test.dto;

import com.test.entity.FineDustEntity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FineDustDto {
	
//	private int so2Flag;			// 아황산가스 플래그
//	private int coFlag;				// 일산화탄소 플래그
//	private int no2Flag;			// 이산화질소 플래그
//	private int o3Flag;				// 오존 플래그
//	private int pm10Flag;			// 미세먼지(PM10) 플래그
//	private int pm25Flag;			// 초미세먼지(PM2.5) 플래그
	
	private String no;

	private String so2Grade;			// 아황산가스 지수
	private String so2Value;		// 아황산가스 농도
	
	private String coGrade;			// 일산화탄소 지수
	private String coValue;			// 일산화탄소 농도
	
	private String no2Grade;			// 이산화질소 지수
	private String no2Value;		// 이산화질소 농도
	
	private String o3Grade;			// 오존 지수
	private String o3Value;			// 오존 농도

	private String khaiGrade;			// 통합대기환경지수
	private String khaiValue;			// 통합대기환경수치
	
	private String pm10Grade;			// 미세먼지(PM10) 24시간 등급자료
	private String pm10Value;		// 미세먼지(PM10) 농도
	
	private String pm25Grade;			// 초미세먼지(PM2.5) 24시간 등급자료
	private String pm25Value;		// 초미세먼지(PM2.5) 농도
	
	private String sidoName;		// 시도명
	private String dataTime;		// 측정일시 10AM 5PM 
	private String stationName;		// 측정소명

	
	public FineDustEntity toEntity() {
		FineDustEntity build = FineDustEntity.builder()
								.no(no) 
								.so2Grade(so2Grade)  			
								.so2Value(so2Value) 
								.coGrade(coGrade) 		
								.coValue(coValue)  			
								.no2Grade(no2Grade)  		
								.no2Value(no2Value) 		
								.o3Grade(o3Grade)			
								.o3Value(o3Value)  		
								.khaiGrade(khaiGrade)  			
								.khaiValue(khaiValue)  			
								.pm10Grade(pm10Grade) 		
								.pm10Value(pm10Value) 		
								.pm25Grade(pm25Grade) 
								.pm25Value(pm25Value) 		
								.sidoName(sidoName) 	
								.dataTime(dataTime) 		
								.stationName(stationName)
								.build();
		return build;
	}
	
	public FineDustDto(FineDustEntity fde) {
		this.no = fde.getNo();
		this.so2Grade = fde.getSo2Grade(); 			
		this.so2Value = fde.getSo2Value();
		this.coGrade = fde.getCoGrade();		
		this.coValue = fde.getCoValue(); 			
		this.no2Grade = fde.getNo2Grade(); 		
		this.no2Value = fde.getNo2Value();		
		this.o3Grade = fde.getO3Grade();			
		this.o3Value = fde.getO3Value(); 		
		this.khaiGrade = fde.getKhaiGrade(); 			
		this.khaiValue = fde.getKhaiValue(); 			
		this.pm10Grade = fde.getPm10Grade();		
		this.pm10Value = fde.getPm10Value();		
		this.pm25Grade = fde.getPm25Grade();
		this.pm25Value = fde.getPm25Value();		
		this.sidoName = fde.getSidoName();	
		this.dataTime = fde.getDataTime();		
		this.stationName = fde.getStationName(); 	
	}

	@Builder
	public FineDustDto(String no, String so2Grade, String so2Value, String coGrade, String coValue, String no2Grade, String no2Value, String o3Grade, String o3Value,
			String khaiGrade, String khaiValue, String pm10Grade, String pm10Value, String pm25Grade, String pm25Value, String sidoName, String  dataTime, String stationName) {
		this.no = no;
		this.so2Grade = so2Grade; 			
		this.so2Value = so2Value;
		this.coGrade = coGrade;		
		this.coValue = coValue; 			
		this.no2Grade = no2Grade; 		
		this.no2Value = no2Value;		
		this.o3Grade = o3Grade;			
		this.o3Value = o3Value; 		
		this.khaiGrade = khaiGrade; 			
		this.khaiValue = khaiValue; 			
		this.pm10Grade = pm10Grade;		
		this.pm10Value = pm10Value;		
		this.pm25Grade = pm25Grade;
		this.pm25Value = pm25Value;		
		this.sidoName = sidoName;	
		this.dataTime = dataTime;		
		this.stationName = stationName; 	
	}
}

