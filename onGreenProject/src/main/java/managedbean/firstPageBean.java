package managedbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class firstPageBean {

	private List<String> tregjet;
	private String image;
	
	@PostConstruct
	public void init() {
		tregjet = new ArrayList<>();
		shtoTregjet();
		tregoKuriozitet();
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<String> getTregjet() {
		return tregjet;
	}

	public void setTregjet(List<String> tregjet) {
		this.tregjet = tregjet;
	}

	public void shtoTregjet() {
		tregjet.add("Autostrada Tirane-Durres.jpg");
		tregjet.add("Njesia 2 Tirane.jpg");
		tregjet.add("Pazari i Ri.jpg");
		tregjet.add("Tregu ne Astir.jpg");
		tregjet.add("Uzina Dinamo.jpg");
		tregjet.add("xhamlliku.jpg");
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
	
	public void tregoKuriozitet() {
		image = "kuriozitet";
		Random rand = new Random();
		int nrImage = rand.nextInt(8);
		image = image + nrImage + ".jpg";
	}
}
