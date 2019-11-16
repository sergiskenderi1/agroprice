package repository;

import entity.User;

public interface UserRepositoryInterface {

	public boolean verifyLogin(String username, String fjalekalimi);
	
	public User getUserByUsername(String username);
	
	public boolean addUser(User user);
	
	public boolean verifyUsername(String username);

	public boolean verifyEmail(String email);
}
