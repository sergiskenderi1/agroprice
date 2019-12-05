package model;

import entity.Produkt;
import entity.Tregu;
import entity.User;

public class ProduktNeTregModel {

	private int id;
	private int sasiaNeTreg;
	private boolean valid;
	private String pershkrimi;
	private User user;
	private Produkt produkt;
	private Tregu tregu;
	private float cmimiShites;
	
	public int getId() {
		return id;
	}
	
	public float getCmimiShites() {
		return cmimiShites;
	}


	public void setCmimiShites(float cmimiShites) {
		this.cmimiShites = cmimiShites;
	}



	public void setId(int id) {
		this.id = id;
	}
	public int getSasiaNeTreg() {
		return sasiaNeTreg;
	}
	public void setSasiaNeTreg(int sasiaNeTreg) {
		this.sasiaNeTreg = sasiaNeTreg;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Produkt getProdukt() {
		return produkt;
	}
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	public Tregu getTregu() {
		return tregu;
	}
	public void setTregu(Tregu tregu) {
		this.tregu = tregu;
	}

	public String getPershkrimi() {
		return pershkrimi;
	}

	public void setPershkrimi(String pershkrimi) {
		this.pershkrimi = pershkrimi;
	}
	
}
