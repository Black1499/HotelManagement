package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @author 杨万生
 *
 */
public class MD5 {
	/**
	 * 
	 * @param password 密码，需要加密的字段
	 * @return 返回已经加密了的文字
	 */
	public static String getMD5(String password){
	    try {
	        // 得到一个信息摘要器
	        MessageDigest digest = MessageDigest.getInstance("md5");
	        byte[] result = digest.digest(password.getBytes());
	        StringBuffer buffer = new StringBuffer();
	        // 把每一个byte 做一个与运算 0xff;
	        for (byte b : result) {
	            // 与运算
	            int number = b & 0xff;//加密
	            String str = Integer.toHexString(number);
	            if (str.length() == 1) {
	                buffer.append("0");
	            }
	            buffer.append(str);
	        }
	        // 标准的md5加密后的结果
	        return buffer.toString();
	    }catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        return "";
	    }
	}
}
