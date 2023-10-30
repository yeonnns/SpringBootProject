package com.test.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dto.SalaryDto;
import com.test.mapper.UserMapper;
import com.test.utill.Encryption;
import com.test.utill.ExcelUtilCustom;

@Service
public class FileService {


	
	@Autowired
	UserMapper userMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);

	// 급여 엑셀 업로드
	@Transactional
	public void salaryFileUpload(Map<?, ?> returnObject) throws Exception {
		
		int lineCount = 0;
		SalaryDto salaryDto = new SalaryDto(); 
		ArrayList<SalaryDto> salaryList = new ArrayList<>();
		List<String[]> data = ExcelUtilCustom.readCsv(returnObject.get("resultPath").toString());
		Iterator<String[]> it = data.iterator();
		
		while (it.hasNext()) {
			String[] array = (String[]) it.next();
			if (lineCount < 2) {
				lineCount++;
				continue;
			}
			int cursor = 1;
			// break;
			if (array[0].equals("합계")) {
				break;
			}
			Encryption encyp = new Encryption();
			salaryDto = new SalaryDto();
			salaryDto.setUsername(array[cursor++]);
			salaryDto.setDepth1(array[cursor++]);
			salaryDto.setDepth2(array[cursor++]);
			salaryDto.setDepth3(array[cursor++]);
			salaryDto.setSalary(encyp.encrypt(array[cursor++]));
			salaryDto.setGrade(encyp.encrypt(array[cursor++]));
			salaryDto.setIncentive(encyp.encrypt(array[cursor++]));
			salaryDto.setPromotion(encyp.encrypt(array[cursor++]));
			salaryDto.setPosPlus(encyp.encrypt(array[cursor++]));
			salaryDto.setSmileCash(encyp.encrypt(array[cursor++]));
			salaryDto.setEnsion(encyp.encrypt(array[cursor++]));
			salaryDto.setHealthIns(encyp.encrypt(array[cursor++]));
			salaryDto.setEmployIns(encyp.encrypt(array[cursor++]));
			salaryDto.setLongTermIns(encyp.encrypt(array[cursor++]));
			salaryDto.setIncomeTax(encyp.encrypt(array[cursor++]));
			salaryDto.setLoincomeTax(encyp.encrypt(array[cursor++]));
			salaryList.add(salaryDto);
			if (salaryList.size() == 2500) {
				logger.info("급여명세서 Insert 중...");
				userMapper.salaryInsert(salaryList);
				salaryList.clear();
			}
		}
		logger.info("급여명세서 Insert 중...");
		if (salaryList.size() != 0) {
			userMapper.salaryInsert(salaryList);
		}
		
		
		logger.info("급여명세서 Insert 완료...");
		deldeteSal("C://salaryFile");
		logger.info("급여명세서 excel 삭제 완료...");
	
	}
	
	// salaryFile폴더 + 파일 삭제
	public void deldeteSal(String path) {
		File folder = new File(path);
		
		try {
			while(folder.exists()) {
				File[] fileList = folder.listFiles(); 
				int cnt = fileList.length;
				
				for(int i = 0; i<cnt; i++) {
					fileList[i].delete();
				}
				
				if(fileList.length == 0 && folder.isDirectory()){ 
					folder.delete(); //대상폴더 삭제
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SalaryDto> getSalaryList(SalaryDto salDto) throws Exception {
		Encryption encyp = new Encryption();
		ArrayList<SalaryDto> salaryList = userMapper.getSalaryList(salDto);
		int cnt = salaryList.size();
		
		for(int i = 0; i< cnt; i++) {
			salaryList.get(i).setSalary(encyp.decrypt(salaryList.get(i).getSalary()));
			salaryList.get(i).setGrade(encyp.decrypt(salaryList.get(i).getGrade()));
			salaryList.get(i).setIncentive(encyp.decrypt(salaryList.get(i).getIncentive()));
			salaryList.get(i).setPromotion(encyp.decrypt(salaryList.get(i).getPromotion()));
			salaryList.get(i).setPosPlus(encyp.decrypt(salaryList.get(i).getPosPlus()));
			salaryList.get(i).setSmileCash(encyp.decrypt(salaryList.get(i).getSmileCash()));
			salaryList.get(i).setEnsion(encyp.decrypt(salaryList.get(i).getEnsion()));
			salaryList.get(i).setHealthIns(encyp.decrypt(salaryList.get(i).getHealthIns()));
			salaryList.get(i).setEmployIns(encyp.decrypt(salaryList.get(i).getEmployIns()));
			salaryList.get(i).setLongTermIns(encyp.decrypt(salaryList.get(i).getLongTermIns()));
			salaryList.get(i).setIncomeTax(encyp.decrypt(salaryList.get(i).getIncomeTax()));
			salaryList.get(i).setLoincomeTax(encyp.decrypt(salaryList.get(i).getLoincomeTax()));
		}
		return salaryList; 
	}
	
}