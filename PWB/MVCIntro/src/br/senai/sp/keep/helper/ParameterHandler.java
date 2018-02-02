package br.senai.sp.keep.helper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ParameterHandler {
	
	private Map<String, String[]> map;
	private Map<String, String> parameterMap;

	public ParameterHandler(HttpServletRequest req) {
		this.map = req.getParameterMap();
		this.parameterMap = new HashMap<String, String>();
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		//
		while(iterator.hasNext()) {
			String key = iterator.next();
			// Getting value for the @key variable
			String value = map.get(key)[0];
			// Setting key and value at HashMap
			parameterMap.put(key, value);
		}
	}
	
	public Boolean containsKey(String key) {
		return parameterMap.containsKey(key);
	}
	
	public String getString(String key) {
		if(!containsKey(key))
			return null;
		return parameterMap.get(key);
	}
	
	public Boolean getBoolean(String key) {
		return containsKey(key);
	}
	
	public Integer getInteger(String key) {
		if(!containsKey(key))
			return null;
		Integer result = null;
		String resultString = parameterMap.get(key);
		result = Integer.parseInt(resultString);
		return result;
	}
	
	public LocalDateTime getLocalDateTime(String key) {
		if(!containsKey(key))
			return null;
		return LocalDateTime.parse(parameterMap.get(key));
	}
	
	public Map<String, String> getMap() {
		return parameterMap;
	}
}
