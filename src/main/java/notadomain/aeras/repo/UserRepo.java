package notadomain.aeras.repo;

import notadomain.aeras.model.User;

public interface UserRepo {
	public void addUserToDatabase(User user);
	public User getUserByUsername(String username);
	public User getUserById(int id);
	public void updateUser(User user);
	public void deleteUser(User user);
}
