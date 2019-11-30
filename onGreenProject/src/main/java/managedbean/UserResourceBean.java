package managedbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;

import model.UserModel;
import service.TreguService;
import service.TreguServiceInterface;
import service.UserService;
import service.UserServiceInterface;
import util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class UserResourceBean {
	private UserModel userModel;
	private UserServiceInterface userService;
	private TreguServiceInterface tregService;
	private List<UserModel> employeeList;
	private List<UserModel> filteredEmployees;

	@PostConstruct
	public void init() {
		this.userModel = new UserModel();
		this.userService = new UserService();
		this.employeeList = new ArrayList<>();
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
	
	public void krijoShites(int idTregu) {
		if (userService.krijoShites(userModel, idTregu)) {
			employeeList = userService.tregoShitesit(idTregu);
			FacesContextUtil.facesContext("Sukses!", "Shitesi u shtua me sukses!");
			PrimeFaces.current().ajax().update("formEmployee:tableEmployee");
			userModel = new UserModel();
			PrimeFaces.current().executeScript("PF('dlgNew').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Shitesi me kete username eshte ekzistues!");

		}
	}

	public List<UserModel> getEmployeeList() {
		return employeeList;
	}


	public void setEmployeeList(List<UserModel> employeeList) {
		this.employeeList = employeeList;
	}


	public List<UserModel> getFilteredEmployees() {
		return filteredEmployees;
	}


	public void setFilteredEmployees(List<UserModel> filteredEmployees) {
		this.filteredEmployees = filteredEmployees;
	}


	public UserServiceInterface getUserService() {
		return userService;
	}
	
	public void redirect() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (!context.isPostback() && context.isValidationFailed()) {
			FacesContextUtil.facesContext("Error!", "URL e gabuar ose nuk eshte zgjedhur asnje treg!");
			FacesContext context1 = FacesContext.getCurrentInstance();
			context1.getExternalContext().getFlash().setKeepMessages(true);
			context.getExternalContext().redirect("zgjidhTreg.xhtml");
		}
	}
	
	public void tregoShitesit(int idTreg) throws IOException {
		tregService = new TreguService();
		if (tregService.gjejTregNgaId(idTreg)){
			this.employeeList = userService.tregoShitesit(idTreg);
		} else {
			FacesContextUtil.facesContext("Error!", "URL e gabuar ose nuk eshte zgjedhur asnje treg!");
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect("zgjidhTreg.xhtml");
		}
	}
	
	public void ndryshoShites() {
		if (userService.ndryshoUser(userModel)) {
			userService.tregoShitesit(userModel.getTregu().getIdtregu());
			FacesContextUtil.facesContext("Sukses!", "Shitesi u ndryshuar me sukses!");
			PrimeFaces.current().ajax().update("formEmployee:tableEmployee");
			userModel = new UserModel();
			PrimeFaces.current().executeScript("PF('entityDialog').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Shitesi me kete username eshte ekzistues!");
		}
	}
	
	public void ndryshoProfil(UserModel user) {
		if (userService.ndryshoUser(user)) {
			FacesContextUtil.facesContext("Sukses!", "Profili u ndryshua me sukses!");
			PrimeFaces.current().ajax().update("formNew");
			userModel = new UserModel();
		} else {
			FacesContextUtil.facesContext("Error!", "Profili me kete username eshte ekzistues!");
		}
	}
	
	public void fshiShites(UserModel shites) {
		if (userService.fshiUser(shites)) {
			employeeList = userService.tregoShitesit(shites.getTregu().getIdtregu());
			FacesContextUtil.facesContext("Sukses!!", "Shitesi u fshi me sukses!");
			PrimeFaces.current().ajax().update("formEmployee:tableEmployee");
		} else {
			FacesContextUtil.facesContext("Smund te fshihet!", "Shitesi ka rezervime ne pritje!");
		}
	}
	
	public void handleClose(CloseEvent event) {
		this.userModel = new UserModel();
	}

	public void tregoTeGjitheShitesit() {
		this.employeeList = userService.tregoTeGjitheShitesit();
	}
	
	public int gjejSasiPerShites(UserModel userModel) {
		return userService.gjejSasiPerShites(userModel);
	}
}