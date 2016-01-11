package io.github.hengyunabc.zabbix.api;

import com.alibaba.fastjson.JSONObject;

public interface ZabbixApi {

	void init();

	void destory();

	String apiVersion();

	JSONObject call(Request request);

	boolean login(String user, String password);
}
