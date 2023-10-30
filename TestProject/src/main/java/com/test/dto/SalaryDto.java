package com.test.dto;

import lombok.Data;

@Data
public class SalaryDto {

		private String username;
		private String depth1;
		private String depth2;
		private String depth3;

		private String salary;
		private String grade;
		private String incentive;
		private String promotion;
		private String posPlus;
		private String smileCash;
		
		private String ension; // 국민연금
		private String healthIns; // 건강보험 
		private String employIns;	// 고용보험
		private String longTermIns; // 장기요양보험료
		private String incomeTax; // 소득세
		private String loincomeTax; // 지방소득세
		
		private String startDate;
		private String EndDate;
		
		private String nowMonth;
		
		
		private String createDate;
}
