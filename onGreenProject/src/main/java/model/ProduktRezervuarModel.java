package model;

import entity.ProduktNeTreg;
import entity.Rezervim;

public class ProduktRezervuarModel {

	private int id;
	private int sasia;
	private float cmimi;
	private boolean valid;
	private Rezervim rezervim;
	private ProduktNeTreg produktNeTreg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSasia() {
		return sasia;
	}
	public void setSasia(int sasia) {
		this.sasia = sasia;
	}
	public float getCmimi() {
		return cmimi;
	}
	public void setCmimi(float cmimi) {
		this.cmimi = cmimi;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public Rezervim getRezervim() {
		return rezervim;
	}
	public void setRezervim(Rezervim rezervim) {
		this.rezervim = rezervim;
	}
	public ProduktNeTreg getProduktNeTreg() {
		return produktNeTreg;
	}
	public void setProduktNeTreg(ProduktNeTreg produktNeTreg) {
		this.produktNeTreg = produktNeTreg;
	}
	
}
