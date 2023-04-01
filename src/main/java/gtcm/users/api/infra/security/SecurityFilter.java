package gtcm.users.api.infra.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import gtcm.users.api.repository.UserMongoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserMongoRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("START");
		String token = getToken(request);
		
		if(token != null) {
			String email = tokenService.getSubject(token);
			UserDetails user = repository.findByEmail(email);
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		log.info("END");
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(token != null) {
			return token.replace("Bearer ", "");
		}
		
		return null;
	}
}
