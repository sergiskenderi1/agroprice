package model;

import entity.NjesiMatese;
import entity.Produkt;
import entity.Tregu;
import entity.User;

public class ProduktNeTregModel {

	private int id;
	private int sasiaNeTreg;
	private boolean valid;
	private User user;
	private Produkt produkt;
	private NjesiMatese njesiMatese;
	private Tregu tregu;
	
	public int getId() {
		return id;
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
	public NjesiMatese getNjesiMatese() {
		return njesiMatese;
	}
	public void setNjesiMatese(NjesiMatese njesiMatese) {
		this.njesiMatese = njesiMatese;
	}
	public Tregu getTregu() {
		return tregu;
	}
	public void setTregu(Tregu tregu) {
		this.tregu = tregu;
	}
	
	
}
