package gtcm.users.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gtcm.users.api.model.User;
import gtcm.users.api.model.db.UserDB;
import gtcm.users.api.repository.UserMongoRepository;

@Service
public class FindUserByEmailService {

	private static final Logger log = LoggerFactory.getLogger(FindUserByEmailService.class);
	
	@Autowired
	private UserMongoRepository repository;
	
	public User find(String email) {
		log.info("START - email: {}", email);
		User user = new User();
		
		try {
			user = new User((UserDB) repository.findByEmail(email));
		} catch(Error error) {
			log.error("ERROR: {}", error);
			throw error;
		}
		
		log.info("END - user: {}", user);
		return user;
	}
}
