package entity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import converter.RezervimConverter;
import model.ProduktRezervuarModel;
import model.RezervimModel;
import service.ProduktRezervuarService;
import service.ProduktRezervuarServiceInterface;

@ManagedBean
@ViewScoped
public class ProduktRezervuarBean {

	private ProduktRezervuarServiceInterface produktRezervuarService;
	private ProduktRezervuarModel produktRezervuarModel ;
	private List<ProduktRezervuarModel> produkteRezervuar ;
	
	@PostConstruct
	public void init() {
		produktRezervuarService = new ProduktRezervuarService();
		produktRezervuarModel = new ProduktRezervuarModel();
		produkteRezervuar = new ArrayList<>();
	}

	public ProduktRezervuarServiceInterface getProduktRezervuarService() {
		return produktRezervuarService;
	}

	public void setProduktRezervuarService(ProduktRezervuarServiceInterface produktRezervuarService) {
		this.produktRezervuarService = produktRezervuarService;
	}

	public ProduktRezervuarModel getProduktRezervuarModel() {
		return produktRezervuarModel;
	}

	public void setProduktRezervuarModel(ProduktRezervuarModel produktRezervuarModel) {
		this.produktRezervuarModel = produktRezervuarModel;
	}

	public List<ProduktRezervuarModel> getProdukteRezervuar() {
		return produkteRezervuar;
	}

	public void setProdukteRezervuar(List<ProduktRezervuarModel> produkteRezervuar) {
		this.produkteRezervuar = produkteRezervuar;
	}
	
	public void shiko(RezervimModel rezervim) {
		this.produktRezervuarModel.setRezervim(RezervimConverter.convertToEntity(rezervim));
		this.produkteRezervuar = produktRezervuarService.tregoProdukteNeRezervim(rezervim.getId());
	}
}
