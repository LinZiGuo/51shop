package com.tools;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class JsonUtil {

	/**
	 * 将数组转换成String类型的JSON数据格式
	 * @param objects
	 * @return
	 */
	public static String array2json(Object[] objects) {
		JSONArray jsonArray = JSONArray.fromObject(objects);
		return jsonArray.toString();
	}
	
	/**
	 * 将list集合转换成String类型的JSON数据格式
	 * @param list
	 * @return
	 */
	public static String list2json(List list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}
	
	/**
	 * 将map集合转换成String类型的JSON数据格式
	 * @param list
	 * @return
	 */
	public static String map2json(Map map) {
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}
	
	/**
	 * 将Object对象转换成String类型的JSON数据格式
	 * @param object
	 * @return
	 */
	public static String object2json(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}
	
	public static JsonConfig configJson(String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(true);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return jsonConfig;
	}

}
