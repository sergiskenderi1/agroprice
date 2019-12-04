package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rezervim")
public class Rezervim {

	@Id
	@Column(name = "idrezervim")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private int idrezervim;
	private String data;
	private float cmimiTotal;
	private String mesazhi;
	private boolean valid;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idclient")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idShites")
	private User shites;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idstatus")
	private Status status;
	@OneToMany(mappedBy = "rezervim", fetch = FetchType.LAZY)
	private List<ProduktRezervuar> produkteRezervuar = new ArrayList<>();
	
	public Rezervim() {
		
	}

	public User getShites() {
		return shites;
	}


	public void setShites(User shites) {
		this.shites = shites;
	}




	/**
	 * @return the idrezervim
	 */
	public int getIdrezervim() {
		return idrezervim;
	}


	/**
	 * @param idrezervim the idrezervim to set
	 */
	public void setIdrezervim(int idrezervim) {
		this.idrezervim = idrezervim;
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

	/**
	 * @return the produkteRezervuar
	 */
	public List<ProduktRezervuar> getProdukteRezervuar() {
		return produkteRezervuar;
	}

	/**
	 * @param produkteRezervuar the produkteRezervuar to set
	 */
	public void setProdukteRezervuar(List<ProduktRezervuar> produkteRezervuar) {
		this.produkteRezervuar = produkteRezervuar;
	}
	
	
}
