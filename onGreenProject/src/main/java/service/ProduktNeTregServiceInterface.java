package service;

import java.util.List;
import model.ProduktNeTregModel;

public interface ProduktNeTregServiceInterface {

	public List<ProduktNeTregModel> tregoProdukteNgaShites(int idShites);
	
	public boolean ndryshoProduktNeTreg(ProduktNeTregModel produktNeTreg);
	
	public boolean fshiProduktNeTreg(int id);
	
	public boolean fshiProduktNeTreg(ProduktNeTregModel produktNeTreg);
	
	public boolean shtoProduktNeTreg(ProduktNeTregModel produktNeTreg,int idShites);
	
	public List<ProduktNeTregModel> tregoProdukteNeTregje();
	
}
