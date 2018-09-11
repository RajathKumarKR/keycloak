package com.example.productapp.service;

import java.util.Arrays;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
	
	@Autowired
	private KeycloakRestTemplate template;

	
	public ResponseEntity<String[]>  getCustomerInfo() {
		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		KeycloakSecurityContext context = (KeycloakSecurityContext) token.getCredentials();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer " + context.getTokenString());
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		try {
			ResponseEntity<String[]> result=template.exchange("http://localhost:51021/subcustomers",HttpMethod.GET,entity, String[].class);
			System.out.println("After rest call output "+result.getBody());
			return result;
		}catch(Exception e) {
			System.out.println("Error "+e.toString());
			return null;
		}
	}
	
	public ResponseEntity<String[]> getItems(){
		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		KeycloakSecurityContext context = (KeycloakSecurityContext) token.getCredentials();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer " + context.getTokenString());
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		//	headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		try {
			ResponseEntity<String[]> result=template.exchange("http://localhost:51021/subproducts",HttpMethod.GET,entity, String[].class);
			System.out.println("After rest call output "+result.getBody());
			
			return result;
		}catch(Exception e) {
			System.out.println("Error "+e.toString());
			return null;
		}

	}

}
