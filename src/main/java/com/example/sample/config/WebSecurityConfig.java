package com.example.sample.config;

import static org.springframework.http.HttpMethod.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
public class WebSecurityConfig extends ResourceServerConfigurerAdapter{

	@Value("${VCAP_SERVICES}")
	private String vcap;
	private String xsappname;
	// = "sampleapplication!t2160"; //Hard coded for now


	@Override
	public void configure(HttpSecurity http) throws Exception {
		// Callback scope needed for tenant onboarding to access callbacks
				// String callbackScope = xsappname + ".Callback";
				// JSONObject vcap_object = new JSONObject(vcap);
				// JSONArray xs = vcap_object.getJSONArray("xsuaa");
				// xsappname = JsonPath.read(vcap, "$.xsuaa[0].credentials.xsappname");// xs.getJSONObject(0).getJSONObject("credentials").getString("xsappname");
				// // User scope, used to access app
				// String userScope = xsappname + ".User";

				// String hasScopeCallback = "#oauth2.hasScope('" + callbackScope + "')";
				// String hasScopeUser = "#oauth2.hasScope('" + userScope + "')";

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
