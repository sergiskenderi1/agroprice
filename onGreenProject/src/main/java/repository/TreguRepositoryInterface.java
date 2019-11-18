package repository;

import java.util.List;

import entity.ProduktRezervuar;
import entity.Tregu;

public interface TreguRepositoryInterface {

	public List<Tregu> tregoTregjet();

	public Tregu gjejTregNgaEmri(String emri);

	public boolean krijoTreg(Tregu tregu);
	
	public boolean ndryshoTreg(Tregu tregu);
	
	public boolean fshiTreg(Tregu tregu);
	
	public boolean verifikoTreg(Tregu tregu);

}
