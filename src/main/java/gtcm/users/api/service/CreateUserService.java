package gtcm.users.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gtcm.users.api.model.User;
import gtcm.users.api.model.db.UserDB;
import gtcm.users.api.repository.UserMongoRepository;

@Service
public class CreateUserService {
	
	private static final Logger log = LoggerFactory.getLogger(CreateUserService.class);
	
	@Autowired
	private UserMongoRepository repository;
	
	@Transactional
	public User create(User request) {
		log.info("START - request: {}", request);
		User user = new User();
		
		try {
			user = new User(repository.save(new UserDB(request)));
		} catch(Error error) {
			log.error("ERROR: {}", error);
			throw error;
		}
		
		log.info("END - user: {}", user);
		return user; 
	}
}
