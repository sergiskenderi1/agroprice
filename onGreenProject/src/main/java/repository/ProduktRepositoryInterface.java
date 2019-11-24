package repository;

import java.util.List;

import entity.NjesiMatese;
import entity.Produkt;

public interface ProduktRepositoryInterface {
	
	public List<Produkt> tregoProduktet();
	
	public boolean krijoProdukt(Produkt produkt,String njesiMatese);
	
	public Produkt gjejProduktNgaEmri(String emri);
	
	public boolean ndryshoProdukt(Produkt produkt,String njesiMatese);

	public boolean fshiProdukt(Produkt produkt);
	
	public List<NjesiMatese> tregoNjesiMatese();
}
