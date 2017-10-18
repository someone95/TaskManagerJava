package task.man.solved.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import task.man.solved.entities.User;

@Repository
public class UsersRepo extends BaseRepo<User> {
	public User getByUsernamePassword(String username, String password)
			throws InstantiationException, IllegalAccessException {
		User user = null;
		List<User> usersList = getAll();
		for (User item : usersList) {
			if (item.getUsername().equals(username) && item.getPassword().equals(password)) {
				user = item;
				return user;
			}
		}
		return null;
	}
}
