package service;

import java.util.ArrayList;
import java.util.List;

import converter.NjesiMateseConverter;
import converter.ProduktConverter;
import entity.NjesiMatese;
import entity.Produkt;
import entity.ProduktNeTreg;
import entity.ProduktRezervuar;
import entity.Rezervim;
import model.NjesiMateseModel;
import model.ProduktModel;
import repository.ProduktNeTregRepository;
import repository.ProduktNeTregRepositoryInterface;
import repository.ProduktRepository;
import repository.ProduktRepositoryInterface;
import repository.ProduktRezervuarRepository;
import repository.ProduktRezervuarRepositoryInterface;
import repository.RezervimRepository;
import repository.RezervimRepositoryInterface;

public class ProduktService implements ProduktServiceInterface{

	ProduktRepositoryInterface productRepository = new ProduktRepository();
	
	@Override
	public List<ProduktModel> tregoProduktet() {
		List<ProduktModel> productModels = new ArrayList<>();
		for (Produkt product : productRepository.tregoProduktet()) {
			productModels.add(ProduktConverter.convertToProduktModel(product));
		}
		return productModels;
	}
	
	@Override
	public boolean krijoProdukt(ProduktModel produktModel,String njesiMatese) {
		if((productRepository.gjejProduktNgaEmri(produktModel.getEmri()) == null) &&
			 (produktModel.getCmimiMin() <= produktModel.getCmimiMax())) {
			return productRepository.krijoProdukt(ProduktConverter.convertToProduktEntity(produktModel),njesiMatese);
		}else
			return false;
	}
	
	@Override
	public boolean ndryshoProdukt(ProduktModel produktModel,String njesiMatese) {
		if (productRepository.gjejProduktNgaEmri(produktModel.getEmri()) == null 
				&& produktModel.getCmimiMin() <= produktModel.getCmimiMax()) {
			return productRepository.ndryshoProdukt(ProduktConverter.convertToProduktEntity(produktModel),njesiMatese);
		}
		if (productRepository.gjejProduktNgaEmri(produktModel.getEmri()).getIdprodukt() == produktModel.getId()
				&& produktModel.getCmimiMin() <= produktModel.getCmimiMax()) {
			return productRepository.ndryshoProdukt(ProduktConverter.convertToProduktEntity(produktModel),njesiMatese);
		} else {
			return false;
		}
	}
	
	@Override
	public boolean verifikoProduktPerTeFshire(ProduktModel produktModel) {
        ProduktNeTregRepositoryInterface produktRepository = new ProduktNeTregRepository();
        if(produktRepository.gjejProduktNeTregNgaId(produktModel.getId()).isEmpty()) {
        	return true;
        }
		return false;
	}
	
	@Override
	public boolean fshiProdukt(ProduktModel produktModel) {
		ProduktNeTregRepositoryInterface produktNeTregRepository = new ProduktNeTregRepository();
		if(produktNeTregRepository.gjejProduktNeTregNgaId(produktModel.getId()).isEmpty()) {
		return productRepository.fshiProdukt(ProduktConverter.convertToProduktEntity(produktModel));
		}else {
			return false;
		}
	}
	
	@Override
	public List<NjesiMateseModel> tregoNjesiMatese() {
		List<NjesiMateseModel> njesiMatese = new ArrayList<>();
		for(NjesiMatese entity : productRepository.tregoNjesiMatese()) {
			njesiMatese.add(NjesiMateseConverter.convertToNjesiMateseModel(entity));
		}
		return njesiMatese;
	}
	
	@Override
	public int gjejSasiNeTregje(ProduktModel produktModel) {
		List<ProduktNeTreg> produkteNeTregje = new ArrayList<>();
		ProduktNeTregRepositoryInterface produktNeTregRepository = new ProduktNeTregRepository();
		produkteNeTregje = produktNeTregRepository.gjejProduktNeTregNgaId(produktModel.getId());
		int sasia = 0;
		for(ProduktNeTreg produktNeTreg : produkteNeTregje) {
			sasia += produktNeTreg.getSasiaNeTreg();
		}
		return sasia;
	}
	
	@Override
	public float gjejCmiminMesatar(ProduktModel produktModel) {
		List<ProduktNeTreg> produkteNeTregje = new ArrayList<>();
		ProduktNeTregRepositoryInterface produktNeTregRepository = new ProduktNeTregRepository();
		produkteNeTregje = produktNeTregRepository.gjejProduktNeTregNgaId(produktModel.getId());
		float cmimiMesatar = 0 ;
		for(ProduktNeTreg produktNeTreg : produkteNeTregje) {
			cmimiMesatar += produktNeTreg.getCmimiShites();
		}
		
		cmimiMesatar /= produkteNeTregje.size();
		return cmimiMesatar;
	}
	
	@Override
	public float gjejCmiminMesatarPerMuaj(ProduktModel produktModel, int muaji) {
		float cmimiTotal=0;
		int i=0;
		try {
		RezervimRepositoryInterface rezervimRepository = new RezervimRepository();
		ProduktRezervuarRepositoryInterface produktRezervuarRepository = new ProduktRezervuarRepository();
		List<Rezervim> rezervime = new ArrayList<>();
		rezervime = rezervimRepository.gjejRezervimeNeMuaj(muaji);
		List<ProduktRezervuar> produkteTeRezervuara = new ArrayList<>();
		for(Rezervim rezervim : rezervime) {
			produkteTeRezervuara = produktRezervuarRepository.tregoProdukteNeRezervim(rezervim.getIdrezervim());
			for(ProduktRezervuar produkte : produkteTeRezervuara) {
				if(produkte.getProduktNeTreg().getProdukt().getIdprodukt() == produktModel.getId()) {
					cmimiTotal += (produkte.getCmimi()/produkte.getSasia());
					i++;
				}
			}
			produkteTeRezervuara = new ArrayList<>();
		}
		return (cmimiTotal/i);
		}catch (Exception e) {
			return 0;
		}
	}
	
	@Override
	public float gjejCmiminMeTeVogel(ProduktModel produktModel) {
		List<ProduktNeTreg> produkteNeTregje = new ArrayList<>();
		ProduktNeTregRepositoryInterface produktNeTregRepository = new ProduktNeTregRepository();
		produkteNeTregje = produktNeTregRepository.gjejProduktNeTregNgaId(produktModel.getId());
		float cmimiMin = produktModel.getCmimiMax();
		for(ProduktNeTreg produktNeTreg : produkteNeTregje) {
			if(produktNeTreg.getCmimiShites() < cmimiMin) {
				cmimiMin = produktNeTreg.getCmimiShites();
			}
		}
		if(produkteNeTregje.isEmpty()) {
			cmimiMin = 0;
		}
		return cmimiMin;
	}
	
@Override
	public float gjejCmiminMeTeLarte(ProduktModel produktModel) {
	List<ProduktNeTreg> produkteNeTregje = new ArrayList<>();
	ProduktNeTregRepositoryInterface produktNeTregRepository = new ProduktNeTregRepository();
	produkteNeTregje = produktNeTregRepository.gjejProduktNeTregNgaId(produktModel.getId());
	float cmimiMax = produktModel.getCmimiMin();
	for(ProduktNeTreg produktNeTreg : produkteNeTregje) {
		if(produktNeTreg.getCmimiShites() > cmimiMax) {
			cmimiMax = produktNeTreg.getCmimiShites();
		}
	}
	if(produkteNeTregje.isEmpty()) {
		cmimiMax = 0;
	}
	return cmimiMax;
	}
}
