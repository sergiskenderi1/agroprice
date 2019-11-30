package service;

import java.util.List;

import model.ProduktRezervuarModel;

public interface ProduktRezervuarServiceInterface {
	
	public List<ProduktRezervuarModel> tregoProdukteNeRezervim(int idRezervim);

	public boolean rezervoProdukt(ProduktRezervuarModel produktRezervuar,int idKlient);
	
	public boolean ndryshoProduktNeRezervim(ProduktRezervuarModel produkt);
	
	public boolean fshiProdukt(ProduktRezervuarModel produkt);
}
