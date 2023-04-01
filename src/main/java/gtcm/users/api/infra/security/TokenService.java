package gtcm.users.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import gtcm.users.api.model.User;

@Service
public class TokenService {
	
	private static final Logger log = LoggerFactory.getLogger(TokenService.class);
	
	@Value("${api.security.token.password}")
	private String password;
	
	public String generateToken(User user) {
		try {
			log.info("START");
		    var algorithm = Algorithm.HMAC256(password);
		    String token = JWT.create()
			        .withIssuer("gtcm.users api")
			        .withSubject(user.getEmail())
			        .withClaim("isAdmin", user.getIsAdmin())
			        .withExpiresAt(expDate())
			        .sign(algorithm);
		    log.info("END - token: {}", token);
		    return token;
		} catch (JWTCreationException error) {
			log.error(error.getMessage());
		    throw new RuntimeException("Erro ao gerar token jwt");
		}
	}

	private Instant expDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
