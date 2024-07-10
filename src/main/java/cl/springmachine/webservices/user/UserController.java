package cl.springmachine.webservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cl.springmachine.webservices.post.PostEntity;
import jakarta.validation.Valid;

@RestController
public class UserController {
	
	private UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/users")
	public List<UserEntity> getUsers() {
		return service.findAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public UserEntity getUserById(@PathVariable Integer id) {
		return service.findUserById(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) {
		UserEntity savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteByUserId(@PathVariable Integer id) {
		service.deleteById(id);	
	}
	
	@GetMapping("/users/{id}/posts")
	public List<PostEntity> getAllPostsByUserId(@PathVariable Integer id) {
		return service.getAllPostsByUserId(id);	
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<List<PostEntity>> createPostForUser(@Valid @RequestBody PostEntity post, @PathVariable Integer id) {
		PostEntity savedPost = service.createPostForUser(post, id);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
