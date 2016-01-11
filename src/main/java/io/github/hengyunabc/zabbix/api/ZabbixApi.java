package io.github.hengyunabc.zabbix.api;

import com.alibaba.fastjson.JSONObject;

public interface ZabbixApi {

	void init();

	void destroy();

	String apiVersion();

	JSONObject call(Request request);

	boolean login(String user, String password);
}
