package repository;

import java.util.List;
import entity.ProduktNeTreg;

public interface ProduktNeTregRepositoryInterface {

	public List<ProduktNeTreg> gjejProduktNeTregNgaId(int idProdukt);
	
	public List<ProduktNeTreg> tregoProdukteNgaShites(int idShites);
	
	public boolean ndryshoProduktNeTreg(ProduktNeTreg produktNeTreg);
	
	public boolean fshiProduktNeTreg(int idProduktNeTreg);
	
	public boolean shtoProduktNeTreg(ProduktNeTreg produktNeTreg);
	
	public List<ProduktNeTreg> tregoProdukteNeTregje();
}
