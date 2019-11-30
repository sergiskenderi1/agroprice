package service;

import java.util.ArrayList;
import java.util.List;

import converter.ProduktRezervuarConverter;
import entity.ProduktRezervuar;
import entity.Rezervim;
import model.ProduktRezervuarModel;
import repository.ProduktRezervuarRepository;
import repository.ProduktRezervuarRepositoryInterface;
import repository.RezervimRepository;
import repository.RezervimRepositoryInterface;

public class ProduktRezervuarService implements ProduktRezervuarServiceInterface {

	ProduktRezervuarRepositoryInterface produktRezervuarRepository = new ProduktRezervuarRepository();

	@Override
	public List<ProduktRezervuarModel> tregoProdukteNeRezervim(int idRezervim) {
		List<ProduktRezervuarModel> model = new ArrayList<>();
		for (ProduktRezervuar entity : produktRezervuarRepository.tregoProdukteNeRezervim(idRezervim)) {
			model.add(ProduktRezervuarConverter.convertToModel(entity));
		}
		return model;
	}

	@Override
	public boolean rezervoProdukt(ProduktRezervuarModel produktRezervuar, int idKlient) {
		RezervimRepositoryInterface rezervimRepository = new RezervimRepository();
		Rezervim rezervim = new Rezervim();
		if (produktRezervuar.getSasia() < produktRezervuar.getProduktNeTreg().getSasiaNeTreg()) {
			if (rezervimRepository.gjejRezervimNePritje(idKlient,
					produktRezervuar.getProduktNeTreg().getUser().getIduseri()) == null) {
				// krijo rezervim + shto kete produkt ne rezervimin e sapokrijuar
				float cmimi = produktRezervuar.getSasia() * produktRezervuar.getProduktNeTreg().getCmimiShites();
				produktRezervuar.setCmimi(cmimi);
				rezervim = rezervimRepository.krijoRezervim(idKlient,
						produktRezervuar.getProduktNeTreg().getUser().getIduseri(),cmimi);
				produktRezervuar.setRezervim(rezervim);
				return produktRezervuarRepository
						.rezervoProdukt(ProduktRezervuarConverter.convertToEntity(produktRezervuar));
			} else {
				// thjesht shton produktin ne rezervimin e krijuar me siper + kontrollon nese e ka porositur njeher kete produkt
				rezervim = rezervimRepository.gjejRezervimNePritje(idKlient, 
						produktRezervuar.getProduktNeTreg().getUser().getIduseri());
				List<ProduktRezervuarModel> produkteNeRezervim = tregoProdukteNeRezervim(rezervim.getIdrezervim());
				for(ProduktRezervuarModel produkte : produkteNeRezervim) {
					if(produkte.getProduktNeTreg().getId() == produktRezervuar.getProduktNeTreg().getId()) {
						return false;
					}
				}
					float cmimi = produktRezervuar.getSasia() * produktRezervuar.getProduktNeTreg().getCmimiShites();
					rezervim.setCmimiTotal(rezervim.getCmimiTotal() + cmimi);
					produktRezervuar.setCmimi(cmimi);
					produktRezervuar.setRezervim(rezervim);
					return (rezervimRepository.ndryshoRezervim(rezervim) &&
							produktRezervuarRepository.rezervoProdukt(ProduktRezervuarConverter.convertToEntity(produktRezervuar)));
			}
		} else
			return false;
	}
	
	@Override
	public boolean ndryshoProduktNeRezervim(ProduktRezervuarModel produkt) {
		if(produkt.getSasia() > produkt.getProduktNeTreg().getSasiaNeTreg()) {
			return false;
		}else {
			float cmimiTotal = produkt.getRezervim().getCmimiTotal() - produkt.getCmimi();
			cmimiTotal += produkt.getSasia() * produkt.getProduktNeTreg().getCmimiShites();
			produkt.getRezervim().setCmimiTotal(cmimiTotal);
			produkt.setCmimi(produkt.getProduktNeTreg().getCmimiShites() * produkt.getSasia());
			RezervimRepositoryInterface rezervimRepository = new RezervimRepository();
			rezervimRepository.ndryshoRezervim(produkt.getRezervim());
			return produktRezervuarRepository.ndryshoProduktNeRezervim(ProduktRezervuarConverter.convertToEntity(produkt));
		}
	}
	
	@Override
	public boolean fshiProdukt(ProduktRezervuarModel produkt) {
		float cmimiTotal = produkt.getRezervim().getCmimiTotal() - produkt.getCmimi();
		produkt.getRezervim().setCmimiTotal(cmimiTotal);
		RezervimRepositoryInterface rezervimRepository = new RezervimRepository();
		rezervimRepository.ndryshoRezervim(produkt.getRezervim());
		return produktRezervuarRepository.fshiProdukt(ProduktRezervuarConverter.convertToEntity(produkt));
	}
}
