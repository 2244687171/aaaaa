package com.baizhi.util;
/**
 * 获得随机盐的工具类
 */
import java.util.Random;

public class SaltUtil {
	public static String getSalt(){//获取随机盐的方法
   char[] code = "QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm1234567890".toCharArray();
   StringBuilder sb = new StringBuilder();
   Random random = new Random();
   for (int i = 0; i < 4; i++) {
	sb.append(code[random.nextInt(code.length)]);
}
   return sb.toString();
	}
}
