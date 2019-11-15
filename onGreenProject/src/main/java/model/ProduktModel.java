package model;

public class ProduktModel {

	private Integer id;
	private String emri;
	private float cmimi;
	private boolean valid;

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

	/**
	 * @return the cmimi
	 */
	public float getCmimi() {
		return cmimi;
	}

	/**
	 * @param cmimi the cmimi to set
	 */
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
