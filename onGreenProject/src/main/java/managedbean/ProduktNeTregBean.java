package managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

import converter.ProduktConverter;
import model.ProduktModel;
import model.ProduktNeTregModel;
import model.UserModel;
import service.ProduktNeTregService;
import service.ProduktNeTregServiceInterface;
import util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class ProduktNeTregBean {
	
	private ProduktNeTregModel produktNeTregModel ;
	private List<ProduktNeTregModel> produkteNeTreg;
	private List<ProduktNeTregModel> filteredProducts;
	private ProduktNeTregServiceInterface produktNeTregService ;
	private int idShites;

	@PostConstruct
	public void init(){
		produktNeTregModel = new ProduktNeTregModel();
		produkteNeTreg = new ArrayList<>();
		produktNeTregService = new ProduktNeTregService();
	}
	
	public void produktiZgjedhur(ProduktModel produkt) {
		this.produktNeTregModel.setProdukt(ProduktConverter.convertToProduktEntity(produkt));
	}
	
	public void setIdShites(int idShites) {
		this.idShites = idShites;
	}

	public int getIdShites() {
		return idShites;
	}

	public void edit(ProduktNeTregModel produktModel) {
		this.produktNeTregModel = produktModel;
	}
	
	public List<ProduktNeTregModel> getFilteredProducts() {
		return filteredProducts;
	}

    public void tregoProdukteNeTregje() {
    	produkteNeTreg = produktNeTregService.tregoProdukteNeTregje();
    }

	public void setFilteredProducts(List<ProduktNeTregModel> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}



	public ProduktNeTregModel getProduktNeTregModel() {
		return produktNeTregModel;
	}

	public void setProduktNeTregModel(ProduktNeTregModel produktNeTregModel) {
		this.produktNeTregModel = produktNeTregModel;
	}

	public List<ProduktNeTregModel> getProdukteNeTreg() {
		return produkteNeTreg;
	}

	public void setProdukteNeTreg(List<ProduktNeTregModel> produkteNeTreg) {
		this.produkteNeTreg = produkteNeTreg;
	}

	public ProduktNeTregServiceInterface getProduktNeTregService() {
		return produktNeTregService;
	}

	public void setProduktNeTregService(ProduktNeTregServiceInterface produktNeTregService) {
		this.produktNeTregService = produktNeTregService;
	}
	
	public void tregoProdukteNgaShites(int idShites){
		produkteNeTreg = produktNeTregService.tregoProdukteNgaShites(idShites);
	}
	
	public void handleClose() {
		this.produktNeTregModel = new ProduktNeTregModel();
	}
	
	public void ndryshoProduktNeTregShites(int id) {
		if (produktNeTregService.ndryshoProduktNeTreg(produktNeTregModel)) {
			tregoProdukteNgaShites(id);
			FacesContextUtil.facesContext("Sukses!", "Produkti u ndryshua me sukses!");
			PrimeFaces.current().ajax().update("formProdukt:tableProduktNeTregShites");
			produktNeTregModel = new ProduktNeTregModel();
			PrimeFaces.current().executeScript("PF('entityDialog').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Cmimi i vendosur nga ju nuk eshte me i madh se Cmimi Minimal dhe me i "
					+ " vogel se Cmimi Maksimal!");
		}
	}
	
	public void shtoProduktNeTregShites(int id) {
		if(produktNeTregService.shtoProduktNeTreg(produktNeTregModel,id)) {
			tregoProdukteNgaShites(id);
			FacesContextUtil.facesContext("Sukses!", "Produkti u ndryshua me sukses!");
			PrimeFaces.current().ajax().update("productform:productsdatatable");
			produktNeTregModel = new ProduktNeTregModel();
			PrimeFaces.current().executeScript("PF('entityDialog').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Cmimi i vendosur nga ju nuk eshte me i madh se Cmimi Minimal dhe me i "
					+ " vogel se Cmimi Maksimal!");
		}
	}
	
	public void fshiProduktShites(ProduktNeTregModel produktNeTreg,int id) {
		if (produktNeTregService.fshiProduktNeTreg(produktNeTreg.getId())) {
			tregoProdukteNgaShites(id);
			FacesContextUtil.facesContext("Sukses!", "Produkti u fshi me sukses!");
			PrimeFaces.current().ajax().update("formProdukt:tableProduktNeTregShites");
			produktNeTregModel = new ProduktNeTregModel();
			PrimeFaces.current().executeScript("PF('entityDialog').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Nuk mund te fshihet sepse ka rezervime per kete produkt");
		}
	}
	
	public void fshiProduktNeTreg(ProduktNeTregModel produktNeTreg) {
		if(produktNeTregService.fshiProduktNeTreg(produktNeTreg)) {
			tregoProdukteNeTregje();
			FacesContextUtil.facesContext("Sukses!", "Produkti u fshi me sukses!");
			PrimeFaces.current().ajax().update("formProdukt:tableProduktNeTregShites");
			produktNeTregModel = new ProduktNeTregModel();
			PrimeFaces.current().executeScript("PF('entityDialog').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Nuk mund te fshihet sepse ka rezervime per kete produkt");
		}
	}
	
}
