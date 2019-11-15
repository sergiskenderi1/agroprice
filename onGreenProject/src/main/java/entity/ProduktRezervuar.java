package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produktrezervuar")
public class ProduktRezervuar {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private int id;
	private int sasia;
	private float cmimi;
	private boolean valid;
	@ManyToOne
	@JoinColumn(name = "id_rezervim")
	private Rezervim rezervim;
	@ManyToOne
	@JoinColumn(name = "id_produktnetreg")
	private ProduktNeTreg produktNeTreg;
	
	public ProduktRezervuar() {
		
	}

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
