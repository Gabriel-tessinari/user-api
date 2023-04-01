package gtcm.users.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gtcm.users.api.repository.UserMongoRepository;

@Service
public class DeleteUserService {

	private static final Logger log = LoggerFactory.getLogger(DeleteUserService.class);
	
	@Autowired
	private UserMongoRepository repository;
	
	public void delete(String id) {
		log.info("START - id: {}", id);
		
		try {
			repository.deleteById(id);
		} catch(Error error) {
			log.error("ERROR: {}", error);
			throw error;
		}
		
		log.info("END");
	}
}
