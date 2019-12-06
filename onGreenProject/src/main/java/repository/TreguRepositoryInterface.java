package repository;

import java.util.List;

import entity.Tregu;

public interface TreguRepositoryInterface {

	public List<Tregu> tregoTregjet();
	
	public List<Tregu> tregoTregjeNgaCmimi(float cmimi);

	public Tregu gjejTregNgaEmri(String emri);

	public boolean krijoTreg(Tregu tregu);
	
	public boolean ndryshoTreg(Tregu tregu);
	
	public boolean fshiTreg(Tregu tregu);
	
	public boolean verifikoTreg(Tregu tregu);
	
	public boolean gjejTregNgaID(int idTregu);

}
