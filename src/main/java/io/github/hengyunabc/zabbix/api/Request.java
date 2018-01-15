package io.github.hengyunabc.zabbix.api;

import java.util.HashMap;
import java.util.Map;

public class Request extends AbstractRequest {
	private Map<String, Object> params = new HashMap<>();

	public void putParam(String key, Object value) {
		params.put(key, value);
	}

	public Object removeParam(String key) {
		return params.remove(key);
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
