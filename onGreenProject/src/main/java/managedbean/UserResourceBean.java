package managedbean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.UserModel;
import service.UserService;
import service.UserServiceInterface;

@ManagedBean
@ViewScoped
public class UserResourceBean {
	private UserModel userModel;
	private UserServiceInterface userService;

	@PostConstruct
	public void init() {
		this.userModel = new UserModel();
		this.userService = new UserService();
	}


	public void edit(UserModel userModel) {
		this.userModel = userModel;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public UserServiceInterface getEmployeeService() {
		return userService;
	}

	public void setUserService(UserServiceInterface userService) {
		this.userService = userService;
	}

	public String moveToEmployeePannel() {
		return "indexEmployee.xhtml";
	}

	public String addUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		// verifikon nese username eshte ekzistues
		if (!(userService.verifyUsername(userModel.getUsername()))) {
			// verifikon nese emaili q ka dhene eshte ekzistues
			if (!(userService.verifyEmail(userModel.getEmail()))) {
				if (userService.addUser(userModel)) {
					FacesMessage facesMessage = new FacesMessage("Regjistrimi u krye me sukses.");
					facesContext.addMessage("signUpForm:register", facesMessage);
					facesContext.getExternalContext().getFlash().setKeepMessages(true);
					return "klientPannel.xhtml?faces-redirect=true";
				} else {
					return null;
				}
			} else {
				FacesMessage facesMessage = new FacesMessage("Ky email nuk eshte i disponueshem.");
				facesContext.addMessage("registerForm:email", facesMessage);
				return null;
			}
		} else {
			FacesMessage facesMessage = new FacesMessage("Ky username nuk eshte i disponueshem.");
			facesContext.addMessage("registerForm:username", facesMessage);
			return null;
		}
	}

	public UserServiceInterface getUserService() {
		return userService;
	}

}