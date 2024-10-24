package com.praj.hotel.util;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	 @Value("${myapp.jwt.secret}")
	    private String secret;

	    public void validateToken(String token) {
	        Jwts.parserBuilder().setSigningKey(getSignatureKey()).build().parseClaimsJws(token);
	    }
	    
	    private Key getSignatureKey() {
	        // Decode the Base64-encoded secret key
	        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	    }
}
