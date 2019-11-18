package service;

import java.util.List;

import model.TreguModel;

public interface TreguServiceInterface {

	public List<TreguModel> tregoTregjet();

	public boolean krijoTreg(TreguModel tregModel);
	
	public boolean ndryshoTreg(TreguModel tregModel);
	
	public boolean fshiTreg(TreguModel tregModel);
}
