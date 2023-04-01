package gtcm.users.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class CreateUserRequest {
	
	@NotBlank
	String name;
	
	@NotBlank
	@Email
	String email;
	
	@NotBlank
	String password;
	
	Boolean isAdmin;
}
