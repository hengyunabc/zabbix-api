package io.github.hengyunabc.zabbix.api;

import java.util.ArrayList;
import java.util.List;

public class DeleteRequest extends AbstractRequest {
	private List<Object> params = new ArrayList<>();

	public void addParam(Object value) {
		params.add(value);
	}

	public Object removeParam(Object value) {
		return params.remove(value);
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}
}
