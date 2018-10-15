package com.example.sample.rest.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.xs2.security.container.SecurityContext;
import com.sap.xs2.security.container.UserInfo;

@RestController
public class ExampleRestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	Optional<OAuth2RestOperations> restTemplate;

	@Autowired
	@Qualifier(value = "vcap")
	Optional<Object> vcap;

	@GetMapping(path = "/", produces = { "text/html" })
	public String init() {
		Optional<UserInfo> userInfo = obtainUserInfo();
		return buildHelloMessage(userInfo);
	}

	@GetMapping(path = "/test", produces = { "text/html" })
	public String test() {
		return "Test Rest Endpoint";
	}

	private String buildHelloMessage(Optional<UserInfo> userInfo) {
		StringBuffer helloMessage = new StringBuffer("Hello ");
		userInfo.ifPresent(info -> consumeUserInfo(info, helloMessage));
		return helloMessage.toString();
	}

	private void consumeUserInfo(UserInfo userInfo, StringBuffer helloMessage) {
		// Use xs security lib to read user id and tenant id from security context
		try {
			helloMessage.append(userInfo.getLogonName());
			helloMessage.append("\n");

			helloMessage.append("; your tenant sub-domain is ");
			String subdomain = userInfo.getSubdomain();
			helloMessage.append(subdomain);

			helloMessage.append("; your tenant zone id is ");
			String idzone = userInfo.getIdentityZone();
			helloMessage.append(idzone);

		} catch (Exception e) {
			logger.error("Failed to build Hello message", e);
		}
	}

	private Optional<UserInfo> obtainUserInfo() {
		UserInfo userInfo = null;
		try {
			userInfo = SecurityContext.getUserInfo();
		} catch (Exception e) {
			logger.error("Failed to obtain user info", e);
		}
		return Optional.ofNullable(userInfo);
	}
}
