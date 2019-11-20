package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="produkt")
public class Produkt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idprodukt")
	private Integer idprodukt;
	private String emri;
	private float cmimiMin;
	private float cmimiMax;
	private boolean valid;
	
	public Produkt() {
		
	}

	public float getCmimiMin() {
		return cmimiMin;
	}



	public void setCmimiMin(float cmimiMin) {
		this.cmimiMin = cmimiMin;
	}



	public float getCmimiMax() {
		return cmimiMax;
	}



	public void setCmimiMax(float cmimiMax) {
		this.cmimiMax = cmimiMax;
	}



	/**
	 * @return the idprodukt
	 */
	public Integer getIdprodukt() {
		return idprodukt;
	}



	/**
	 * @param idprodukt the idprodukt to set
	 */
	public void setIdprodukt(Integer idprodukt) {
		this.idprodukt = idprodukt;
	}



	public String getEmri() {
		return emri;
	}

	public void setEmri(String emri) {
		this.emri = emri;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
}
