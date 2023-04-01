package gtcm.users.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gtcm.users.api.model.User;
import gtcm.users.api.repository.UserMongoRepository;

@Service
public class FindAllUserService {
	
	private static final Logger log = LoggerFactory.getLogger(FindAllUserService.class);
	
	@Autowired
	private UserMongoRepository repository;
	
	public List<User> findAll() {
		log.info("START");
		List<User> users = new ArrayList<User>();
		
		try {
			users = repository.findAll().stream().map(user -> new User(user)).collect(Collectors.toList());
		} catch(Error error) {
			log.error("ERROR: {}", error);
			throw error;
		}
		
		log.info("END - users: {}", users);
		return users;
	}
}
