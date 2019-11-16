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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int sasiaNeTreg;
	private boolean valid;
	@ManyToOne
	@JoinColumn(name = "idshites")
	private User user;
	@ManyToOne
	@JoinColumn(name = "idProdukt")
	private Produkt produkt;
	@ManyToOne
	@JoinColumn(name = "idNjesiMatese")
	private NjesiMatese njesiMatese;
	@ManyToOne
	@JoinColumn(name = "treguId")
	private Tregu tregu;
	
	public ProduktNeTreg() {
		
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
