package model;

import entity.Roli;
import entity.Tregu;

public class UserModel {

	private Integer id;
	private String username;
	private String fjalekalimi;
	private String emri;
	private String mbiemri;
	private String adresa;
	private String email;
	private String celular;
	private Tregu tregu;
	private Roli role;
	private boolean valid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFjalekalimi() {
		return fjalekalimi;
	}
	public void setFjalekalimi(String fjalekalimi) {
		this.fjalekalimi = fjalekalimi;
	}
	public String getEmri() {
		return emri;
	}
	public void setEmri(String emri) {
		this.emri = emri;
	}
	public String getMbiemri() {
		return mbiemri;
	}
	public void setMbiemri(String mbiemri) {
		this.mbiemri = mbiemri;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public Tregu getTregu() {
		return tregu;
	}
	public void setTregu(Tregu tregu) {
		this.tregu = tregu;
	}
	public Roli getRole() {
		return role;
	}
	public void setRole(Roli role) {
		this.role = role;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	

}
