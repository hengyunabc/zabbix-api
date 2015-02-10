package io.github.hengyunabc.zabbix.api.test;

import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class DefaultZabbixApiTest {

	ZabbixApi zabbixApi;

	@Before
	public void before() {
		String url = "http://localhost:49156/zabbix/api_jsonrpc.php";

		url = "http://192.168.90.102/zabbix/api_jsonrpc.php";

		zabbixApi = new DefaultZabbixApi(url);
		zabbixApi.init();
	}

	@After
	public void after() {
		zabbixApi.destory();
	}

	@Test
	public void testVersion() {
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
	
	@Test
	public void testHostGet(){
		boolean login = zabbixApi.login("zabbix.dev", "goK0Loqua4Eipoe");
		System.err.println("login:" + login);
		
		String host = "192.168.66.29xx";
		JSONObject filter = new JSONObject();
		
		filter.put("host", new String[] { host });
		Request getRequest = RequestBuilder.newBuilder()
				.method("host.get").paramEntry("filter", filter)
//				.paramEntry("output", "extend")
				.build();
		JSONObject getResponse = zabbixApi.call(getRequest);
		System.err.println(getResponse);
		String hostid = getResponse.getJSONArray("result")
				.getJSONObject(0).getString("hostid");
		System.err.println(hostid);
	}

	@Test
	public void testItemCreate() {
		
		boolean login = zabbixApi.login("zabbix.dev", "goK0Loqua4Eipoe");
		System.err.println("login:" + login);
		String name = "testItem";
		String key = name;
		String hostid = "10180";
		int type = 2; // trapper
		int value_type = 0; // float
		int delay = 30;
		
		String interfaceid = "123";

		Request request = RequestBuilder.newBuilder().method("item.create")
				.paramEntry("name", name).paramEntry("key_", key)
				.paramEntry("hostid", hostid).paramEntry("type", type)
				.paramEntry("value_type", value_type)
				.paramEntry("delay", delay)
				.paramEntry("interfaceid", interfaceid)
				.build();
		
		System.err.println(JSON.toJSONString(request));
		
		JSONObject result = zabbixApi.call(request);

		System.err.println(result);

	}
}
