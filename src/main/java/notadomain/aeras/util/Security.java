package notadomain.aeras.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import notadomain.aeras.exception.InvalidTokenException;

public final class Security {
	
	private Security() {}
	
	public static String generateSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		
		return Base64.getUrlEncoder().withoutPadding().encodeToString(salt);
	}
	
	public static String generatePasswordHash(String password, String salt) {
		byte[] saltbytes = Base64.getUrlDecoder().decode(salt);

		String hashed = Base64.getUrlEncoder().withoutPadding()
				.encodeToString(generateKey(password, saltbytes).getEncoded());
		
		return hashed;
	}
	
	public static String generateJwt(String subject, SecretKey key) {
		// TODO How to generate/store securely private key
		JwtBuilder builder = Jwts.builder();
		Date exp = new Date();
		System.out.println("Token create date " + exp.toString());
		Instant ins = exp.toInstant();
		ins = ins.plusSeconds(5*60);
		exp = Date.from(ins);
		System.out.println("Token supposed expiry date " + exp.toString());
		String token = builder.subject(subject).expiration(exp).signWith(key).compact();
		return token;
	}
	
	public static String verifyJwt(String jwt, SecretKey key) throws InvalidTokenException{
		 
		Jws<Claims> jws;
		Claims claims;
		try {
			jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt);
			claims = jws.getPayload();
			return claims.getSubject();
		} catch (JwtException e) {
			throw new InvalidTokenException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}
	
	private static SecretKey generateKey(String password, byte[] salt) {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65535, 256);

		SecretKey key = null;
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			key = factory.generateSecret(spec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return key;
	}
	
}
