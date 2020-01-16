package service;

import java.util.ArrayList;
import java.util.List;

import converter.TreguConverter;
import entity.ProduktNeTreg;
import entity.Tregu;
import model.TreguModel;
import repository.ProduktNeTregRepository;
import repository.ProduktNeTregRepositoryInterface;
import repository.ProduktRezervuarRepository;
import repository.ProduktRezervuarRepositoryInterface;
import repository.RezervimRepository;
import repository.RezervimRepositoryInterface;
import repository.TreguRepository;
import repository.TreguRepositoryInterface;

public class TreguService implements TreguServiceInterface {

	public TreguRepositoryInterface treguRepository = new TreguRepository();

	public List<TreguModel> tregoTregjet() {
		List<TreguModel> treguModels = new ArrayList<TreguModel>();
		for (Tregu tregu : treguRepository.tregoTregjet()) {
			treguModels.add(TreguConverter.convertToModel(tregu));
		}
		return treguModels;
	}

	@Override
	public boolean krijoTreg(TreguModel tregModel) {
		if (treguRepository.gjejTregNgaEmri(tregModel.getEmri()) == null) {
			return treguRepository.krijoTreg(TreguConverter.convertToEntity(tregModel));
		} else
			return false;
	}

	@Override
	public boolean ndryshoTreg(TreguModel tregModel) {
		if (treguRepository.gjejTregNgaEmri(tregModel.getEmri()) == null) {
			return treguRepository.ndryshoTreg(TreguConverter.convertToEntity(tregModel));
		} else
			return false;
	}
	
	@Override
	public boolean fshiTreg(TreguModel tregModel) {
		RezervimRepositoryInterface rezervimRepository = new RezervimRepository();
		if(rezervimRepository.rezervimeAktiveNeTreg(tregModel.getId()).size() == 0) {
			return treguRepository.fshiTreg(TreguConverter.convertToEntity(tregModel));
		}else
		return false;
	}
	
	@Override
	public TreguModel gjejTregNgaEmri(String emri) {
		
		return TreguConverter.convertToModel(treguRepository.gjejTregNgaEmri(emri));
	}
	
	@Override
	public boolean gjejTregNgaId(int idTreg) {
		 if(!treguRepository.gjejTregNgaID(idTreg))
			return false;
		else 
			return true;
	}
	
	@Override
	public int sasiaProdukteve(TreguModel treguModel) {
		ProduktNeTregRepositoryInterface produktRepository = new ProduktNeTregRepository();
		List<ProduktNeTreg> produkte = new ArrayList<>();
		produkte = produktRepository.tregoProdukteNeTreg(treguModel.getId());
		int sasiaTotale = 0;
		for(ProduktNeTreg produkt : produkte) {
			sasiaTotale += produkt.getSasiaNeTreg();
		}
		return sasiaTotale;
	}
	
	@Override
	public String produktiMeIShitur(TreguModel treguModel) {
		ProduktNeTregRepositoryInterface produktRepository = new ProduktNeTregRepository();
		List<ProduktNeTreg> produkte = new ArrayList<>();
		produkte = produktRepository.tregoProdukteNeTreg(treguModel.getId());
		int sasia = 0 ;
		String emri="";
		ProduktRezervuarRepositoryInterface produktRezervuarRepository = new ProduktRezervuarRepository();
		for(ProduktNeTreg produkt : produkte) {
			if(sasia < produktRezervuarRepository.tregoSasiRezervuar(produkt)) {
				sasia = produktRezervuarRepository.tregoSasiRezervuar(produkt);
				emri  = produkt.getProdukt().getEmri();
			}
		}
		return emri;
	}
}
