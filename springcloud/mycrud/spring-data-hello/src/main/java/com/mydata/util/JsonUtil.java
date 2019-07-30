package com.mydata.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
	
	public static Map<String,Object> jsonToMap (String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr)) {
			throw new NullPointerException("the params is empty!");
		}
		Map<String,Object> resultMap = new Gson().fromJson(jsonStr, new TypeToken<HashMap<String,Object>>(){}.getType());
		return resultMap;
	}
	
	public static String mapToJsonStr (Map<String,Object> inputDataMap) {
		String resultStr = new Gson().toJson(inputDataMap);
		return resultStr;
	}

}
