package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="produktnetreg")
public class ProduktNeTreg {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int sasiaNeTreg;
    private float cmimiShites;
    private String pershkrimi;
	private boolean valid;
	@ManyToOne
	@JoinColumn(name = "idshites")
	private User user;
	@ManyToOne
	@JoinColumn(name = "idProdukt")
	private Produkt produkt;
	@ManyToOne
	@JoinColumn(name = "treguId")
	private Tregu tregu;
	
	public ProduktNeTreg() {
		
	}

	public float getCmimiShites() {
		return cmimiShites;
	}



	public void setCmimiShites(float cmimiShites) {
		this.cmimiShites = cmimiShites;
	}



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
