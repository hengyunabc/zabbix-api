# zabbix-api
zabbix-api for java

https://www.zabbix.com/wiki/doc/api

https://www.zabbix.com/documentation/2.4/manual/api/reference/user/login

##Info
API is simple, beacuse java can not process json like dynamic language. 

You can build you own ```Request``` Object.

```java
public interface ZabbixApi {

	public void init();

	public void destory();

	public String apiVersion();

	public JSONObject call(Request request);

	public boolean login(String user, String password);
}
```

##Example
```java
		boolean login = zabbixApi.login("zabbix.dev", "goK0Loqua4Eipoe");
		System.err.println("login:" + login);
		
		String host = "192.168.66.29";
		JSONObject filter = new JSONObject();
		
		filter.put("host", new String[] { host });
		Request getRequest = RequestBuilder.newBuilder()
				.method("host.get").paramEntry("filter", filter)
				.build();
		JSONObject getResponse = zabbixApi.call(getRequest);
		System.err.println(getResponse);
		String hostid = getResponse.getJSONArray("result")
				.getJSONObject(0).getString("hostid");
		System.err.println(hostid);
```

