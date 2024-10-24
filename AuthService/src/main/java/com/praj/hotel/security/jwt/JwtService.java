package com.praj.hotel.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
//	@Value("${myapp.jwt.secret}")
//	private String secret;
//	
//	public static final String  SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
//	
//	public void validateToken(String token) {
//		Jwts.parserBuilder().setSigningKey(getSignatureKey()).build().parseClaimsJws(token);
//	}
//	
//	public String generateToken(String userName) {
//		Map<String, Object> claims = new HashMap<>();
//		return createToken(claims , userName);
//	}
//	
//	
//	private String createToken(Map<String, Object>  claims, String userName) {
//	return 	Jwts.builder()
//		.setClaims(claims)
//		.setSubject(userName)
//		.setIssuedAt(new Date(System.currentTimeMillis()))
//		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
//		.signWith(getSignatureKey() , SignatureAlgorithm.HS256)
//		.compact();
//	}
//	
//	private Key getSignatureKey() { //key is an interface 
//		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
//	}
//	
//	public String getUsername(String token) {
//		return parseJWT(token).getSubject();
//	}
//	
//	private Claims parseJWT(String token) {
//	 return Jwts.parserBuilder().setSigningKey(getSignatureKey()).build()
//		.parseClaimsJws(token).getBody();
//	}
//	
//	public Date getissueDate(String token) {
//		return parseJWT(token).getIssuedAt();
//	}
	
    @Value("${myapp.jwt.secret}")
    private String secret;

    public void validateToken(String token) {
        Jwts.parserBuilder().setSigningKey(getSignatureKey()).build().parseClaimsJws(token);
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 minutes expiration
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignatureKey() {
        // Decode the Base64-encoded secret key
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String getUsername(String token) {
        return parseJWT(token).getSubject();
    }

    private Claims parseJWT(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignatureKey()).build()
                .parseClaimsJws(token).getBody();
    }

    public Date getIssueDate(String token) {
        return parseJWT(token).getIssuedAt();
    }

}
