package com.example.sample.rest.impl;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayloadDataDto {

	private String subscriptionAppName;
	private String subscriptionAppId;
	private String subscribedTenantId;
	private String subscribedSubdomain;
	private String subscriptionAppPlan;
	private long subscriptionAppAmount;
	private String[] dependantServiceInstanceAppIds = null;

	private Map<String, String> additionalInformation;

	public PayloadDataDto() {}

	public PayloadDataDto(String subscriptionAppName, String subscriptionAppId,
			String subscribedTenantId, String subscribedSubdomain, String subscriptionAppPlan,
			Map<String, String> additionalInformation) {
		this.subscriptionAppName = subscriptionAppName;
		this.subscriptionAppId = subscriptionAppId;
		this.subscribedTenantId = subscribedTenantId;
		this.subscribedSubdomain = subscribedSubdomain;
		this.subscriptionAppPlan = subscriptionAppPlan;
		this.additionalInformation = additionalInformation;

	}

	public String getSubscriptionAppName() {
		return subscriptionAppName;
	}

	public void setSubscriptionAppName(String subscriptionAppName) {
		this.subscriptionAppName = subscriptionAppName;
	}

	public String getSubscriptionAppId() {
		return subscriptionAppId;
	}

	public void setSubscriptionAppId(String subscriptionAppId) {
		this.subscriptionAppId = subscriptionAppId;
	}

	public String getSubscribedTenantId() {
		return subscribedTenantId;
	}

	public void setSubscribedTenantId(String subscribedTenantId) {
		this.subscribedTenantId = subscribedTenantId;
	}

	public String getSubscribedSubdomain() {
		return subscribedSubdomain;
	}

	public void setSubscribedSubdomain(String subscribedSubdomain) {
		this.subscribedSubdomain = subscribedSubdomain;
	}

	public String getSubscriptionAppPlan() {
		return subscriptionAppPlan;
	}

	public void setSubscriptionAppPlan(String subscriptionAppPlan) {
		this.subscriptionAppPlan = subscriptionAppPlan;
	}

	public Map<String, String> getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(Map<String, String> additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public long getSubscriptionAppAmount() {
		return subscriptionAppAmount;
	}

	public void setSubscriptionAppAmount(long subscriptionAppAmount) {
		this.subscriptionAppAmount = subscriptionAppAmount;
	}

	public String[] getDependantServiceInstanceAppIds() {
		return dependantServiceInstanceAppIds;
	}

	public void setDependantServiceInstanceAppIds(
			String[] dependantServiceInstanceAppIds) {
		this.dependantServiceInstanceAppIds = dependantServiceInstanceAppIds;
	}

	public String toString() {
		return String.format("Payload data: subscriptionAppName=%s, subscriptionAppId=%s, subscribedTenantId=%s,"
				+ "subscribedSubdomain=%s subscriptionAppPlan=%s subscriptionAppAmount=%s dependantServiceInstanceAppIds=%s", this.subscriptionAppName, this.subscriptionAppId,
				this.subscribedTenantId, this.subscribedSubdomain, this.subscriptionAppPlan, this.getSubscriptionAppAmount(), dependantServiceInstanceAppIds);
	}
}