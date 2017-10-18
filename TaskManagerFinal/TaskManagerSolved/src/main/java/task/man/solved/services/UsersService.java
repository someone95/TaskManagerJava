package task.man.solved.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task.man.solved.entities.User;
import task.man.solved.repositories.UsersRepo;

@Service
@Transactional
public class UsersService extends BaseService<User> {
	@Autowired
	private UsersRepo usersRepo;

	public User getByUsernamePassword(String username, String password)
			throws InstantiationException, IllegalAccessException {
		return usersRepo.getByUsernamePassword(username, password);
	}
}
