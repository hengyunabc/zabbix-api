package io.github.hengyunabc.zabbix.api;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestBuilder {
	private static final AtomicInteger nextId = new AtomicInteger(1);

	private Request request = new Request();

	private RequestBuilder() {}

	public static RequestBuilder newBuilder() {
		return new RequestBuilder();
	}

	public Request build() {
		return build(request);
	}

	static <R extends AbstractRequest> R build(R request) {
		if (request.getId() == null) {
			request.setId(nextId.incrementAndGet());
		}
		return request;
	}

	public RequestBuilder version(String version) {
		version(request, version);
		return this;
	}

	static <R extends AbstractRequest> void version(R request, String version) {
		request.setJsonrpc(version);
	}

	/**
	 * Do not necessary to call this method. If don not set id, ZabbixApi will auto set request auth.
	 *
	 * @param auth
	 * @return
	 */
	public RequestBuilder auth(String auth) {
		auth(request, auth);
		return this;
	}

	static <R extends AbstractRequest> void auth(R request, String auth) {
		request.setAuth(auth);
	}

	public RequestBuilder method(String method) {
		method(request, method);
		return this;
	}

	static <R extends AbstractRequest> void method(R request, String method) {
		request.setMethod(method);
	}

	/**
	 * Do not necessary to call this method. If don not set id, RequestBuilder will auto generate.
	 *
	 * @param id
	 * @return
	 */
	public RequestBuilder id(Integer id) {
		id(request, id);
		return this;
	}

	static <R extends AbstractRequest> void id(R request, Integer id) {
		request.setId(id);
	}

	public RequestBuilder paramEntry(String key, Object value) {
		request.putParam(key, value);
		return this;
	}
}
