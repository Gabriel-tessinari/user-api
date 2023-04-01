package gtcm.users.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gtcm.users.api.model.User;
import gtcm.users.api.repository.UserMongoRepository;

@Service
public class FindUserByIdService {

	private static final Logger log = LoggerFactory.getLogger(FindUserByIdService.class);
	
	@Autowired
	private UserMongoRepository repository;
	
	public User find(String id) {
		log.info("START - id: {}", id);
		User user = new User();
		
		try {
			user = new User(repository.findById(id));
		} catch(Error error) {
			log.error("ERROR: {}", error);
			throw error;
		}
		
		log.info("END - user: {}", user);
		return user;
	}
}
