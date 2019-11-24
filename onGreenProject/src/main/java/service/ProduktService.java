package service;

import java.util.ArrayList;
import java.util.List;

import converter.NjesiMateseConverter;
import converter.ProduktConverter;
import entity.NjesiMatese;
import entity.Produkt;
import model.NjesiMateseModel;
import model.ProduktModel;
import repository.ProduktNeTregRepository;
import repository.ProduktNeTregRepositoryInterface;
import repository.ProduktRepository;
import repository.ProduktRepositoryInterface;

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
		return productRepository.fshiProdukt(ProduktConverter.convertToProduktEntity(produktModel));
	}
	
	@Override
	public List<NjesiMateseModel> tregoNjesiMatese() {
		List<NjesiMateseModel> njesiMatese = new ArrayList<>();
		for(NjesiMatese entity : productRepository.tregoNjesiMatese()) {
			njesiMatese.add(NjesiMateseConverter.convertToNjesiMateseModel(entity));
		}
		return njesiMatese;
	}
}
