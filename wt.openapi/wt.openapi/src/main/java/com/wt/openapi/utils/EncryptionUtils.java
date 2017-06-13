package com.wt.openapi.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

public class EncryptionUtils {

	private HashMap<String, String> encryptData = new HashMap<String, String>();

	public HashMap<String, String> encryption(String str) throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		String randomNum = new Integer(random.nextInt()).toString();
        
		// salt key create
		MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md5Digest =  md5.digest(randomNum.getBytes());
		BigInteger number = new BigInteger(1, md5Digest);
		
        String saltKey = number.toString(16);
        
        // encrypt pw
        String encryptionStr = getSHA256Digest(saltKey+str);
		
		encryptData.put("saltKey", saltKey);
		encryptData.put("encryptionStr", encryptionStr);
		
		return encryptData;
	}
	
	public String getSHA256Digest(String input) throws NoSuchAlgorithmException{
		StringBuffer encryptData = new StringBuffer();
		
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		sha.update(input.getBytes());
		
		byte[] messageDigest = sha.digest();
		for(int i=0; i<messageDigest.length; i++){
			encryptData.append(Integer.toHexString(messageDigest[i] & 0xFF));
		}
		
		return encryptData.toString();
	}
	
	public String getTempPassword() throws Exception {
		String password = "";
		String mark[] = {"!", "@", "#", "$", "%", "^", "*", "=", "-"};
		for(int i = 0; i < 8; i++){
			char lowerStr = (char)(Math.random() * 26 + 97);
			if(i%4 == 0){
				password += (int)(Math.random() * 10);
			} else if (i%7 == 0) {
				password += mark[(int)(Math.random() * 10)];
			}else {
				password += lowerStr;
			}
		}
		return password;
	}
}
