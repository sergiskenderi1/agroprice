package service;

import java.util.ArrayList;
import java.util.List;

import converter.RezervimConverter;
import entity.Rezervim;
import model.RezervimModel;
import repository.RezervimRepository;
import repository.RezervimRepositoryInterface;

public class RezervimService implements RezervimServiceInterface{

	RezervimRepositoryInterface rezervimRepository = new RezervimRepository();
	
	@Override
	public List<RezervimModel> tregoRezervimePerfunduaraShites(int idShites) {
		List<RezervimModel> model = new ArrayList<>();
		for(Rezervim rezervim : rezervimRepository.tregoRezervimePerfunduaraShites(idShites)) {
			model.add(RezervimConverter.convertToModel(rezervim));
		}
		return model;
	}
	
	@Override
	public List<RezervimModel> tregoRezervimeShites(int idShites) {
		List<RezervimModel> model = new ArrayList<>();
		for(Rezervim rezervim : rezervimRepository.tregoRezervimeShites(idShites)) {
			model.add(RezervimConverter.convertToModel(rezervim));
		}
		return model;
	}
	
	@Override
	public List<RezervimModel> tregoRezervimeNePritjeShites(int idShites) {
		List<RezervimModel> model = new ArrayList<>();
		for(Rezervim rezervim : rezervimRepository.tregoRezervimeNePritjeShites(idShites)) {
			model.add(RezervimConverter.convertToModel(rezervim));
		}
		return model;
	}
	
	@Override
	public boolean konfirmoRezervim(RezervimModel rezervim) {
		return rezervimRepository.konfirmoRezervim(RezervimConverter.convertToEntity(rezervim));
	}
	
	@Override
	public boolean pranoRezervim(RezervimModel rezervim) {
		return rezervimRepository.pranoRezervim(RezervimConverter.convertToEntity(rezervim));
	}
	
	@Override
	public List<RezervimModel> tregoRezervimeNePritjeKlient(int idKlient) {
		List<RezervimModel> model = new ArrayList<>();
		for(Rezervim rezervim : rezervimRepository.tregoRezervimeNePritjeKlient(idKlient)) {
			model.add(RezervimConverter.convertToModel(rezervim));
		}
		return model;
	}
	
	@Override
	public List<RezervimModel> tregoRezervimeKlient(int idKlient) {
		List<RezervimModel> model = new ArrayList<>();
		for(Rezervim rezervim : rezervimRepository.tregoRezervimeKlient(idKlient)) {
			model.add(RezervimConverter.convertToModel(rezervim));
		}
		return model;
	}
}
