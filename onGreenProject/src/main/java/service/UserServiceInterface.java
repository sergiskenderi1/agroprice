package service;

import model.UserModel;

public interface UserServiceInterface {

	public boolean verifyLogin(UserModel userModel);
	
	public UserModel getUserByUsername(String username);
	
	public boolean addUser(UserModel userModel);
	
	public boolean verifyUsername(String username);

	public boolean verifyEmail(String email);

}
