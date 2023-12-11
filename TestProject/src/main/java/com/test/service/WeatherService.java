package com.test.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dto.FineDustDto;
import com.test.entity.FineDustEntity;
import com.test.mapper.FineDustMapper;

@Service
@Transactional
public class WeatherService {

	@Autowired
	FineDustMapper fineDustMapper;
	
	private String serviceKey = "serviceKeyserviceKeyserviceKeyserviceKeyserviceKey";
	private String endPoint  = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	public void fineDustInsert() throws Exception {
	    StringBuilder urlBuilder = new StringBuilder(endPoint); 
	    urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey);
	    urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); // xml json
	    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); 
	    urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
	    urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode("서울", "UTF-8")); // 시도 이름(전국, 서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)*/
	    urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8")); 
	    URL url = new URL(urlBuilder.toString());
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
	    System.out.println("Response code: " + conn.getResponseCode());
	    BufferedReader rd;
	    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    } else {
	        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	    }
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	        sb.append(line);
	    }
	    String result = sb.toString();
	    
	    rd.close();
	    conn.disconnect();
	    	
	    // JSON parser 만들어 문자열 데이터를 객체화한다.
	    JSONParser parser = new JSONParser();
	   
	    //JSON데이터를 넣어 JSON Object 로 만들어 준다.
	    JSONObject obj = (JSONObject)parser.parse(result);
	    JSONObject responseResult = (JSONObject)obj.get("response");
	    JSONObject bodyResult = (JSONObject)responseResult.get("body");
	    FineDustDto fdDto = new FineDustDto();
	    ArrayList<FineDustDto> dustList = new ArrayList<FineDustDto>();
	    ArrayList<FineDustEntity> dustList2 = new ArrayList<FineDustEntity>();

	    //배열 추출
	    JSONArray itemsResult = (JSONArray)bodyResult.get("items");
	    for(int i=0, n=itemsResult.size(); i<n; i++){
	        //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
	        JSONObject object = (JSONObject) itemsResult.get(i);
	       // String[] test = {"so2Grade", "so2Value", "coGrade", "coValue", "no2Grade", "no2Value", "o3Grade", "o3Value", "khaiGrade", "khaiValue", "pm10Grade", "pm10Value", "pm25Grade", "pm25Value", "sidoName", "dataTime", "stationName"};
	        
	        fdDto = new FineDustDto();
	        fdDto.setSo2Grade((String)object.get("so2Grade"));
	        fdDto.setSo2Value((String)object.get("so2Value"));
	        fdDto.setCoGrade((String)object.get("coGrade"));
	        fdDto.setCoValue((String)object.get("coValue"));
	        fdDto.setNo2Grade((String)object.get("no2Grade"));
	        fdDto.setNo2Value((String)object.get("no2Value"));
	        fdDto.setO3Grade((String)object.get("o3Grade"));
	        fdDto.setO3Value((String)object.get("o3Value"));
	        fdDto.setKhaiGrade((String)object.get("khaiGrade"));
	        fdDto.setKhaiValue((String)object.get("khaiValue"));
	        fdDto.setPm10Grade((String)object.get("pm10Grade"));
	        fdDto.setPm10Value((String)object.get("pm10Value"));
	        fdDto.setPm25Grade((String)object.get("pm25Grade"));
	        fdDto.setPm25Value((String)object.get("pm25Value"));
	        fdDto.setSidoName((String)object.get("sidoName"));
	        fdDto.setDataTime((String)object.get("dataTime"));
	        fdDto.setStationName((String)object.get("stationName"));
	        dustList.add(fdDto);
	        
	    }
	    fineDustMapper.insertFineDust(dustList);
	}


}
