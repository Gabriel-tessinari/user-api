package gtcm.users.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import gtcm.users.api.model.CreateUserRequest;
import gtcm.users.api.model.User;
import gtcm.users.api.service.CreateUserService;
import gtcm.users.api.service.DeleteUserService;
import gtcm.users.api.service.FindAllUserService;
import gtcm.users.api.service.FindUserByIdService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
@SuppressWarnings("rawtypes")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private FindAllUserService findAllUserService;
	
	@Autowired
	private CreateUserService createUserService;
	
	@Autowired
	private DeleteUserService deleteUserService;
	
	@Autowired
	private FindUserByIdService findUserByIdService;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		log.info("START");
		List<User> users = findAllUserService.findAll();
		log.info("END - users: {}", users);
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> findById(@PathVariable String id) {
		log.info("START - id: {}", id);
		User user = findUserByIdService.find(id);
		log.info("END - user: {}", user);
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserRequest request,
			UriComponentsBuilder uriBuilder) {
		log.info("START - request: {}", request);
		User userRequest = new User(request);
		User user = createUserService.create(userRequest);
		log.info("END - user: {}", user);
		var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deleteUser(@PathVariable String id) {
		log.info("START - id: {}", id);
		deleteUserService.delete(id);
		log.info("END");
		return ResponseEntity.noContent().build();
	}
}
