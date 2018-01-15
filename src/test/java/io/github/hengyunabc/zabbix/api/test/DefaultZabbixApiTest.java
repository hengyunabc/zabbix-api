package io.github.hengyunabc.zabbix.api.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.DeleteRequest;
import io.github.hengyunabc.zabbix.api.DeleteRequestBuilder;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

public class DefaultZabbixApiTest {

	private ZabbixApi zabbixApi;

	@Before
	public void before() {
		String url = "http://localhost:49156/zabbix/api_jsonrpc.php";

		url = "http://192.168.90.102/zabbix/api_jsonrpc.php";

		zabbixApi = new DefaultZabbixApi(url);
		zabbixApi.init();
	}

	@After
	public void after() {
		zabbixApi.destroy();
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
			System.err.println(JSON.toJSONString(response, true));
		}
	}

	@Test
	public void testHostGet() {
		boolean login = zabbixApi.login("zabbix.dev", "goK0Loqua4Eipoe");
		System.err.println("login:" + login);

		String host = "192.168.66.29";
		JSONObject filter = new JSONObject();

		filter.put("host", new String[] { host });
		Request getRequest = RequestBuilder.newBuilder().method("host.get")
				.paramEntry("filter", filter).build();
		JSONObject getResponse = zabbixApi.call(getRequest);
		System.err.println(getResponse);
		String hostid = getResponse.getJSONArray("result").getJSONObject(0)
				.getString("hostid");
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
				.paramEntry("interfaceid", interfaceid).build();

		System.err.println(JSON.toJSONString(request));

		JSONObject result = zabbixApi.call(request);

		System.err.println(JSON.toJSONString(result, true));
	}

	@Test
	public void testItemDelete() {
		boolean login = zabbixApi.login("zabbix.dev", "goK0Loqua4Eipoe");
		System.err.println("login:" + login);
		String name = "testItem2";
		String key = name;
		String hostid = "10180";
		int type = 2; // trapper
		int value_type = 0; // float
		int delay = 30;

		String interfaceid = "123";

		Request createRequest = RequestBuilder.newBuilder().method("item.create")
				.paramEntry("name", name).paramEntry("key_", key)
				.paramEntry("hostid", hostid).paramEntry("type", type)
				.paramEntry("value_type", value_type)
				.paramEntry("delay", delay)
				.paramEntry("interfaceid", interfaceid).build();

		System.err.println(JSON.toJSONString(createRequest));

		JSONObject createResponse = zabbixApi.call(createRequest);

		System.err.println(JSON.toJSONString(createResponse, true));

		JSONObject getItemFilter = new JSONObject();
		getItemFilter.put("key_", key);
		Request getRequest = RequestBuilder.newBuilder().method("item.get")
				.paramEntry("output", "extend")
				.paramEntry("hostids", hostid)
				.paramEntry("sortfield", "name")
				.paramEntry("filter", getItemFilter)
				.build();

		System.err.println(JSON.toJSONString(getRequest));

		JSONObject getResponse = zabbixApi.call(getRequest);

		System.err.println(JSON.toJSONString(getResponse, true));

		if (!getResponse.getJSONArray("result").isEmpty()) {
			String itemid = getResponse.getJSONArray("result").getJSONObject(0).getString("itemid");
			System.err.println("Created item id = " + itemid);

			if (itemid != null) {
				DeleteRequest deleteRequest = DeleteRequestBuilder.newBuilder().method("item.delete")
						.param(itemid)
						.build();

				System.err.println(JSON.toJSONString(deleteRequest));

				JSONObject deleteResponse = zabbixApi.call(deleteRequest);

				System.err.println(JSON.toJSONString(deleteResponse, true));
			}
		}
	}

	@Test
	public void testGetTrigger() {
		boolean login = zabbixApi.login("zabbix.dev", "goK0Loqua4Eipoe");
		System.err.println("login:" + login);

		Request request = RequestBuilder.newBuilder().method("trigger.get")
				.paramEntry("triggerids", 2322).paramEntry("output", "extend")
				.paramEntry("selectFunctions", "extend").build();

		System.err.println(JSON.toJSONString(request));

		JSONObject result = zabbixApi.call(request);

		System.err.println(JSON.toJSONString(result, true));
	}

}
