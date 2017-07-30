package com.baizhi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * MD5加密的工具类
 *
 */
public class MD5Util {
    public static String getMD5(String pwd) {//获得加密之后的密码
    	try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			//加密
			byte[] bs = messageDigest.digest(pwd.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : bs) {
				//判断进行与运算之后的值是否小于16，若是小于16，则用十六进制表示是一位字符，所以要进行拼接
				int c = b & 0xff;
				if(c<16){
					sb.append("0");
				}
				sb.append(Integer.toHexString(c));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
}
