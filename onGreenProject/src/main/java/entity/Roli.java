package entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roli")
public class Roli {

	@Id
	@Column(name = "idroli")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String emri;
	private String pershkrim;
	
	public Roli() {
		
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


}
