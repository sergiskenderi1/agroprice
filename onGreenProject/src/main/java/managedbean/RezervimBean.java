package managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

import model.RezervimModel;
import service.RezervimService;
import service.RezervimServiceInterface;
import util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class RezervimBean {
	
	private RezervimServiceInterface rezervimService ;
	private RezervimModel rezervimModel;
	private List<RezervimModel> rezervime;
	private List<RezervimModel> filteredRezervime;
	private int id;

	@PostConstruct
	public void init() {
		rezervimService = new RezervimService();
		rezervimModel = new RezervimModel();
		rezervime = new ArrayList<>();
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public RezervimServiceInterface getRezervimService() {
		return rezervimService;
	}

	public void setRezervimService(RezervimServiceInterface rezervimService) {
		this.rezervimService = rezervimService;
	}

	public RezervimModel getRezervimModel() {
		return rezervimModel;
	}

	public void setRezervimModel(RezervimModel rezervimModel) {
		this.rezervimModel = rezervimModel;
	}

	public List<RezervimModel> getRezervime() {
		return rezervime;
	}

	public void setRezervime(List<RezervimModel> rezervime) {
		this.rezervime = rezervime;
	}

	public List<RezervimModel> getFilteredRezervime() {
		return filteredRezervime;
	}

	public void setFilteredRezervime(List<RezervimModel> filteredRezervime) {
		this.filteredRezervime = filteredRezervime;
	}
	
	public void tregoRezervimePerfunduaraShites(int idShites) {
		this.id = idShites;
		this.rezervime = rezervimService.tregoRezervimePerfunduaraShites(idShites);
	}
	
	public void shiko(RezervimModel rezervimModel) {
		this.rezervimModel = rezervimModel;
	}
	
	public void handleClose() {
		this.rezervimModel = new RezervimModel();
	}
	
	public void tregoRezervimeShites(int idShites) {
		this.id = idShites;
		this.rezervime = rezervimService.tregoRezervimeShites(idShites);
	}
	
	public void tregoRezervimeKlient(int idKlient) {
		this.id = idKlient;
		this.rezervime = rezervimService.tregoRezervimeKlient(idKlient);
	}
	
	public void tregoRezervimeNePritjeShites(int idShites) {
		this.id = idShites;
		this.rezervime = rezervimService.tregoRezervimeNePritjeShites(idShites);
	}
	
	public void tregoRezervimeNePritjeKlient(int idKlient) {
		this.id = idKlient;
		this.rezervime = rezervimService.tregoRezervimeNePritjeKlient(idKlient);
	}
	
	public void konfirmoRezervim(RezervimModel rezervim) {
		if (rezervimService.konfirmoRezervim(rezervim)) {
			rezervime = rezervimService.tregoRezervimeNePritjeShites(id);
			FacesContextUtil.facesContext("Sukses!", "Rezervimi u konfirmua me sukses!");
			PrimeFaces.current().ajax().update("formRezervim:tableRezervim");
			
		} else {
			FacesContextUtil.facesContext("Gabim" , "Rezervimi nuk mund te konfirmohet !");

		}
	}
	
	public void pranoRezervim(RezervimModel rezervim) {
		if (rezervimService.pranoRezervim(rezervim)) {
			rezervime = rezervimService.tregoRezervimeShites(id);
			FacesContextUtil.facesContext("Sukses!", "Rezervimi u pranua me sukses!");
			PrimeFaces.current().ajax().update("formRezervim:tableRezervim");
			
		} else {
			FacesContextUtil.facesContext("Gabim","Rezervimi nuk mund te pranohet pasi eshte fshire ose eshte ne perpunim.");

		}
	}
}
