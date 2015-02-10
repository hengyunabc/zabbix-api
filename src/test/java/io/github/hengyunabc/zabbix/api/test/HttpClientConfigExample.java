package io.github.hengyunabc.zabbix.api.test;

import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientConfigExample{

	public static void main(String[] args) {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(5 * 1000).setConnectionRequestTimeout(5 * 1000)
				.setSocketTimeout(5 * 1000).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(); 
		
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connManager)
				.setDefaultRequestConfig(requestConfig).build();
		
		ZabbixApi zabbixApi = new DefaultZabbixApi(
				"http://localhost:10051/zabbix/api_jsonrpc.php", httpclient);
		zabbixApi.init();
		
		String apiVersion = zabbixApi.apiVersion();
		
		System.out.println("api version:" + apiVersion);
		
		zabbixApi.destory();
	}
}
