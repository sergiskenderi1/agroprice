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
	private float cmimi;
	private boolean valid;
	
	public Produkt() {
		
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
	
	
}
