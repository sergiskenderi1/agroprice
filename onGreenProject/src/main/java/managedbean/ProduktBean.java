package managedbean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private String data;
	private int muaji;
	
	@PostConstruct
	public void init() {
		productModel = new ProduktModel();
		productService = new ProduktService();
		productModels = new ArrayList<>();
		productModels = productService.tregoProduktet();
		njesiMateseList = productService.tregoNjesiMatese();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		data = (String) dateFormat.format(date);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getMuaji() {
		return muaji;
	}

	public void setMuaji(int muaji) {
		this.muaji = muaji;
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
	
	public void currentNjesiMatese() {
		this.njesiMatese = productModel.getNjesiMatese().getEmri();
	}
	
	public void fshiProdukt(ProduktModel produktModel) {
		if (productService.fshiProdukt(produktModel)) {
			productModels = productService.tregoProduktet();
			FacesContextUtil.facesContext("Sukses!", "Produkti u fshi me sukses!");
			PrimeFaces.current().ajax().update("productform:productsdatatable");
		} else {
			FacesContextUtil.facesContext("Error!", "Produkti nuk mund te fshihet sepse ka rezervime aktive per kete "
					+ " produkt !");
			PrimeFaces.current().ajax().update("productform:productsdatatable");
		}
	}
	
	public int sasiaNeTregje(ProduktModel produktModel) {
		return productService.gjejSasiNeTregje(produktModel);
	}
	
	public float cmimiMesatar(ProduktModel produktModel) {
		if(muaji == 0)
		return productService.gjejCmiminMesatar(produktModel);
		else {
			if(muaji < Integer.parseInt(data.substring(0,2)))
			return productService.gjejCmiminMesatarPerMuaj(produktModel,muaji,Integer.parseInt(data.substring(6)));
			else
				return productService.gjejCmiminMesatarPerMuaj(produktModel,muaji,Integer.parseInt(data.substring(6))-1);	
		}
	}

	public float cmimiMeiVogel(ProduktModel produktModel) {
		return productService.gjejCmiminMeTeVogel(produktModel);
	}
	
	public float cmimiMeiLarte(ProduktModel produktModel) {
		return productService.gjejCmiminMeTeLarte(produktModel);
	}
}
