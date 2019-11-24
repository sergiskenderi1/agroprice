package service;

import java.util.ArrayList;
import java.util.List;
import converter.ProduktNeTregConverter;
import entity.ProduktNeTreg;
import entity.User;
import model.ProduktNeTregModel;
import repository.ProduktNeTregRepository;
import repository.ProduktNeTregRepositoryInterface;
import repository.ProduktRezervuarRepository;
import repository.ProduktRezervuarRepositoryInterface;
import repository.UserRepository;
import repository.UserRepositoryInterface;

public class ProduktNeTregService implements ProduktNeTregServiceInterface{
  
	ProduktNeTregRepositoryInterface produktNeTregRepository = new ProduktNeTregRepository();
	
	@Override
	public List<ProduktNeTregModel> tregoProdukteNgaShites(int idShites) {
		List<ProduktNeTregModel> modele = new ArrayList<>();
		for(ProduktNeTreg entity : produktNeTregRepository.tregoProdukteNgaShites(idShites)) {
			modele.add(ProduktNeTregConverter.convertToModel(entity));
		}
		return modele;
	}
	
	@Override
	public boolean ndryshoProduktNeTreg(ProduktNeTregModel produktNeTreg) {
	    if(produktNeTreg.getCmimiShites() >= produktNeTreg.getProdukt().getCmimiMin() && produktNeTreg.getCmimiShites() <= 
	    		produktNeTreg.getProdukt().getCmimiMax()) {
	    	return produktNeTregRepository.ndryshoProduktNeTreg(ProduktNeTregConverter.convertToEntity(produktNeTreg));
	    }else
		return false;
	}
	
	@Override
	public boolean fshiProduktNeTreg(int id) {
		ProduktRezervuarRepositoryInterface produktRezervuar = new ProduktRezervuarRepository();
		if(!produktRezervuar.verifikoProduktPerRezervime(id)) {
			return produktNeTregRepository.fshiProduktNeTreg(id);
		}else {
			return false;
		}
	}
	
	@Override
	public boolean shtoProduktNeTreg(ProduktNeTregModel produktNeTreg,int idShites) {
		UserRepositoryInterface userRepository = new UserRepository();
		User shites = userRepository.getUserById(idShites);
		produktNeTreg.setUser(shites);
		produktNeTreg.setTregu(shites.getTregu());
		if(produktNeTreg.getCmimiShites() >= produktNeTreg.getProdukt().getCmimiMin() && produktNeTreg.getCmimiShites() <= 
	    		produktNeTreg.getProdukt().getCmimiMax())
		return produktNeTregRepository.shtoProduktNeTreg(ProduktNeTregConverter.convertToEntity(produktNeTreg));
		else
			return false;
	}
	
	@Override
	public List<ProduktNeTregModel> tregoProdukteNeTregje() {
		List<ProduktNeTregModel> modele = new ArrayList<>();
		for(ProduktNeTreg entity : produktNeTregRepository.tregoProdukteNeTregje()) {
			modele.add(ProduktNeTregConverter.convertToModel(entity));
		}
		return modele;
	}
	
	@Override
	public boolean fshiProduktNeTreg(ProduktNeTregModel produktNeTreg) {
		ProduktRezervuarRepositoryInterface produktRezervuar = new ProduktRezervuarRepository();
		if(!produktRezervuar.verifikoProduktPerRezervime(produktNeTreg.getId())) {
			return produktNeTregRepository.fshiProduktNeTreg(produktNeTreg.getId());
		}else {
			return false;
		}
	}
}
