package senai.sstorage.authentication;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import senai.sstorage.api.TemplateController;
import senai.sstorage.dao.UserDAO;
import senai.sstorage.exceptions.BadRequestException;
import senai.sstorage.exceptions.UnauthorizedException;
import senai.sstorage.models.User;

public final class JwtManager {

	private JwtManager() {
		// Static class
	}

	// Token Data
	private static final String TOKEN_SECRET = "senaiMAIORetec", TOKEN_ISSUER = "sstorage", TOKEN_SUBJECT = "SStorage";

	// Claims
	public static final String AUTH0_CLAIM = "auth-level";
	
	// Blacklisted Tokens
	public static final List<String> blackListTokens = new ArrayList<>();

	public static String generateToken(Map<String, String> payloads) {
		Date now = new Date();
		Builder tokenBuilder = JWT
				.create()
				.withIssuer(TOKEN_ISSUER)
				.withSubject(TOKEN_SUBJECT)
				.withIssuedAt(now)
				.withExpiresAt(new Date(now.getTime() + (1 * 60 * 60 * 1000))); // 1 hour to expire
		// Adding payloads
		if(payloads != null) {
			Iterator<String> keyIterator = payloads.keySet().iterator();
			while (keyIterator.hasNext()) {
				String currentKey = keyIterator.next();
				tokenBuilder.withClaim(currentKey, payloads.get(currentKey));
			}
		}
		//
		try {
			return tokenBuilder.sign(Algorithm.HMAC512(TOKEN_SECRET));
		} catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static Authority validateToken(String token, Authority requiredAuth) throws UnauthorizedException {
		if(blackListTokens.contains(token))
			throw new UnauthorizedException("Logout already performed");
		DecodedJWT decoded;
		try {
			decoded = JWT.require(Algorithm.HMAC512(TOKEN_SECRET)).build().verify(token);
		} catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		Authority auth = Authority
				.valueOf(decoded.getClaim(TemplateController.HEADER_USER_AUTH).asString().toUpperCase());
		Date expiresAt = decoded.getExpiresAt();
		if(expiresAt.getTime() < new Date().getTime()) {
			throw new UnauthorizedException("Token expired");
		}
		if(auth.getLevel() < requiredAuth.getLevel()) {			
			throw new UnauthorizedException("Invalid authority level"); 
		}
		return auth;
	}
	
	public static DecodedJWT decodeToken(String token) throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
		return JWT.require(Algorithm.HMAC512(TOKEN_SECRET)).build().verify(token);
	}
	
	public static Long getUserIdFromToken(String token) {
		try {
			DecodedJWT decoded = decodeToken(token);
			return Long.parseLong(decoded.getClaim(TemplateController.HEADER_USER_ID).asString());
		} catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void devalidateToken(String token) throws BadRequestException {
		try {
			if(blackListTokens.contains(token))
				throw new BadRequestException();
			DecodedJWT decoded = JWT.require(Algorithm.HMAC512(TOKEN_SECRET)).build().verify(token);
		} catch(Exception e) {
			throw new BadRequestException("Token already logged out");
		}
		blackListTokens.add(token);
	}

}
