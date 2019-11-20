package repository;

import java.util.List;

import entity.Produkt;

public interface ProduktRepositoryInterface {
	
	public List<Produkt> tregoProduktet();
	
	public boolean krijoProdukt(Produkt produkt);
	
	public Produkt gjejProduktNgaEmri(String emri);
	
	public boolean ndryshoProdukt(Produkt produkt);

	public boolean fshiProdukt(Produkt produkt);
}
