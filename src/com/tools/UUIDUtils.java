package com.tools;

import java.util.UUID;

public class UUIDUtils {

	/**
	 * 随机生成ID
	 * @return
	 */
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 生成随机码
	 * @return
	 */
	public static String getCode() {
		return getId();
	}
}
