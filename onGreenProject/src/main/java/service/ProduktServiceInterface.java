package service;

import java.util.List;

import model.NjesiMateseModel;
import model.ProduktModel;

public interface ProduktServiceInterface {

	public List<ProduktModel> tregoProduktet();
	
	public boolean krijoProdukt(ProduktModel produktModel,String njesiMatese);
	
	public boolean ndryshoProdukt(ProduktModel produktModel,String njesiMatese);
	
	public boolean fshiProdukt(ProduktModel produktModel);
	
	public List<NjesiMateseModel> tregoNjesiMatese();
	
	public int gjejSasiNeTregje(ProduktModel produktModel);
	
	public float gjejCmiminMesatar(ProduktModel produktModel);
	
	public float gjejCmiminMesatarPerMuaj(ProduktModel produktModel,int muaji,int viti);
	
	public float gjejCmiminMeTeVogel(ProduktModel produktModel);
	
	public float gjejCmiminMeTeLarte(ProduktModel produktModel);
}
