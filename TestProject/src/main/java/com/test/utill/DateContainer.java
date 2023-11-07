package com.test.utill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ch.qos.logback.classic.Logger;

public class DateContainer {

	public static String getDateTest(int num) {
		return getWeekMon(num);
	}

	public static String lastWeekStartDate() {
		return getWeekMon(-14);
	}

	public static String lastWeekEndDate() {
		return getWeekSun(-7);
	}

	public static String nowWeekStartDate() {
		return getWeekMon(-7);
	}

	public static String nowWeekEndDate() {
		return getWeekSun(0);
	}
	
	//현재시각 yyyy-MM-dd HH:mm:ss
	public static String getNowDateTimeSecond() {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		return format1.format(Calendar.getInstance().getTime());
	}
	
	public static String getNowDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		return formatter.format(c.getTime());
	}
	
	public static String getNowDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd_HHmm");
		Calendar c = Calendar.getInstance();
		return formatter.format(c.getTime());
	}
	
	public static String getNowMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM");
		Calendar c = Calendar.getInstance();
		return formatter.format(c.getTime());
	}

	// 어제 날짜 (리포트 배치용)
	public static Long getYesterdayDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		return Long.valueOf(formatter.format(c.getTime()));
	}
	
	public static String getYesterdayDate(String yyyymmdd) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		
 		Calendar c = Calendar.getInstance();
 		c.set(yyyy, mm, dd);
 		c.add(Calendar.DATE, -1);
		
		return formatter.format(c.getTime());
	}
	public static String getYesterdayDateHyphen(String yyyymmdd) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		
 		Calendar c = Calendar.getInstance();
 		c.set(yyyy, mm, dd);
 		c.add(Calendar.DATE, -1);
		
		return formatter.format(c.getTime());
	}
	

	public static String getWeekMon(int addDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.add(Calendar.DATE, addDate);
		return formatter.format(c.getTime());
	}

	public static String getWeekSun(int addDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.add(Calendar.DATE, addDate);
		return formatter.format(c.getTime());
	}

//	이번주 월요일
	public static String getMondayFromDay(String yyyymmdd) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8)) - 1;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);
		setDate = formatter.format(calendar.getTime());
		int yoil = calendar.get(Calendar.DAY_OF_WEEK);

		int gap = 0;
		int[] idx = { 1, 2, 3, 4, 5, 6, 7 };
		for(int i = 0; i < idx.length; i++) {
			if (yoil != 2 && yoil == idx[i]) {
				gap = i * 2 - yoil;
				gap = 0 - gap;
			}
		}

		calendar.add(Calendar.DATE, gap);
		setDate = formatter.format(calendar.getTime());
		yoil = calendar.get(Calendar.DAY_OF_WEEK);

		return setDate;
	}

	
//	이번주 일요일
	public static String getSundayFromDay(String yyyymmdd) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8)) - 1;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);
		setDate = formatter.format(calendar.getTime());
		int yoil = calendar.get(Calendar.DAY_OF_WEEK);

		int gap = 0;
		int[] idx = { 1, 2, 3, 4, 5, 6, 7 };
		for(int i = 0; i < idx.length; i++) {
			if (yoil != 2 && yoil == idx[i]) {
				gap = i * 2 - yoil;
				gap = 0 - gap;
			}
		}

		calendar.add(Calendar.DATE, gap);
		calendar.add(Calendar.DATE, 6);
		setDate = formatter.format(calendar.getTime());
		yoil = calendar.get(Calendar.DAY_OF_WEEK);

		return setDate;
	}

	//저번주 월요일
	public static String getBeforeMondayFromDay(String yyyymmdd) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8)) - 1;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);
		setDate = formatter.format(calendar.getTime());
		int yoil = calendar.get(Calendar.DAY_OF_WEEK);

		int gap = 0;
		int[] idx = { 1, 2, 3, 4, 5, 6, 7 };
		for(int i = 0; i < idx.length; i++) {
			if (yoil != 2 && yoil == idx[i]) {
				gap = i * 2 - yoil;
				gap = 0 - gap;
			}
		}

		calendar.add(Calendar.DATE, gap);
		calendar.add(Calendar.DATE, -7);
		setDate = formatter.format(calendar.getTime());
		yoil = calendar.get(Calendar.DAY_OF_WEEK);

		return setDate;
	}

