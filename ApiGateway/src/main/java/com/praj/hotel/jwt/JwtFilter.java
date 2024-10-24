package com.praj.hotel.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.praj.hotel.util.JwtUtil;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {
	
	@Autowired
	private RouteValidator routeValidator;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public JwtFilter() {
		super(Config.class);
	}
	
	public static class Config {
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if(routeValidator.isSecured.test(exchange.getRequest())) {
//				headers contains token or not
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException(" missing authorization headers");
				}
				
//				If it contains get that header
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeader!=null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}
				try {
//					Rest call the Auth-Service
					jwtUtil.validateToken(authHeader);
			
				} catch (Exception e) {
					System.out.println("invalid access....!");
					throw new RuntimeException("un-authorize access to the application!");
				}
			}
			return chain.filter(exchange);
		});
	}

}
