package model;

import entity.Status;
import entity.User;

public class RezervimModel {

	private int id;
	private String data;
	private float cmimiTotal;
	private String mesazhi;
	private boolean valid;
	private User user;
	private Status status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public float getCmimiTotal() {
		return cmimiTotal;
	}
	public void setCmimiTotal(float cmimiTotal) {
		this.cmimiTotal = cmimiTotal;
	}
	public String getMesazhi() {
		return mesazhi;
	}
	public void setMesazhi(String mesazhi) {
		this.mesazhi = mesazhi;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