//	저번주 일요일
	public static String getBeforeSundayFromDay(String yyyymmdd) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8)) - 1;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);
		setDate = formatter.format(calendar.getTime());
		int yoil = calendar.get(Calendar.DAY_OF_WEEK);

		int gap = 0;
		int[] idx = { 1, 2, 3, 4, 5, 6, 7 };
		for(int i = 0; i < idx.length; i++) {
			if (yoil != 2 && yoil == idx[i]) {
				gap = i * 2 - yoil;
				gap = 0 - gap;
			}
		}

		calendar.add(Calendar.DATE, gap);
		calendar.add(Calendar.DATE, -1);
		setDate = formatter.format(calendar.getTime());
		yoil = calendar.get(Calendar.DAY_OF_WEEK);

		return setDate;
	}
	
	//이번주 월요일
	public static String getNowMondayFromDay(String yyyymmdd) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8)) - 1;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);
		setDate = formatter.format(calendar.getTime());
		int yoil = calendar.get(Calendar.DAY_OF_WEEK);

		int gap = 0;
		int[] idx = { 1, 2, 3, 4, 5, 6, 7 };
		for(int i = 0; i < idx.length; i++) {
			if (yoil != 2 && yoil == idx[i]) {
				gap = i * 2 - yoil;
				gap = 0 - gap;
			}
		}

		calendar.add(Calendar.DATE, gap);
		setDate = formatter.format(calendar.getTime());
		yoil = calendar.get(Calendar.DAY_OF_WEEK);

		return setDate;
	}

