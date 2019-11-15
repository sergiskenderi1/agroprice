package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tregu")
public class Tregu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtregu")
	private int id;
	private String emri;
	private String adresa;
	private String celular;
	private boolean valid;
	@OneToMany(mappedBy = "tregu", fetch = FetchType.LAZY)
	private List<User> shites = new ArrayList<User>();
	@OneToMany(mappedBy = "tregu", fetch = FetchType.LAZY)
	private List<ProduktNeTreg> produktNeTreg = new ArrayList<ProduktNeTreg>();

	public Tregu() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmri() {
		return emri;
	}
	public void setEmri(String emri) {
		this.emri = emri;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public List<User> getShites() {
		return shites;
	}
	public void setShites(List<User> shites) {
		this.shites = shites;
	}
	public List<ProduktNeTreg> getProduktNeTreg() {
		return produktNeTreg;
	}
	public void setProduktNeTreg(List<ProduktNeTreg> produktNeTreg) {
		this.produktNeTreg = produktNeTreg;
	}
	
}
