package com.test.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;

public class ExcelUtilCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);

	public static Workbook getWorkbook(String filePath) {
		logger.info("@@@@@@@@@@@@@@@@@");
		/*
		 * FileInputStream은 파일의 경로에 있는 파일을 읽어서 Byte로 가져온다.
		 * 
		 * 파일이 존재하지 않는다면은 RuntimeException이 발생된다.
		 */
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		Workbook wb = null;

		/*
		 * 파일의 확장자를 체크해서 .XLS 라면 HSSFWorkbook에 .XLSX라면 XSSFWorkbook에 각각 초기화 한다.
		 */
		if (filePath.toUpperCase().endsWith(".CSV")) {
			try {
				wb = new HSSFWorkbook(fis);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		} else if (filePath.toUpperCase().endsWith(".XLSX")) {
			try {
				wb = new XSSFWorkbook(fis);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

		return wb;

	}

	// Excel Upload시에 데이터를 얻어옵니다.
	@SuppressWarnings("deprecation")
	public static String cellValue(Cell cell) {

		String value = null;
		if (cell == null)
			value = "";
		else {
			switch (cell.getCellType()) { // cell 타입에 따른 데이타 저장
			case Cell.CELL_TYPE_FORMULA:
				value = cell.getCellFormula();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					// you should change this to your application date format
					SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					value = "" + objSimpleDateFormat.format(cell.getDateCellValue());
				} else {
					value = "" + String.format("%.0f", new Double(cell.getNumericCellValue()));
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = "" + cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				// value=""+cell.getBooleanCellValue();
				value = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				value = "" + cell.getErrorCellValue();
				break;
			default:
			}
		}

		return value.trim();
	}

	public static List<String[]> readCsv(String excelFile) {
		File file = new File(excelFile);
		Charset fileChatset = null;
		try {
			fileChatset = findFileEncoding(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<String[]> data = new ArrayList<String[]>();
		try {
//			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(excelFile), "MS949"));
			FileInputStream fis = new FileInputStream(excelFile);
			InputStreamReader isr = null;
			if(fileChatset.name().equals("UTF-8")) {
				isr = new InputStreamReader(fis, "UTF-8");	
			}else {
				isr = new InputStreamReader(fis, "MS949");
			}
			CSVReader reader = new CSVReader(isr,',','"','∮');
//			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(excelFile), "MS949"),',','"','∮');
			String[] s;
			while ((s = reader.readNext()) != null) {
				data.add(s);
			}
			fis.close();
			isr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	//엑셀파일 캐릭터셋 이름 찾기
	public static Charset findFileEncoding(File file) throws IOException {
        Charset charset = null;
        byte[] buf = new byte[4096]; 
        try (
              FileInputStream fis = new FileInputStream(file); 
        ){
            UniversalDetector detector = new UniversalDetector(null); 
            int nread; 
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                 detector.handleData(buf, 0, nread); 
                } 
                detector.dataEnd(); 
                String encoding = detector.getDetectedCharset(); 
                if (encoding != null) { 
                    charset = Charset.forName(encoding);
                }else { 
                    throw new RuntimeException("파일 Chaset Find에 실패하였습니다.");
                } 
                detector.reset();
                fis.close();
        }catch(IOException e){
            throw new IOException(e.getMessage());
        }
        logger.info("파일 이름 : " + file.getName() + " / 캐릭터셋 : "+ charset);
        return charset;
    }

}
