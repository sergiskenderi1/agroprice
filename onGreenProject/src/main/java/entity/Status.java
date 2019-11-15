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
@Table(name = "status")
public class Status {

	@Id
	@Column(name = "idstatus")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String emri;
	private String pershkrim;
	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<Rezervim> rezervim = new ArrayList<Rezervim>();
	
	public Status() {
		
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

	public String getPershkrim() {
		return pershkrim;
	}

	public void setPershkrim(String pershkrim) {
		this.pershkrim = pershkrim;
	}

	public List<Rezervim> getRezervim() {
		return rezervim;
	}

	public void setRezervim(List<Rezervim> rezervim) {
		this.rezervim = rezervim;
	}
	
	
}
