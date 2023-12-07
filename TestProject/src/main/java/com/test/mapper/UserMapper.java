package com.test.mapper;

import java.util.ArrayList;

import com.test.dto.SalaryDto;
import com.test.dto.SalesDto;
import com.test.dto.UserDto;

public interface UserMapper {

	public UserDto UserInfo(String username);
	public int userInsert(UserDto userDto);
	public ArrayList<SalesDto> SalesInfo();
	public int[] daySales();
	public int[] monthSales();
	public ArrayList<UserDto> latestJoin();
	// salary
	public int salaryInsert(ArrayList<SalaryDto> list);
	public ArrayList<SalaryDto> getSalaryList(SalaryDto salDto);

	

}
