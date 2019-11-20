package model;

public class ProduktModel {

	private Integer id;
	private String emri;
	private float cmimiMin;
	private float cmimiMax;
	private boolean valid;

	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    

	/**
	 * @return the emri
	 */
	public String getEmri() {
		return emri;
	}

	/**
	 * @param emri the emri to set
	 */
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
