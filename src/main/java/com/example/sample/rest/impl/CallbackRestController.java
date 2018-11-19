package com.example.sample.rest.impl;

import java.io.IOException;
import java.util.*;

import org.codehaus.jackson.map.ObjectMapper; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class CallbackRestController {
	

	// This Endpoint is called when a new tenant subscribes to the application
		@PutMapping(value = "/callback/v1.0/tenants/{tenantID}")
		public String callbackPut(@PathVariable(value = "tenantID") String tenantID, @RequestBody PayloadDataDto payload)
				throws IOException {
			String logMessage = "callback service successfully called with RequestMethod = PUT for tenant " + tenantID + " with payload message = " + payload.toString();
			
			//	logger.info(logMessage);
			ObjectMapper objectMapper = new ObjectMapper();
			return "https://" + payload.getSubscribedSubdomain() + "-mt-hw-spring-ar-app.cfapps.eu10.hana.ondemand.com";
		}
		
		// This Endpoint is called when information about the subscription is requested (For example the dependencies)
		@GetMapping(value = "/callback/v1.0/dependencies")
		public List<DependantServiceDto> callbackGet() {
			String logMessage = "callback service successfully called with RequestMethod = GET for tenant ";
			//logger.info(logMessage);
			List<DependantServiceDto> dependenciesList = new ArrayList<>();
			//List<DependantServiceDto> dependenciesList = Arrays.asList( new DependantServiceDto[] {new DependantServiceDto("sample-saas-reuse-service-[c/d/i-user]", xsappnameService)});
			return dependenciesList;
		}

		// This Endpoint is called when a tenant unsubscribes from the application
		@DeleteMapping(value = "/callback/v1.0/tenants/{tenantID}")
		public void callbackDelete(@PathVariable(value = "tenantID") String tenantID, @RequestBody PayloadDataDto payload) {
			String logMessage = "callback service successfully called with RequestMethod = DELETE for tenant " + tenantID + " with payload message = " + payload.toString();
			//logger.info(logMessage);
		}

}