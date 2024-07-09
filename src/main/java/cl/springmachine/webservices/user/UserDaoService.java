package cl.springmachine.webservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static Integer usersCount = 0;
	
	static {
		users.add(new User(++usersCount, "Michael", LocalDate.now().minusYears(50)));
		users.add(new User(++usersCount, "Dwight", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(30)));
	}

	public List<User> findAllUsers() {
		return users;
	}

	public User findUserById(Integer id) {
		User user = users.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
		if (user == null) throw new UserNotFoundException("Users id: " + id);
		return user;
	}

	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

	public void deleteById(Integer id) {
		User user = users.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
		if (user == null) throw new UserNotFoundException("User id: " + id);
		users.remove(user);
		
	}

}
