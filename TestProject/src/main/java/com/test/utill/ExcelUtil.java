package com.test.utill;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtil {
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);


	public static XSSFCellStyle getTitleFont(XSSFWorkbook xlsxWb) {
		XSSFCellStyle style = xlsxWb.createCellStyle();
		XSSFFont font = xlsxWb.createFont();
		font.setFontName("맑은 고딕");
		font.setFontHeight((short)180);
		font.setBold(true);
		style.setFont(font);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		return style;
	}

	public static XSSFCellStyle getDefaultFont(XSSFWorkbook xlsxWb) {
		XSSFCellStyle style = xlsxWb.createCellStyle();
		XSSFFont font = xlsxWb.createFont();
		font.setFontName("맑은 고딕");
		font.setFontHeight((short)180);
		style.setFont(font);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		return style;
	}
	

	public static XSSFCellStyle getSalesRedFont(XSSFWorkbook xlsxWb) {
		XSSFCellStyle style = xlsxWb.createCellStyle();
		XSSFFont font = xlsxWb.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setFontName("맑은 고딕");
		font.setFontHeight((short)180);
		style.setFont(font);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		return style;
	}

	public static XSSFCellStyle getSalesBlueFont(XSSFWorkbook xlsxWb) {
		XSSFCellStyle style = xlsxWb.createCellStyle();
		XSSFFont font = xlsxWb.createFont();
		font.setColor(HSSFColor.BLUE.index);
		font.setFontName("맑은 고딕");
		font.setFontHeight((short)180);
		style.setFont(font);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		return style;
	}
	
}
