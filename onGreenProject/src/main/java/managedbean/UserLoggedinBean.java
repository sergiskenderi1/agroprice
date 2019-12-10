package managedbean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import model.UserModel;
import service.UserService;
import service.UserServiceInterface;
import util.FacesContextUtil;

@ManagedBean
@SessionScoped
public class UserLoggedinBean {

	private UserModel userModel;
	private UserServiceInterface userService;
	private boolean loggedIn;

	@PostConstruct
	public void init() {
		setUserService(new UserService());
		userModel = new UserModel();
	}

	
	
	public boolean isLoggedIn() {
		return loggedIn;
	}



	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
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
			loggedIn = true;
		} catch (Exception ex) {
			userModel = new UserModel();
		}
	}

	public String dilni() {
		this.userModel = new UserModel();
		loggedIn = false;
		return "/logohu.xhtml?faces-redirect=true";
	}
	
	public void control() throws IOException{
		if(userModel.getId() != null) {
			this.userModel = new UserModel();
			loggedIn = false;
		}
	}
	
}
