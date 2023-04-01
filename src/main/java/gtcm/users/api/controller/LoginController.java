package gtcm.users.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gtcm.users.api.infra.security.TokenService;
import gtcm.users.api.infra.security.model.TokenResponse;
import gtcm.users.api.model.LoginRequest;
import gtcm.users.api.model.User;
import gtcm.users.api.model.db.UserDB;
import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
@SuppressWarnings("rawtypes")
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity login(@RequestBody @Valid LoginRequest request) {
		log.info("START - request: {}", request);
		var authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		var auth = manager.authenticate(authToken);
		String token = tokenService.generateToken(new User((UserDB) auth.getPrincipal()));
		TokenResponse response = new TokenResponse(token);
		log.info("END - response: {}", response);
		return ResponseEntity.ok(response);
	}
}
