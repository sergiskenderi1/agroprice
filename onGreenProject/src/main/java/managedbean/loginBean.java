package managedbean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.UserModel;
import service.UserService;
import service.UserServiceInterface;

@ManagedBean
@RequestScoped
public class loginBean {

	private UserModel userModel;
	private UserServiceInterface userService;
	
	@PostConstruct
	public void init() {
		userModel = new UserModel();
		userService = new UserService();
	}

	/**
	 * @return the userModel
	 */
	public UserModel getUserModel() {
		return userModel;
	}

	/**
	 * @param userModel the userModel to set
	 */
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	/**
	 * @return the userService
	 */
	public UserServiceInterface getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserServiceInterface userService) {
		this.userService = userService;
	}
	
	public String verifyLogin() {
		if (userService.verifyLogin(userModel)) {
			this.userModel = userService.getUserByUsername(userModel.getUsername());
			if (userModel.getRole().getIdroli() == 1) {
				return "adminPannel.xhtml?faces-redirect=true";
			} else if (userModel.getRole().getIdroli() == 2) {
				return "shitesPannel.xhtml?faces-redirect=true";
			} else if (userModel.getRole().getIdroli() == 3) {
				return "klientPannel.xhtml?faces-redirect=true";
			} else {
				return null;
			}
		} else {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Username ose Password i gabuar !");
			facesContext.addMessage("loginForm:loginButton", facesMessage);
			return null;
		}
	}
	
}
