package gtcm.users.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import gtcm.users.api.model.db.UserDB;

public interface UserMongoRepository extends MongoRepository<UserDB, String> {
	UserDetails findByEmail(String email);
}
