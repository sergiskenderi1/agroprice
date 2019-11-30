package service;

import java.util.List;

import model.RezervimModel;

public interface RezervimServiceInterface {

	public List<RezervimModel> tregoRezervimePerfunduaraShites(int idShites);
	
	public List<RezervimModel> tregoRezervimeShites(int idShites);
	
	public List<RezervimModel> tregoRezervimeNePritjeShites(int idShites);
	
	public boolean konfirmoRezervim(RezervimModel rezervim);
	
	public boolean pranoRezervim(RezervimModel rezervim);
	
	public List<RezervimModel> tregoRezervimeNePritjeKlient(int idKlient);
	
	public List<RezervimModel> tregoRezervimeKlient(int idKlient);
	
	public List<RezervimModel> tregoRezervimeTeKrijuaraKlient(int idKlient);
	
	public boolean dergoRezervim(RezervimModel rezervim);
	
	public boolean fshiRezervim(RezervimModel rezervim);
}
