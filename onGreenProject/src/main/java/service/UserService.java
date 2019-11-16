package service;

import converter.UserConverter;
import model.UserModel;
import repository.UserRepository;
import repository.UserRepositoryInterface;

public class UserService implements UserServiceInterface{

	public UserRepositoryInterface userRepository = new UserRepository();
	
	public boolean verifyLogin(UserModel userModel) {
		return userRepository.verifyLogin(userModel.getUsername(), userModel.getFjalekalimi());
	}
	
	public boolean addUser(UserModel userModel) {
			return userRepository.addUser(UserConverter.convertToUser(userModel));
	}
	
	public UserModel getUserByUsername(String username) {
		UserModel userModel = UserConverter.convertToUserModel(userRepository.getUserByUsername(username));
		return userModel;
	}
	
	public boolean verifyUsername(String username) {
		return userRepository.verifyUsername(username);
	}

	public boolean verifyEmail(String email) {
		return userRepository.verifyEmail(email);
	}
}
