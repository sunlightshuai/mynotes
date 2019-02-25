package com.hoperun.util;

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
	
	
	public static void main(String[] args) {
		String aa = "{\"Service\":{\"Body\":{\"mrchName\":\"周\"},\"Header\": {\"LocalLang\": \"001\",\"TranTeller\": \"100858\",\"ServiceCode\": \"transferCustApprove\",\"TranDate\": \"20171226\",\"ServiceName\": \"transferCustApprove\",\"TranCode\": \"9487\",\"Channel\": \"01\",\"ConsumerId\": \"001\",\"SourceSysId\": \"001\",\"TranSeq\": \"20171226154614777\",\"GlobalSeq\": \"201712261546147779487\",\"TranTime\": \"154614777\",\"BranchId\":\"01001\",\"LegalRepCode\":\"2324\"}}}";
		Map<String,Object> resultMap = jsonToMap(aa);
		System.out.println(resultMap);
		System.out.println("----------");
		System.out.println(mapToJsonStr(resultMap));
	}
}
