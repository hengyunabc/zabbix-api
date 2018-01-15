package io.github.hengyunabc.zabbix.api;

import java.util.Arrays;

public class DeleteRequestBuilder {

	private DeleteRequest request = new DeleteRequest();

	public static DeleteRequestBuilder newBuilder() {
		return new DeleteRequestBuilder();
	}

	public DeleteRequest build() {
		return RequestBuilder.build(request);
	}

	public DeleteRequestBuilder version(String version) {
		RequestBuilder.version(request, version);
		return this;
	}

	/**
	 * Do not necessary to call this method. If don not set id, ZabbixApi will auto set request auth.
	 *
	 * @param auth
	 * @return
	 */
	public DeleteRequestBuilder auth(String auth) {
		RequestBuilder.auth(request, auth);
		return this;
	}

	public DeleteRequestBuilder method(String method) {
		RequestBuilder.method(request, method);
		return this;
	}

	/**
	 * Do not necessary to call this method. If don not set id, RequestBuilder will auto generate.
	 *
	 * @param id
	 * @return
	 */
	public DeleteRequestBuilder id(Integer id) {
		RequestBuilder.id(request, id);
		return this;
	}

	public DeleteRequestBuilder param(Object value) {
		request.addParam(value);
		return this;
	}

	public DeleteRequestBuilder params(Object ... values) {
		request.setParams(Arrays.asList(values));
		return this;
	}

}
