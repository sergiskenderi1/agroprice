package converter;

import entity.User;
import model.UserModel;

public class UserConverter {
   
	public static User convertToUser(UserModel userModel) {
		User user = new User();
		user.setId(userModel.getId());
		user.setCelular(userModel.getCelular());
		user.setAdresa(userModel.getAdresa());
		user.setEmail(userModel.getEmail());
		user.setEmri(userModel.getEmri());
		user.setFjalekalimi(userModel.getFjalekalimi());
		user.setMbiemri(userModel.getMbiemri());
		user.setRole(userModel.getRole());
		user.setTregu(userModel.getTregu());
		user.setUsername(userModel.getUsername());
		user.setValid(userModel.isValid());
		return user;
	}
	
	public static UserModel convertToUserModel(User user) {
		UserModel userModel = new UserModel();
		userModel.setId(user.getId());
		userModel.setCelular(user.getCelular());
		userModel.setAdresa(user.getAdresa());
		userModel.setEmail(user.getEmail());
		userModel.setEmri(user.getEmri());
		userModel.setFjalekalimi(user.getFjalekalimi());
		userModel.setMbiemri(user.getMbiemri());
		userModel.setRole(user.getRole());
		userModel.setTregu(user.getTregu());
		userModel.setUsername(user.getUsername());
		userModel.setValid(user.isValid());
		return userModel;
	}
}
