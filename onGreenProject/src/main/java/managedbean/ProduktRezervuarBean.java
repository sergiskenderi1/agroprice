package managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

import converter.ProduktNeTregConverter;
import converter.RezervimConverter;
import model.ProduktNeTregModel;
import model.ProduktRezervuarModel;
import model.RezervimModel;
import model.TreguModel;
import service.ProduktRezervuarService;
import service.ProduktRezervuarServiceInterface;
import util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class ProduktRezervuarBean {

	private ProduktRezervuarModel produktRezervuarModel ;
	private ProduktRezervuarServiceInterface produktRezervuarService;
	private List<ProduktRezervuarModel> produkteRezervuar;
	private List<ProduktRezervuarModel> filteredProducts;
	
	@PostConstruct
	public void init() {
       produktRezervuarModel = new ProduktRezervuarModel();
       produktRezervuarService = new ProduktRezervuarService();
       produkteRezervuar = new ArrayList<>();
	}

	public ProduktRezervuarModel getProduktRezervuarModel() {
		return produktRezervuarModel;
	}

	public void setProduktRezervuarModel(ProduktRezervuarModel produktRezervuarModel) {
		this.produktRezervuarModel = produktRezervuarModel;
	}

	public List<ProduktRezervuarModel> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(List<ProduktRezervuarModel> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

	public List<ProduktRezervuarModel> getProdukteRezervuar() {
		return produkteRezervuar;
	}

	public void setProdukteRezervuar(List<ProduktRezervuarModel> produkteRezervuar) {
		this.produkteRezervuar = produkteRezervuar;
	}

	public ProduktRezervuarServiceInterface getProduktRezervuarService() {
		return produktRezervuarService;
	}

	public void setProduktRezervuarService(ProduktRezervuarServiceInterface produktRezervuarService) {
		this.produktRezervuarService = produktRezervuarService;
	}
	
	public void produktiZgjedhur(ProduktNeTregModel produktNeTreg) {
		this.produktRezervuarModel.setProduktNeTreg(ProduktNeTregConverter.convertToEntity(produktNeTreg));
	}
	
	public void handleClose() {
		this.produktRezervuarModel = new ProduktRezervuarModel();
	}
	
	public void rezervoProdukt(ProduktRezervuarModel produktRezervuar,int idKlient) {
		if (produktRezervuarService.rezervoProdukt(produktRezervuar,idKlient)) {
			FacesContextUtil.facesContext("Sukses!", "Produkti u rezervua me sukses!");
			PrimeFaces.current().executeScript("PF('entityDialog').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Ju keni rezervuar per kete produkt ose sasia qe kerkoni "
					+ "eshte me e madhe sesa gjendja!");
		}
	}
	
	public void ndryshoProduktNeRezervim(ProduktRezervuarModel produktRezervuar) {
		if (produktRezervuarService.ndryshoProduktNeRezervim(produktRezervuar)) {
			FacesContextUtil.facesContext("Sukses!", "Produkti u ndryshua me sukses!");
			PrimeFaces.current().executeScript("PF('entityDialog').hide();");
			PrimeFaces.current().ajax().update("formProdukt:tableProduktRezervim");
		} else {
			FacesContextUtil.facesContext("Error!", "Sasia e vendosur eshte me e madhe se sasia ne treg!");
		}
	}
	
	public void fshiProdukt(ProduktRezervuarModel produktRezervuar) {
		if (produktRezervuarService.fshiProdukt(produktRezervuar)) {
			produkteRezervuar = produktRezervuarService.tregoProdukteNeRezervim(produktRezervuar.getRezervim().getIdrezervim());
			FacesContextUtil.facesContext("Sukses!", "Produkti u fshi me sukses!");
			PrimeFaces.current().executeScript("PF('entityDialog').hide();");
			PrimeFaces.current().ajax().update("formProdukt:tableProduktRezervim");
		} else {
			FacesContextUtil.facesContext("Error!", "Produkti nuk mund te fshihet!");
		}
	}
	
	public void shiko(RezervimModel rezervim) {
		this.produktRezervuarModel.setRezervim(RezervimConverter.convertToEntity(rezervim));
		produkteRezervuar = produktRezervuarService.tregoProdukteNeRezervim(rezervim.getId());
	}
	
	public void shikoProdukt(ProduktRezervuarModel produkt) {
		this.produktRezervuarModel = produkt;
	}
	
    public String ndryshoRezervim(RezervimModel rezervim) {
    	this.produktRezervuarModel.setRezervim(RezervimConverter.convertToEntity(rezervim));
    	return "/Klient/ndryshoRezervim.xhtml?id=" + rezervim.getId() + "faces-redirect=true";
    }
    
    public void tregoProdukteNeRezervim(int idRezervim) {
       produkteRezervuar = produktRezervuarService.tregoProdukteNeRezervim(idRezervim);
    }
}
