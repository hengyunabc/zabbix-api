package io.github.zabbix.api;

import java.util.HashMap;
import java.util.Map;

public class Request {
	String jsonrpc = "2.0";
	Map<String, Object> params = new HashMap<String, Object>();

	String method;

	String auth;

	Integer id;
	
	public void putParam(String key, Object value){
		params.put(key, value);
	}
	
	public Object removeParam(String key){
		return params.remove(key);
	}

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
