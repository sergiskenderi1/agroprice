package managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class firstPageBean {
 
	@PostConstruct
	public void init() {
		
	}
	
	public String goToRegister() {
		return "regjistrohu.xhtml?faces-redirect=true";
	}
	
	public String goToLogin() {
		return "logohu.xhtml?faces-redirect=true";
	}
	
	public String goToHome() {
		return "faqjaPare.xhtml?faces-redirect=true";
	}
}
