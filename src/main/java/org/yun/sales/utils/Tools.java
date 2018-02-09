package org.yun.sales.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 工具类
 */
public class Tools {

	/**
	 * 计算MD5值
	 * @param str 需要计算的字符串
	 * @return MD5值
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String MD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes("utf-8"));
		BigInteger bigInt = new BigInteger(1, md.digest());
		return bigInt.toString(16);
	}
	
	/**
	 * 获取随机数
	 * @param min 随机数范围(包含)
	 * @param max 随机数范围(不包含)
	 * @return 随机数
	 */
	public static int getRandomNum(int min, int max) {
		int num = ThreadLocalRandom.current().nextInt(min, max);
		return num;
	}
	
	/**
	 * 将Java对象转成Json字符串
	 * @param obj java对象
	 * @return json字符串
	 * @throws Exception
	 */
	public static String getJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper(); 
		String json = mapper.writeValueAsString(obj);
		return json;
	}

}
