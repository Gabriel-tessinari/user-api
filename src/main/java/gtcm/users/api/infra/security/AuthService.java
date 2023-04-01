package gtcm.users.api.infra.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gtcm.users.api.repository.UserMongoRepository;

@Service
public class AuthService implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	private UserMongoRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("START - email: {}", email);
		UserDetails response = repository.findByEmail(email);
		log.info("END - response: {}", response);
		return response;
	}
}
