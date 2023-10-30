package com.test.utill;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public class Encryption {
	// properties로 빼기 -  32byte
	private static final String key = "kimsoyeon1996aes256encryptdecryp"; 
	// 초기 평문블록을 암호화 할 때 - 한 단계 앞의 암호문블록
	private final String iv = key.substring(0, 16); 
	public static String alg = "AES/CBC/PKCS5Padding";
   
	public String encrypt(String sal) throws Exception {
		if(sal.equals("") || sal.equals("0")) {
			return "0";
		}else {
			Cipher cipher = Cipher.getInstance(alg);
			byte[] keyData = key.getBytes();
	        SecretKeySpec keySpec = new SecretKeySpec(keyData, "AES");
	        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
	        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
	
	        byte[] encrypted = cipher.doFinal(sal.getBytes("UTF-8"));
	        return Base64.getEncoder().encodeToString(encrypted);
	   }
	}

	public String decrypt(String cipherSal) throws Exception {
    	if(cipherSal.equals("") || cipherSal.equals("0")) {
    		return "0";
    		
    	}else {
	        Cipher cipher = Cipher.getInstance(alg);
	        byte[] keyData = key.getBytes();
	        SecretKeySpec keySpec = new SecretKeySpec(keyData, "AES");
	        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
	        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
	
	        byte[] decodedBytes = Base64.getDecoder().decode(cipherSal);
	        byte[] decrypted = cipher.doFinal(decodedBytes);
	        return new String(decrypted, "UTF-8");
	    	}
    }
    //random 생성	
	public String getIv() {
		return iv;
	
	}
}