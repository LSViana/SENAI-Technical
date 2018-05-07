package wstemplate.project.deprec_controllers;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

//@Controller
public class JWTController {
	
	public static final String TOKEN_SECRET = "UADHILUDYAS98D7SBG9DAS8 DYSAUDHKJhkjahdso8sadyas8d uasidjulijKSLIaus0a8DU ASIJDASIDAJODJN 0plasijhaoidja bsd98usadjisadoj id";
	public static final String TOKEN_EMISSOR = "Viana Software - RESTFul API - 1.0";
	public static final String TOKEN_ASSUNTO = "Autenticação REST API - 1.0";
	
//	@GetMapping("/token/gerar")
	public String gerarToken(Model model) throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		String token = JWT.
			create()
				.withIssuer(TOKEN_EMISSOR)
				.withSubject(TOKEN_ASSUNTO)
				.withClaim("email", "viana@lindao.com")
				//.withClaim("senha", "123") <- isso não pode
				.withClaim("idade", 17)
				.withIssuedAt(new Date()) //Assina para agora
				//.withExpiresAt(expiresAt) <- Data de expiração
				.sign(Algorithm.HMAC512(TOKEN_SECRET));
		
		model.addAttribute("token", token.replaceAll("\\.", "\\@"));
		
		return "token";
	}
	
//	@GetMapping("/token/validar/{token}")
	public String pegarToken(@PathVariable() String token) throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
		token = token.replaceAll("\\@", "\\.");
		System.out.println(token);
		DecodedJWT decoded = 
		JWT
			.require(Algorithm.HMAC512(TOKEN_SECRET)).build().verify(token);
		//JWT.decode(token);
		
		System.out.println(decoded.getClaim("email").asString());
				
		
		return "token";
	}

}
