package repository;

import java.util.List;

import entity.Rezervim;

public interface RezervimRepositoryInterface {

	public List<Rezervim> tregoRezervimePerfunduaraShites(int idShites);
	
	public List<Rezervim> tregoRezervimeShites(int idShites);
	
	public List<Rezervim> tregoRezervimeNePritjeShites(int idShites);
	
	public List<Rezervim> tregoRezervimeNePritjeKlient(int idShites);
	
	public boolean konfirmoRezervim(Rezervim rezervim);
	
	public boolean pranoRezervim(Rezervim rezervim);
	
	public List<Rezervim> tregoRezervimeKlient(int idKlient);
}
