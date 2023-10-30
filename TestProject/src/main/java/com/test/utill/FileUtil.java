package com.test.utill;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	public static Map salesFileUpload(HttpServletRequest req, HttpServletResponse rep, String name) {
		String path = "";
		if(name.equals("salaryFile")) {
			path = "C://"+name+"//";
		}

		Map returnObject = new HashMap();
		String resultPath = "";
		
		try { 
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
			//UTF8
			mhsr.setCharacterEncoding("utf-8");
			Iterator iter = mhsr.getFileNames();
			MultipartFile mfile = null;
			String fieldName = "";
			List resultList = new ArrayList();
			File dir = new File(path);
			
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}
		
			while (iter.hasNext()) {
				mhsr.setCharacterEncoding("utf-8");
				fieldName = iter.next().toString(); 
				mfile = mhsr.getFile(fieldName);
				String origName;
				origName = new String(mfile.getOriginalFilename()); 
				//origName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "utf-8"); 
				//origName = URLEncoder.encode(origName);
				if ("".equals(origName)) {
					continue;
				} 
				File serverFile = new File(path + File.separator + origName);
				
				
				String saveFileName="";

					int count = 0;
					while(serverFile.exists()) {
						count++;
						int len = origName.lastIndexOf(".");
						String ext = origName.substring(len); // 확장자		
						String preName = origName.substring(0, len);
						saveFileName = preName + "_" + count + ext;// oriname + count + ext;  파일저장 
						serverFile = new File(path + File.separator + saveFileName);
					}
					mfile.transferTo(serverFile);
					
					resultPath = serverFile.getAbsolutePath();
					int nameSize = origName.split("_").length;
					returnObject.put("resultPath", resultPath);
				}
			
		} catch (UnsupportedEncodingException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

}
