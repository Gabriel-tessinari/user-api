package gtcm.users.api.model;

import java.util.Optional;

import gtcm.users.api.model.db.UserDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	String id;
	String name;
	String email;
	String password;
	Boolean isAdmin;
	
	public User(CreateUserRequest request) {
		this.name = request.getName();
		this.email = request.getEmail();
		this.password = request.getPassword();
		this.isAdmin = request.getIsAdmin();
	}
	
	public User(UserDB request) {
		this.id = request.getId();
		this.name = request.getName();
		this.email = request.getEmail();
		this.password = request.getPassword();
		this.isAdmin = request.getIsAdmin();
	}

	public User(Optional<UserDB> request) {
		this.id = request.get().getId();
		this.name = request.get().getName();
		this.email = request.get().getEmail();
		this.password = request.get().getPassword();
		this.isAdmin = request.get().getIsAdmin();
	}
}
