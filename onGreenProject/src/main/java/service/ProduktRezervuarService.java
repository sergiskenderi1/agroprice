package service;

import java.util.ArrayList;
import java.util.List;

import converter.ProduktRezervuarConverter;
import entity.ProduktRezervuar;
import model.ProduktRezervuarModel;
import repository.ProduktRezervuarRepository;
import repository.ProduktRezervuarRepositoryInterface;

public class ProduktRezervuarService implements ProduktRezervuarServiceInterface{

	ProduktRezervuarRepositoryInterface produktRezervuarRepository = new ProduktRezervuarRepository();
	
	@Override
	public List<ProduktRezervuarModel> tregoProdukteNeRezervim(int idRezervim) {
		List<ProduktRezervuarModel> model = new ArrayList<>();
		for(ProduktRezervuar entity : produktRezervuarRepository.tregoProdukteNeRezervim(idRezervim)) {
			model.add(ProduktRezervuarConverter.convertToModel(entity));
		}
		return model;
	}
}
