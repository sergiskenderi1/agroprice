package service;

import java.util.List;

import model.UserModel;

public interface UserServiceInterface {

	public boolean verifyLogin(UserModel userModel);
	
	public UserModel getUserByUsername(String username);
	
	public boolean addUser(UserModel userModel);
	
	public boolean verifyUsername(String username);

	public boolean verifyEmail(String email);
	
	public List<UserModel> tregoShitesit(int idTregu);
	
	public List<UserModel> tregoTeGjitheShitesit();

	public boolean krijoShites(UserModel userModel, int idTregu);
	
	public boolean ndryshoUser(UserModel userModel);
	
	public boolean fshiUser(UserModel userModel);
	
	public int gjejSasiPerShites(UserModel userModel);
}
