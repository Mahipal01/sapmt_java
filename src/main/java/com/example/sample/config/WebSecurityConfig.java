package com.example.sample.config;

import static org.springframework.http.HttpMethod.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import com.sap.xs2.security.commons.SAPOfflineTokenServicesCloud;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class WebSecurityConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// @formatter:off
	http.sessionManagement()
		// session is created by approuter
		.sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
		// demand authentication
		.anonymous().disable()
		// demand specific scopes depending on intended request
		.authorizeRequests()
		// enable OAuth2 checks
		.accessDecisionManager(accessDecisionManagerBean())
		.antMatchers(GET, "/").permitAll()
		.antMatchers(GET, "/test").permitAll()
		.anyRequest().denyAll(); // deny;
	}

	@Bean
	protected static SAPOfflineTokenServicesCloud offlineTokenServicesBean() {
		return new SAPOfflineTokenServicesCloud();
	}

	@Bean
	protected AccessDecisionManager accessDecisionManagerBean() {
		List<AccessDecisionVoter<?>> voterList = new ArrayList<>();
		WebExpressionVoter expressionVoter = new WebExpressionVoter();
		expressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());
		voterList.add(expressionVoter);
		voterList.add(new AuthenticatedVoter());
		return new UnanimousBased(voterList);
	}

}
