package repository;

import java.util.List;

import entity.User;

public interface UserRepositoryInterface {

	public boolean verifyLogin(String username, String fjalekalimi);
	
	public User getUserByUsername(String username);
	
	public User getUserById(int idUser);
	
	public boolean addUser(User user);
	
	public boolean verifyUsername(String username);
	
	public boolean verifyUsernameToEdit(String username,int id);

	public boolean verifyEmail(String email);
	
	public List<User> tregoShitesit(int idTregu);
	
	public boolean krijoShites(User shites, int idTregu);
	
	public boolean editUser(User user);
	
	public boolean fshiUser(User user);
	
	public boolean verifikoShitesNgaRezervimet(int idUser);
}