//	이번주 일요일
	public static String getNowSundayFromDay(String yyyymmdd) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8)) - 1;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);
		setDate = formatter.format(calendar.getTime());
		int yoil = calendar.get(Calendar.DAY_OF_WEEK);

		int gap = 0;
		int[] idx = { 1, 2, 3, 4, 5, 6, 7 };
		for(int i = 0; i < idx.length; i++) {
			if (yoil != 2 && yoil == idx[i]) {
				gap = i * 2 - yoil;
				gap = 0 - gap;
			}
		}
		calendar.add(Calendar.DATE, gap);
		calendar.add(Calendar.DATE, +6);
		setDate = formatter.format(calendar.getTime());
		yoil = calendar.get(Calendar.DAY_OF_WEEK);

		return setDate;
	}
	public static String getNaverPivotMinDate(String yyyymmdd, int addMonth) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";
		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);

		calendar.add(calendar.YEAR, addMonth);
		if(dd == 1) {
			calendar.add(calendar.MONTH, 2);
		}else {
			calendar.add(calendar.MONTH, 3);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1); // 해당 월의 1일로 변경
		setDate = formatter.format(calendar.getTime());
		return setDate;
	}

	/**
	 * 이전달 1일
	 */
	public static String getBeforeMonthStart(String yyyymmdd, int addMonth) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";
		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);

		calendar.add(calendar.MONTH, addMonth);
		calendar.set(Calendar.DAY_OF_MONTH, 1); // 해당 월의 1일로 변경
		setDate = formatter.format(calendar.getTime());
		return setDate;
	}

	/**
	 * 이전달 마지막 날짜
	 */
	public static String getBeforeMonthEnd(String yyyymmdd, int addMonth) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);

		calendar.add(calendar.MONTH, addMonth);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // 해당 월의 1일로 변경
		setDate = formatter.format(calendar.getTime());
		return setDate;
	}

	/**
	 * 이번달 1일
	 */
	public static String getNowMonthStart(String yyyymmdd) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);

		calendar.add(calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1); // 해당 월의 1일로 변경
		setDate = formatter.format(calendar.getTime());
		return setDate;
	}

	/**
	 * 이번달 마지막 날짜
	 */
	public static String getNowMonthEnd(String yyyymmdd) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);

		calendar.add(calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // 해당 월의 1일로 변경
		setDate = formatter.format(calendar.getTime());
		return setDate;
	}

	/**
	 * 이번달 첫번째 월요일
	 */
	public static String getNowMonthFirstMonday(String yyyymmdd) {
		yyyymmdd = getNowMonthStart(yyyymmdd);
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();

		calendar.set(yyyy, mm, dd);

		if ((1 - calendar.get(Calendar.DAY_OF_WEEK) >= 0) || (calendar.get(Calendar.DAY_OF_WEEK) == 2)) {
			dd = (9 + (1 - calendar.get(Calendar.DAY_OF_WEEK)) - 7);
		} else {
			dd = 9 + (1 - calendar.get(Calendar.DAY_OF_WEEK));
		}

		calendar.set(yyyy, mm, dd);

		setDate = formatter.format(calendar.getTime());
		return setDate;
	}

	/**
	 * 다음달 첫번째 일요일
	 */
	public static String getNextMonthFirstSunday(String yyyymmdd) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);

		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // 해당 월의 1일로 변경

		if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
			dd = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else {
			mm = mm + 1;
			dd = 7 + (1 - calendar.get(Calendar.DAY_OF_WEEK));
		}
		calendar.set(yyyy, mm, dd);
		setDate = formatter.format(calendar.getTime());
		return setDate;
	}

	/**
	 * @Author : PJW
	 * @Date : 2020. 8. 7.
	 * @Description : 해당 달의 일 수 구하기 (파라미터 "yyyy-mm")
	 */
	public static int getDayCntMonth(String date) {
		date = date.replaceAll("-", "");
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
//		int month = Integer.valueOf(date.substring(5, 7));
		int days = 0;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			days = 31;
		} else if (month == 2) {
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
				days = 29;
			else
				days = 28;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			days = 30;
		}
		return days;
	}

	/**
	 * @Author : PJW
	 * @Date : 2020. 8. 10.
	 * @Description : 요일구하기 (파라미터 : "yyyy-mm-dd")
	 */
	public static String getDayOfWeek(String date) {
		date = date.replaceAll("-", "");
		int yyyy = Integer.parseInt(date.substring(0, 4));
		int mm = Integer.parseInt(date.substring(4, 6)) - 1;
		int dd = Integer.parseInt(date.substring(6, 8));
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm, dd);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		String korDayOfWeek = "";
		switch (dayOfWeek) {
		case 6:
			korDayOfWeek = "금";
			break;
		case 7:
			korDayOfWeek = "토";
			break;
		case 1:
			korDayOfWeek = "일";
			break;
		case 2:
			korDayOfWeek = "월";
			break;
		case 3:
			korDayOfWeek = "화";
			break;
		case 4:
			korDayOfWeek = "수";
			break;
		case 5:
			korDayOfWeek = "목";
			break;
		}
		return korDayOfWeek;
	}

	/**
	 * @Author : PJW
	 * @Date : 2020. 8. 12.
	 * @Description : 해당 범위의 날짜 수 (네이버 통합리포트 -> 종합 -> 운영일수)
	 */
	public static Integer getDayCntDayw(String startDate, String endDate) {
		startDate = startDate.replaceAll("-", "");
		endDate = endDate.replaceAll("-", "");
		
		int syyyy = Integer.parseInt(startDate.substring(0, 4));
		int smm = Integer.parseInt(startDate.substring(4, 6)) - 1;
		int sdd = Integer.parseInt(startDate.substring(6, 8));

		int eyyyy = Integer.parseInt(endDate.substring(0, 4));
		int emm = Integer.parseInt(endDate.substring(4, 6)) - 1;
		int edd = Integer.parseInt(endDate.substring(6, 8));

		Calendar cal2 = Calendar.getInstance();
		cal2.set(syyyy, smm, sdd);

		Calendar cal1 = Calendar.getInstance();
		cal1.set(eyyyy, emm, edd);

		int count = 0;
		while (!cal2.after(cal1)) {
			count++;
			cal2.add(Calendar.DATE, 1);
		}
//		System.out.println("기준일로부터 " + count + "일이 지났습니다.");

		return count;
	}
	
	//int week주일 전 월요일 Get
	public static String getFiveBeforeMondayFromDay(String yyyymmdd, int week) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8)) - 1;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);
		setDate = formatter.format(calendar.getTime());
		int yoil = calendar.get(Calendar.DAY_OF_WEEK);

		int gap = 0;
		int[] idx = { 1, 2, 3, 4, 5, 6, 7 };
		for(int i = 0; i < idx.length; i++) {
			if (yoil != 2 && yoil == idx[i]) {
				gap = i * 2 - yoil;
				gap = 0 - gap;
			}
		}
		
		calendar.add(Calendar.DATE, gap);
		calendar.add(Calendar.DATE, -(week*7));
		setDate = formatter.format(calendar.getTime());
		yoil = calendar.get(Calendar.DAY_OF_WEEK);

		return setDate;
	}
	
	//두 날짜의 개월차이
	public static int getMonthDiff(String bigDate, String smallDate) {
		int monthDiff=0;
		bigDate = bigDate.replaceAll("-", "");
		int yyyy = Integer.parseInt(bigDate.substring(0, 4));
		int mm = Integer.parseInt(bigDate.substring(4, 6)) - 1;
		int dd = Integer.parseInt(bigDate.substring(6, 8)) - 1;

		Calendar c = Calendar.getInstance();
		c.set(yyyy, mm, dd);
		
		smallDate = smallDate.replaceAll("-", "");
		int yyyy2 = Integer.parseInt(smallDate.substring(0, 4));
		int mm2 = Integer.parseInt(smallDate.substring(4, 6)) - 1;
		int dd2 = Integer.parseInt(smallDate.substring(6, 8)) - 1;

		Calendar c2 = Calendar.getInstance();
		c2.set(yyyy2, mm2, dd2);
		
		c.add(Calendar.MONTH, -2);
		monthDiff = c.compareTo(c2);
		return monthDiff;
	}
	//두 날짜 사이의 날짜 리스트
	public static ArrayList<String> getDateDiffList(String startDate, String endDate) {
		ArrayList<String> resultArr = new ArrayList<String>();
		
		startDate = startDate.replaceAll("-", "");
		endDate = endDate.replaceAll("-", "");
		
		int syyyy = Integer.parseInt(startDate.substring(0, 4));
		int smm = Integer.parseInt(startDate.substring(4, 6)) - 1;
		int sdd = Integer.parseInt(startDate.substring(6, 8));

		int eyyyy = Integer.parseInt(endDate.substring(0, 4));
		int emm = Integer.parseInt(endDate.substring(4, 6)) - 1;
		int edd = Integer.parseInt(endDate.substring(6, 8));

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        
        cal.set (eyyyy, emm, edd); //시작 날짜 셋팅
        endDate = formatter.format(cal.getTime());
        
        cal.set (syyyy, smm, sdd); //종료 날짜 셋팅
        startDate = formatter.format(cal.getTime());    
        
        int i = 0;
        
        while(!startDate.equals(endDate)){ //다르다면 실행, 동일 하다면 빠져나감
            if(i==0) { //최초 실행 출력
//                System.out.println("최초:"+ startDate);
                resultArr.add(startDate);
            }
            
            cal.add(Calendar.DATE, 1); //1일 더해줌
            startDate = formatter.format(cal.getTime()); //비교를 위한 값 셋팅
            
            //+1달 출력
            resultArr.add(startDate);
            i++;
        }
		
		return resultArr;
	}
	
	
	//yyyymmdd 에서 day 만큼 뺀 날짜
	public static String getDecimalDate(String yyyymmdd, int day) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		
 		Calendar c = Calendar.getInstance();
 		c.set(yyyy, mm, dd);
 		c.add(Calendar.DATE, day);
 		return formatter.format(c.getTime());
	}
	
	//yyyymmdd 에서 day 만큼 뺀 월
	public static String getMonthSubtrack(String yyyymmdd, int month) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		
			Calendar c = Calendar.getInstance();
			c.set(yyyy, mm, dd);
			c.add(Calendar.MONTH, month);
		return formatter.format(c.getTime());
	}
	
	//두 날짜 사이의 월 리스트
	public static ArrayList<String> getMonthDiffList(String startDate, String endDate) {
		ArrayList<String> resultArr = new ArrayList<String>();
		
		startDate = startDate.replaceAll("-", "");
		endDate = endDate.replaceAll("-", "");
		
		int syyyy = Integer.parseInt(startDate.substring(0, 4));
		int smm = Integer.parseInt(startDate.substring(4, 6)) - 1;
		int sdd = Integer.parseInt(startDate.substring(6, 8));
	
		int eyyyy = Integer.parseInt(endDate.substring(0, 4));
		int emm = Integer.parseInt(endDate.substring(4, 6)) - 1;
		int edd = Integer.parseInt(endDate.substring(6, 8));
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();

		cal.set (eyyyy, emm, edd); //종료 날짜 셋팅
		endDate = formatter.format(cal.getTime());
		
		cal.set (syyyy, smm, sdd); //시작 날짜 셋팅
		startDate = formatter.format(cal.getTime());    
		
		int i = 0;
		while(!startDate.equals(endDate)){
            if(i==0) { 
                resultArr.add(startDate);
            }
            if(i>100) {
            	break;
            }
            
            cal.add(Calendar.MONTH, 1);
            startDate = formatter.format(cal.getTime());
            
            resultArr.add(startDate);
            i++;
        }
		return resultArr;
	}

	public static boolean beforeCompareDate(String before, String after) {
		boolean result = false;
		 try {
	            SimpleDateFormat dateFormat = new 
	                SimpleDateFormat ("yyyy-MM-dd");
	            Date date1 = dateFormat.parse(before);
	            Date date2 = dateFormat.parse(after);
	            if(date1.before(date2)){
	            	result = true;
	            } else {
	            	result = false;
	            }
	        } catch (ParseException ex) {
	        }
		return result;
	}
	
	public static String getDateCalculation(String nowDate, int day) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		nowDate = nowDate.replaceAll("-", "");
		int yyyy = Integer.parseInt(nowDate.substring(0, 4));
		int mm = Integer.parseInt(nowDate.substring(4, 6)) - 1;
		int dd = Integer.parseInt(nowDate.substring(6, 8));
		
 		Calendar c = Calendar.getInstance();
 		c.set(yyyy, mm, dd);
 		c.add(Calendar.DATE, day);
		
		return formatter.format(c.getTime());
	}
	
}
