package repository;

import java.util.List;
import entity.ProduktRezervuar;

public interface ProduktRezervuarRepositoryInterface{

	public boolean verifikoProduktPerRezervime(int idProduktNeTreg);
	
	public List<ProduktRezervuar> tregoProdukteNeRezervim(int idRezervim);
}
