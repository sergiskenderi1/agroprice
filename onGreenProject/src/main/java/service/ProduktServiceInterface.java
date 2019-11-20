package service;

import java.util.List;

import model.ProduktModel;

public interface ProduktServiceInterface {

	public List<ProduktModel> tregoProduktet();
	
	public boolean krijoProdukt(ProduktModel produktModel);
	
	public boolean ndryshoProdukt(ProduktModel produktModel);
	
	public boolean verifikoProduktPerTeFshire(ProduktModel produktModel);
	
	public boolean fshiProdukt(ProduktModel produktModel);
}
