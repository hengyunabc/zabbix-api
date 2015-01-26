package io.github.zabbix.api.test;

import io.github.zabbix.api.DefaultZabbixApi;
import io.github.zabbix.api.Request;
import io.github.zabbix.api.RequestBuilder;
import io.github.zabbix.api.ZabbixApi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class DefaultZabbixApiTest {

	ZabbixApi zabbixApi;

	@Before
	public void before() {
		zabbixApi = new DefaultZabbixApi(
				"http://localhost:49155/zabbix/api_jsonrpc.php");
		zabbixApi.init();
	}

	@After
	public void after() {
		zabbixApi.destory();
	}
	
	@Test
	public void testVersion(){
		String version = zabbixApi.apiVersion();
		System.err.println(version);
	}

	@Test
	public void testLogin() {
		String user = "admin";
		String password = "zabbix";
		boolean login = zabbixApi.login(user, password);
		System.out.println("login result:" + login);

		if (login) {
			Request request = RequestBuilder.newBuilder().method("user.get")
					.paramEntry("output", "extend").build();
			JSONObject response = zabbixApi.call(request);
			System.err.println(response);
		}
	}
}
