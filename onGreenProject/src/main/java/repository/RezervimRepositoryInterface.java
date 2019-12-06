package repository;

import java.util.List;

import entity.Rezervim;

public interface RezervimRepositoryInterface {

	public List<Rezervim> tregoRezervimePerfunduaraShites(int idShites);
	
	public List<Rezervim> tregoRezervimeShites(int idShites);
	
	public List<Rezervim> tregoRezervimeNePritjeShites(int idShites);
	
	public List<Rezervim> tregoRezervimeNePritjeKlient(int idKlient);
	
	public boolean konfirmoRezervim(Rezervim rezervim);
	
	public boolean pranoRezervim(Rezervim rezervim);
	
	public boolean refuzoRezervim(Rezervim rezervim);
	
	public List<Rezervim> tregoRezervimeKlient(int idKlient);
	
	public Rezervim krijoRezervim(int idKlient,int idShites,float cmimi);
	
	public Rezervim gjejRezervimNePritje(int idKlient,int idShites);
	
	public boolean ndryshoRezervim(Rezervim rezervim);

	public List<Rezervim> tregoRezervimeTeKrijuaraKlient(int idKlient);
	
	public boolean dergoRezervim(Rezervim rezervim);
	
	public boolean fshiRezervim(Rezervim rezervim);
	
	public List<Rezervim> gjejRezervimeNeMuaj(int muaji);
}
