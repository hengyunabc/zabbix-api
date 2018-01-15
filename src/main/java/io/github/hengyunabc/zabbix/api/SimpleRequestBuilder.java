package io.github.hengyunabc.zabbix.api;

import java.util.Arrays;

public class SimpleRequestBuilder {

	private SimpleRequest request = new SimpleRequest();

	public static SimpleRequestBuilder newBuilder() {
		return new SimpleRequestBuilder();
	}

	public SimpleRequest build() {
		return RequestBuilder.build(request);
	}

	public SimpleRequestBuilder version(String version) {
		RequestBuilder.version(request, version);
		return this;
	}

	/**
	 * Do not necessary to call this method. If don not set id, ZabbixApi will auto set request auth.
	 *
	 * @param auth
	 * @return
	 */
	public SimpleRequestBuilder auth(String auth) {
		RequestBuilder.auth(request, auth);
		return this;
	}

	public SimpleRequestBuilder method(String method) {
		RequestBuilder.method(request, method);
		return this;
	}

	/**
	 * Do not necessary to call this method. If don not set id, RequestBuilder will auto generate.
	 *
	 * @param id
	 * @return
	 */
	public SimpleRequestBuilder id(Integer id) {
		RequestBuilder.id(request, id);
		return this;
	}

	public SimpleRequestBuilder param(Object value) {
		request.addParam(value);
		return this;
	}

	public SimpleRequestBuilder params(Object ... values) {
		request.setParams(Arrays.asList(values));
		return this;
	}

}
