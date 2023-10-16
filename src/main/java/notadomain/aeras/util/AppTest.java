package notadomain.aeras.util;

import java.util.Base64;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class AppTest {

	public static void main(String[] args) {
		//HibernateUtil util = HibernateUtil.getInstance();
		
		
		String keyStr ="Qd2ZtDBas7eVH6tm5ti+eUskmoBPMmhhc+x+dt9c2oU=";
		
		SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(keyStr));
		
		System.out.println(keyStr);
		
		//String jwt = Security.generateJwt("001", key);
		
		String tamperedJwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMDEifQ.cZFkK5XvkbPf5wkxGHRLIsPw_O5e_836z_FZnqOx0B8";
		
		System.out.println(tamperedJwt);
		
		Jws<Claims> jws;
		
		try {
			jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(tamperedJwt);
			Claims claims = jws.getPayload();
			String sub = claims.getSubject();
			System.out.println(sub);
		}catch(JwtException e) {
			System.out.println("Tampered JWT");
		}
		
		
	}

}
