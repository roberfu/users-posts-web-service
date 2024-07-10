package cl.springmachine.webservices.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import cl.springmachine.webservices.post.PostEntity;
import cl.springmachine.webservices.post.PostRepository;
import jakarta.validation.Valid;

@Component
public class UserService {
	
	private final UserRepository userRepository;
	
	private final PostRepository postRepository;
	
	private static final String ERROR_MESSAGE = "User id: ";
	
	public UserService(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	public List<UserEntity> findAllUsers() {
		return userRepository.findAll();
	}

	public UserEntity findUserById(Integer id) {
		Optional<UserEntity> optional = userRepository.findById(id);
		if (optional.isEmpty()) throw new UserNotFoundException(ERROR_MESSAGE + id);
		return optional.get();
	}

	public UserEntity save(UserEntity userEntity) {
		return userRepository.save(new UserEntity(null, userEntity.getName(), userEntity.getBirthDate(), new ArrayList<>()));
	}

	public void deleteById(Integer id) {
		Optional<UserEntity> optional = userRepository.findById(id);
		if (optional.isEmpty()) throw new UserNotFoundException(ERROR_MESSAGE + id);
		userRepository.deleteById(id);
		
	}

	public List<PostEntity> getAllPostsByUserId(Integer id) {
		Optional<UserEntity> optional = userRepository.findById(id);
		if (optional.isEmpty()) throw new UserNotFoundException(ERROR_MESSAGE + id);
		return optional.get().getPosts();
	}

	public PostEntity createPostForUser(@Valid PostEntity post, Integer id) {
		Optional<UserEntity> optional = userRepository.findById(id);
		if (optional.isEmpty()) throw new UserNotFoundException(ERROR_MESSAGE + id);
		return postRepository.save(new PostEntity(null, post.getDescription(), optional.get()));
	}

}
