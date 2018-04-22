package com.tools;

import java.util.UUID;

/**
 * 文件上传工具类
 * @author Administrator
 *
 */
public class UploadUtils {

	/**
     * 获取文件真实名称
     * @param name
     * @return
     */
	public static String getRealName(String name) {
		// c:/upload/1.jpg    1.jpg
        //获取最后一个"/"
        int index = name.lastIndexOf("\\");
        return name.substring(index+1);
	}

	/**
	 * 传入文件名称，返回唯一的名称
	 * @param realname
	 * @return
	 */
	public static String getUUIDName(String realname) {
		// 先查找 从后往前找
		int index = realname.lastIndexOf(".");
		// 截取后缀名
		String lastname = realname.substring(index, realname.length());
		// System.out.println(filename);
		// 唯一字符串
		String uuid = UUID.randomUUID().toString().replace("-", "");// 默认带有-

		return uuid + lastname;
	}

	/**
     * 获取文件目录
	 * @param uuidName 
     * @param name 文件名称
     * @return 目录
     */
	public static String getDir(String uuidName) {
		int i = uuidName.hashCode();
        String hex = Integer.toHexString(i);
        int j=hex.length();
        for(int k=0;k<8-j;k++){
            hex="0"+hex;
        }
    //    System.out.println(hex);
        return "/"+hex.charAt(0)+"/"+hex.charAt(1);
	}

}
