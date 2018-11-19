package com.example.sample.rest.impl;

import javax.validation.constraints.NotNull;

public class DependantServiceDto {

	@NotNull
	private String appName;

	@NotNull
	private String appId;

	public DependantServiceDto() {}

	public DependantServiceDto(String appName, String appId) {
		this.appName = appName;
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof DependantServiceDto))
			return false;

		DependantServiceDto that = (DependantServiceDto) o;

		if (!appName.equals(that.appName))
			return false;
		return appId.equals(that.appId);
	}

	@Override public int hashCode() {
		int result = appName.hashCode();
		result = 31 * result + appId.hashCode();
		return result;
	}
}