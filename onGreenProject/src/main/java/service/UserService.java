package service;

import java.util.ArrayList;
import java.util.List;

import converter.UserConverter;
import entity.ProduktNeTreg;
import entity.User;
import model.UserModel;
import repository.ProduktNeTregRepository;
import repository.ProduktNeTregRepositoryInterface;
import repository.RezervimRepository;
import repository.RezervimRepositoryInterface;
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
		if (!userRepository.verifyUsernameToEdit(userModel.getUsername(),userModel.getId()) &&
				!userRepository.verifyEmailToEdit(userModel.getEmail(),userModel.getId())) {
			return userRepository.editUser(UserConverter.convertToUser(userModel));
		} else
			return false;
	}
	
	@Override
	public boolean fshiUser(UserModel userModel) {
		RezervimRepositoryInterface rezervimRepository = new RezervimRepository();
		if (rezervimRepository.tregoRezervimeShites(userModel.getId()).size() == 0 && 
				rezervimRepository.tregoRezervimeNePritjeShites(userModel.getId()).size() == 0) {
			return userRepository.fshiUser(UserConverter.convertToUser(userModel));
		} else
			return false;
	}
	
	@Override
	public List<UserModel> tregoTeGjitheShitesit() {
		List<UserModel> userModels = new ArrayList<>();
		for (User shites : userRepository.tregoTeGjitheShitesit()) {
			userModels.add(UserConverter.convertToUserModel(shites));
		}
		return userModels;
	}
	
	@Override
	public int gjejSasiPerShites(UserModel userModel) {
		ProduktNeTregRepositoryInterface produktRepository = new ProduktNeTregRepository();
		List<ProduktNeTreg> produkte = new ArrayList<>();
		produkte = produktRepository.tregoProdukteNgaShites(userModel.getId());
		int sasia = 0;
		for(ProduktNeTreg produkteNeTreg : produkte) {
			sasia += produkteNeTreg.getSasiaNeTreg();
		}
		return sasia;
	}
}
