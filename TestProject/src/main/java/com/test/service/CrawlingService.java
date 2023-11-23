package com.test.service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dto.StockDto;
import com.test.mapper.CrawlingMapper;
import com.test.utill.ExcelUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

@Service
public class CrawlingService {

	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);

	private static final Object[] String = null;
	private String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private String WEB_DRIVER_PATH = "C:/soyeon/app/crawling/chromedriver.exe";
	private WebDriver driver;
	private String url;
	
	@Autowired
	CrawlingMapper crawlingMapper;
	
	@Transactional
	public void startStockCrawl( ) {
		if(driver != null) {driver.quit();}
		
		WebDriverManager.chromedriver().setup();
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("disable-gpu");
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        driver = new ChromeDriver(options);
		
		try {
			url = "https://finance.naver.com/sise/sise_market_sum.nhn";
			driver.get(url);
			Thread.sleep(1000);
			
			// 기존 checkbox6개중 3개 해제
			driver.findElement(By.id("option21")).click(); // 상장주식수
			WebElement str= driver.findElement(By.id("option21")); // 상장주식수
			driver.findElement(By.id("option15")).click(); // 외국인비율
			driver.findElement(By.id("option6")).click(); // per
			// 기존 3개 + 3개 추가 선택
			// 12 roe  1 거래량 4 시가총액
			driver.findElement(By.id("option18")).click(); // roa
			driver.findElement(By.id("option13")).click(); // 고가
			driver.findElement(By.id("option19")).click(); // 저가
			//제출
			driver.findElement(By.cssSelector("#contentarea_left > div.box_type_m > form > div > div > div > a:nth-child(1) > img")).click();

			ArrayList<StockDto> StockList = new ArrayList<StockDto>();
			StockDto stockdto = null;
			for(int k= 1; k<3; k++) {
				url = "https://finance.naver.com/sise/sise_market_sum.nhn?&page="+k;
				driver.get(url);
				Thread.sleep(1000);
				//System.out.println(driver.getPageSource());
				List<WebElement> elements = driver.findElements(By.cssSelector("#contentarea > div.box_type_l > table.type_2 > tbody "));
				String element = elements.get(0).getText();
				String[] arrElement = element.split("\\n");
				
				for(int i = 0; i< arrElement.length;i++) {
					stockdto = new StockDto();
					String indiviElement = arrElement[i];
					String[] arrIndiviElement = indiviElement.split("\\s");
						for(int j = 0; j< arrIndiviElement.length;j++) {
							arrIndiviElement[j] = arrIndiviElement[j].replaceAll(",","");
						}
						int nu = 0;
						if(arrIndiviElement.length == 12) {
							stockdto.setStockName(arrIndiviElement[1+nu]);
						}else if(arrIndiviElement.length == 13) {
							nu = 1;
							stockdto.setStockName(arrIndiviElement[1]+arrIndiviElement[1+nu]);
						}else if(arrIndiviElement.length == 14) {
							nu = 2;
							stockdto.setStockName(arrIndiviElement[1]+arrIndiviElement[nu]+ arrIndiviElement[1+nu]);
						}else if(arrIndiviElement.length == 15) {
							nu = 3;
							stockdto.setStockName(arrIndiviElement[1]+arrIndiviElement[nu-1]+arrIndiviElement[nu]+ arrIndiviElement[1+nu]);
						}
						stockdto.setPresentPrice(arrIndiviElement[2+nu]);
						stockdto.setPrevDayDiff(arrIndiviElement[3+nu]);
						arrIndiviElement[4+nu] = arrIndiviElement[4+nu].replaceAll("%", "");
						stockdto.setFluctuationRate(arrIndiviElement[4+nu]);
						stockdto.setFarValue(arrIndiviElement[5+nu]);
						stockdto.setTradingValue(arrIndiviElement[6+nu]);
						stockdto.setHighPrice(arrIndiviElement[7+nu]);
						stockdto.setLowPrice(arrIndiviElement[8+nu]);
						stockdto.setMarketCap(arrIndiviElement[9+nu]);
						stockdto.setRoe(arrIndiviElement[10+nu]);
						stockdto.setRoa(arrIndiviElement[11+nu]);
						StockList.add(stockdto);
				}
			}
			crawlingMapper.stockInsert(StockList);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			driver.close();
			driver.quit();
		}	
	}

	public ArrayList<StockDto> stockList(StockDto stockDto){
		return crawlingMapper.stockList(stockDto);
	}
	
	public String latestDate(){
		return crawlingMapper.latestDate();
	}
	
	// csv 사용
	public String stockCrawlCsvDown(StockDto stockDto) {
		StringBuffer resultData = new StringBuffer("일자, 주식명, 현재가, 전일비, 전일 등락률, 액면가, 거래량, 고가, 저가, 시가총액, roe, roa \n");
		try {
			ArrayList<StockDto> list = crawlingMapper.stockList(stockDto);
			int listSize = list.size();
			for(int i=0; i<listSize; i++) {
				StockDto  exList = list.get(i);
				resultData.append("\"" + exList.getCreateDate() + "\",");
				resultData.append("\"" + exList.getStockName() + "\",");
				resultData.append("\"" + exList.getPresentPrice() + "\",");
				resultData.append("\"" + exList.getPrevDayDiff() + "\",");
				resultData.append("\"" + exList.getFluctuationRate() + "\",");
				resultData.append("\"" + exList.getFarValue() + "\",");
				resultData.append("\"" + exList.getTradingValue() + "\",");
				resultData.append("\"" + exList.getHighPrice() + "\",");
				resultData.append("\"" + exList.getLowPrice() + "\",");
				resultData.append("\"" + exList.getMarketCap() + "\",");
				resultData.append("\"" + exList.getRoe() + "\",");
				resultData.append("\"" + exList.getRoa() + "\",");
				resultData.append("\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return resultData.toString();	
	}
	
	// 엑셀다운 poi 엑셀 사용
	public void stockCrawlExcelDown(HttpServletResponse response, StockDto stockDto) {
		ArrayList<StockDto> list = crawlingMapper.stockList(stockDto);
		String stockDay = crawlingMapper.latestDate();
		
		XSSFWorkbook xlsxWb = new XSSFWorkbook();
		//excel style
	    XSSFCellStyle titleStyle = ExcelUtil.getTitleFont(xlsxWb);
	    XSSFCellStyle defaultStyle = ExcelUtil.getDefaultFont(xlsxWb);
	    XSSFCellStyle redStyle = ExcelUtil.getSalesRedFont(xlsxWb);
	    XSSFCellStyle blueStyle = ExcelUtil.getSalesBlueFont(xlsxWb);
	    
	    // 시트명 생성
	    XSSFSheet sheet = xlsxWb.createSheet("today 시총 top100");
	    //행,열
        Row row = null;
        Cell cell = null;

		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 3000);
		sheet.setColumnWidth(11, 3000);
		
		String[] headerKey = {"일자", "주식명", "현재가", "전일비", "전일 등락률", "액면가", "거래량", "고가", "저가", "시가총액", "roe", "roa"};
		
		row = sheet.createRow(0);
		for(int i = 0; i<headerKey.length;i++) {
			cell = row.createCell(i);
			cell.setCellValue(headerKey[i]);
			cell.setCellStyle(titleStyle);
		}
		
		// 데이터 구성
		for(int i=0; i<list.size();i++) {
			row = sheet.createRow(i+1);
			int cellIdx = 0;
		
			StockDto stock = list.get(i);
			cell = row.createCell(cellIdx++);
			cell.setCellValue(stock.getCreateDate());
			cell.setCellStyle(defaultStyle);
			
			cell = row.createCell(cellIdx++);
			cell.setCellValue(stock.getStockName());
			cell.setCellStyle(defaultStyle);
			
			cell = row.createCell(cellIdx++);
			cell.setCellValue(Integer.parseInt(stock.getPresentPrice()));
			cell.setCellStyle(defaultStyle);

			cell = row.createCell(cellIdx++);
			cell.setCellValue(Integer.parseInt(stock.getPrevDayDiff()));
			cell.setCellStyle(defaultStyle);
			if(stock.getFluctuationRate().contains("-")) {
				cell.setCellStyle(redStyle);
			} else if (stock.getFluctuationRate().contains("+")) {
				cell.setCellStyle(blueStyle);
			}
			
			
			cell = row.createCell(cellIdx++);
			cell.setCellValue(Float.parseFloat(stock.getFluctuationRate()));
			cell.setCellStyle(defaultStyle);
			if(stock.getFluctuationRate().contains("-")) {
				cell.setCellStyle(redStyle);
			} else if (stock.getFluctuationRate().contains("+")) {
				cell.setCellStyle(blueStyle);
			}
			
			cell = row.createCell(cellIdx++);
			cell.setCellValue(Integer.parseInt(stock.getFarValue()));
			cell.setCellStyle(defaultStyle);
			
			cell = row.createCell(cellIdx++);
			cell.setCellValue(Integer.parseInt(stock.getTradingValue()));
			cell.setCellStyle(defaultStyle);
			
			cell = row.createCell(cellIdx++);
			cell.setCellValue(Integer.parseInt(stock.getHighPrice()));
			cell.setCellStyle(defaultStyle);
			
			cell = row.createCell(cellIdx++);
			cell.setCellValue(Integer.parseInt(stock.getLowPrice()));
			cell.setCellStyle(defaultStyle);
			
			cell = row.createCell(cellIdx++);
			cell.setCellValue(Integer.parseInt(stock.getMarketCap()));
			cell.setCellStyle(defaultStyle);
			
			cell = row.createCell(cellIdx++);
			cell.setCellValue(stock.getRoe());
			cell.setCellStyle(defaultStyle);
			
			cell = row.createCell(cellIdx++);
			cell.setCellValue(stock.getRoa());
			cell.setCellStyle(defaultStyle);
		}
		
		OutputStream out = null;
		String excelName = "";

		try {
			excelName = stockDay + "_" + URLEncoder.encode("stockList.xlsx", "UTF-8") + ".xlsx";
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename="+excelName);
			response.setContentType("application/vnd.ms-excel");
			
			out = new BufferedOutputStream(response.getOutputStream());
			xlsxWb.write(out);
			out.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public int insertTodayTop() {
		return crawlingMapper.insertTodayTop();
	}
	
	public ArrayList<StockDto> selectWeekTop(){
		ArrayList<StockDto> weekTopList = crawlingMapper.selectWeekTop();
		for(int i = 0, n=weekTopList.size(); i<n; i++ ) {
			String LowPri = weekTopList.get(i).getLastLowPrice();
			weekTopList.get(i).setLastLowPrice("-"+LowPri);
		}
		return weekTopList;
	}
}
