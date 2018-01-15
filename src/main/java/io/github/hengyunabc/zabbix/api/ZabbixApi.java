package io.github.hengyunabc.zabbix.api;

import com.alibaba.fastjson.JSONObject;

public interface ZabbixApi {

	void init();

	void destroy();

	String apiVersion();

	<R extends AbstractRequest> JSONObject call(R request);

	boolean login(String user, String password);

}
