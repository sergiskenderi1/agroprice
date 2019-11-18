package service;

import java.util.ArrayList;
import java.util.List;

import converter.TreguConverter;
import entity.ProduktRezervuar;
import entity.Tregu;
import model.TreguModel;
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
		if(treguRepository.verifikoTreg(TreguConverter.convertToEntity(tregModel))) {
			return treguRepository.fshiTreg(TreguConverter.convertToEntity(tregModel));
		}else
		return false;
	}
}
