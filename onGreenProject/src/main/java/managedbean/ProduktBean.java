package managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;

import model.NjesiMateseModel;
import model.ProduktModel;
import service.ProduktService;
import service.ProduktServiceInterface;
import util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class ProduktBean {
     
	private ProduktServiceInterface productService ;
	private ProduktModel productModel;
	private List<ProduktModel> productModels;
	private List<ProduktModel> filteredProducts;
	private String njesiMatese;
	private List<NjesiMateseModel> njesiMateseList;
	
	@PostConstruct
	public void init() {
		productModel = new ProduktModel();
		productService = new ProduktService();
		productModels = new ArrayList<>();
		productModels = productService.tregoProduktet();
		njesiMateseList = productService.tregoNjesiMatese();
	}
	
	public List<NjesiMateseModel> getNjesiMateseList() {
		return njesiMateseList;
	}

	public void setNjesiMateseList(List<NjesiMateseModel> njesiMateseList) {
		this.njesiMateseList = njesiMateseList;
	}



	public String getNjesiMatese() {
		return njesiMatese;
	}

	public void setNjesiMatese(String njesiMatese) {
		this.njesiMatese = njesiMatese;
	}

	public List<ProduktModel> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(List<ProduktModel> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}


	public void edit(ProduktModel productModel) {
		this.productModel = productModel;
	}

	public ProduktServiceInterface getProductService() {
		return productService;
	}

	public void setProductService(ProduktServiceInterface productService) {
		this.productService = productService;
	}

	public ProduktModel getProductModel() {
		return productModel;
	}

	public void setProductModel(ProduktModel productModel) {
		this.productModel = productModel;
	}

	public List<ProduktModel> getProductModels() {
		return productModels;
	}

	public void setProductModels(List<ProduktModel> productModels) {
		this.productModels = productModels;
	}
	
	public void handleClose(CloseEvent event) {
		this.productModel = new ProduktModel();
	}
	
	public void krijoProdukt() {
		if (productService.krijoProdukt(productModel,njesiMatese)) {
			productModels = productService.tregoProduktet();
			FacesContextUtil.facesContext("Sukses!", "Produkti u krijua me sukses!");
			PrimeFaces.current().ajax().update("productform:productsdatatable");
			productModel = new ProduktModel();
			PrimeFaces.current().executeScript("PF('dlg').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Produkti me kete emer eshte ekzistues ose Cmimi maksimal eshte me i vogel"
					+ " se ai minimal !");
			FacesContext.getCurrentInstance().validationFailed();
		}
	}
	
	public void ndryshoProdukt() {
		if (productService.ndryshoProdukt(productModel,njesiMatese)) {
			productModels = productService.tregoProduktet();
			FacesContextUtil.facesContext("Sukses!", "Produkti u ndryshua me sukses!");
			PrimeFaces.current().ajax().update("productform:productsdatatable");
			productModel = new ProduktModel();
			PrimeFaces.current().executeScript("PF('entityDialog').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Produkti me kete emer eshte ekzistues ose Cmimi maksimal eshte me i vogel"
					+ " se ai minimal!");
			FacesContext.getCurrentInstance().validationFailed();
		}
	}
	
	public void fshiProdukt(ProduktModel produktModel) {
		if (productService.verifikoProduktPerTeFshire(produktModel)) {
			productService.fshiProdukt(produktModel);
			productModels = productService.tregoProduktet();
			FacesContextUtil.facesContext("Sukses!", "Produkti u fshi me sukses!");
			PrimeFaces.current().ajax().update("productform:productsdatatable");
		} else {
			FacesContextUtil.facesContext("Error!", "Produkti nuk mund te fshihet sepse ka gjendje ne tregje.!");
			PrimeFaces.current().ajax().update("productform:productsdatatable");
		}
	}
}
