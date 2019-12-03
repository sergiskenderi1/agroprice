package managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import model.UserModel;
import service.UserService;
import service.UserServiceInterface;

@ManagedBean
@SessionScoped
public class UserLoggedinBean {

	private UserModel userModel;
	private UserServiceInterface userService;

	@PostConstruct
	public void init() {
		setUserService(new UserService());
		userModel = new UserModel();
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public UserServiceInterface getUserService() {
		return userService;
	}

	public void setUserService(UserServiceInterface userService) {
		this.userService = userService;
	}

	public void UserLoggedIn(ActionEvent event) {
		try {
			String username = (String) event.getComponent().getAttributes().get("username");
			userModel = userService.getUserByUsername(username);
		} catch (Exception ex) {
			userModel = new UserModel();
		}
	}

	public String dilni() {
		this.userModel = new UserModel();
		return "/logohu.xhtml?faces-redirect=true";
	}
}
