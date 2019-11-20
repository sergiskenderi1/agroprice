package service;

import java.util.ArrayList;
import java.util.List;

import converter.UserConverter;
import entity.User;
import model.UserModel;
import repository.UserRepository;
import repository.UserRepositoryInterface;

public class UserService implements UserServiceInterface {

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

	@Override
	public List<UserModel> tregoShitesit(int idTregu) {
		List<UserModel> userModels = new ArrayList<>();
		for (User shites : userRepository.tregoShitesit(idTregu)) {
			userModels.add(UserConverter.convertToUserModel(shites));
		}
		return userModels;
	}
	
	@Override
	public boolean krijoShites(UserModel userModel, int idTregu) {
		if (!userRepository.verifyUsername(userModel.getUsername())) {
			return userRepository.krijoShites(UserConverter.convertToUser(userModel), idTregu);
		} else
			return false;
	}
	
	@Override
	public boolean ndryshoUser(UserModel userModel) {
		if (!userRepository.verifyUsernameToEdit(userModel.getUsername(),userModel.getId())) {
			return userRepository.editUser(UserConverter.convertToUser(userModel));
		} else
			return false;
	}
	
	@Override
	public boolean fshiUser(UserModel userModel) {
		if (!userRepository.verifikoShitesNgaRezervimet(userModel.getId())) {
			return userRepository.fshiUser(UserConverter.convertToUser(userModel));
		} else
			return false;
	}
}
