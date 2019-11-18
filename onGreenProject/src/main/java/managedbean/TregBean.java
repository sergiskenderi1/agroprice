package managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;

import model.TreguModel;
import service.TreguService;
import service.TreguServiceInterface;
import util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class TregBean {

	private TreguModel tregModel;
	private TreguServiceInterface tregService;
	private List<TreguModel> storeList;
	private List<TreguModel> filteredStores;

	@PostConstruct
	public void init() {
		this.tregModel = new TreguModel();
		this.tregService = new TreguService();
		storeList = new ArrayList<>();
		this.storeList = tregService.tregoTregjet();
	}

	/**
	 * @return the tregModel
	 */
	public TreguModel getTregModel() {
		return tregModel;
	}

	/**
	 * @param tregModel the tregModel to set
	 */
	public void setTregModel(TreguModel tregModel) {
		this.tregModel = tregModel;
	}

	/**
	 * @return the tregService
	 */
	public TreguServiceInterface getTregService() {
		return tregService;
	}

	/**
	 * @param tregService the tregService to set
	 */
	public void setTregService(TreguServiceInterface tregService) {
		this.tregService = tregService;
	}
	
	
	/**
	 * @return the storeList
	 */
	public List<TreguModel> getStoreList() {
		return storeList;
	}

	/**
	 * @param storeList the storeList to set
	 */
	public void setStoreList(List<TreguModel> storeList) {
		this.storeList = storeList;
	}
	

	/**
	 * @return the filteredStore
	 */
	public List<TreguModel> getFilteredStores() {
		return filteredStores;
	}

	/**
	 * @param filteredStore the filteredStore to set
	 */
	public void setFilteredStores(List<TreguModel> filteredStores) {
		this.filteredStores = filteredStores;
	}

	public String moveToTregPannel() {
		return "tregjet.xhtml?faces-redirect=true";
	}

	public void krijoTreg() {
		if (tregService.krijoTreg(tregModel)) {
			storeList = tregService.tregoTregjet();
			FacesContextUtil.facesContext("Sukses!", "Tregu u regjistrua me sukses!");
			PrimeFaces.current().ajax().update("formStore:tableStore");
		    tregModel = new TreguModel();
			PrimeFaces.current().executeScript("PF('dlgNew').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Tregu me kete emer ekziton ne tabele!");
		}
	}
	
	public void ndryshoTreg() {
		if (tregService.ndryshoTreg(tregModel)) {
			tregService.tregoTregjet();
			FacesContextUtil.facesContext("Sukses!", "Tregu u ndryshua me sukses!");
			PrimeFaces.current().ajax().update("formStore:tableStore");
			tregModel = new TreguModel();
			PrimeFaces.current().executeScript("PF('entityDialog').hide();");
		} else {
			FacesContextUtil.facesContext("Error!", "Tregu me kete emer ekziton ne tabele!");
		}
	}
	
	public void fshiTreg(TreguModel tregModel) {
		if (tregService.fshiTreg(tregModel)) {
			storeList = tregService.tregoTregjet();
			FacesContextUtil.facesContext("Sukses!", "Tregu u fshi me sukses!");
			PrimeFaces.current().ajax().update("formStore:tableStore");
			
		} else {
			FacesContextUtil.facesContext("Tregu nuk mund te fshihet!", "Ndodhen shites dhe produkte brenda ketij tregu!");

		}
	}
	
	public void edit(TreguModel tregModel) {
		this.tregModel = tregModel;
	}
	
	
	public void handleClose(CloseEvent event) {
		this.tregModel = new TreguModel();
	}
}
