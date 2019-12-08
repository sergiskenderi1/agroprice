package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import converter.RezervimConverter;
import entity.ProduktRezervuar;
import entity.Rezervim;
import model.RezervimModel;
import repository.ProduktNeTregRepository;
import repository.ProduktNeTregRepositoryInterface;
import repository.ProduktRezervuarRepository;
import repository.ProduktRezervuarRepositoryInterface;
import repository.RezervimRepository;
import repository.RezervimRepositoryInterface;

public class RezervimService implements RezervimServiceInterface {

	RezervimRepositoryInterface rezervimRepository = new RezervimRepository();

	@Override
	public List<RezervimModel> tregoRezervimePerfunduaraShites(int idShites) {
		List<RezervimModel> model = new ArrayList<>();
		for (Rezervim rezervim : rezervimRepository.tregoRezervimePerfunduaraShites(idShites)) {
			model.add(RezervimConverter.convertToModel(rezervim));
		}
		return model;
	}

	@Override
	public List<RezervimModel> tregoRezervimeShites(int idShites) {
		List<RezervimModel> model = new ArrayList<>();
		for (Rezervim rezervim : rezervimRepository.tregoRezervimeShites(idShites)) {
			model.add(RezervimConverter.convertToModel(rezervim));
		}
		return model;
	}

	@Override
	public List<RezervimModel> tregoRezervimeNePritjeShites(int idShites) {
		List<RezervimModel> model = new ArrayList<>();
		for (Rezervim rezervim : rezervimRepository.tregoRezervimeNePritjeShites(idShites)) {
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
		ProduktNeTregRepositoryInterface produktRepository = new ProduktNeTregRepository();
		ProduktRezervuarRepositoryInterface produktRezervuarRepository = new ProduktRezervuarRepository();
		List<ProduktRezervuar> produkteNeRezervim = new ArrayList<>();
		produkteNeRezervim = produktRezervuarRepository.tregoProdukteNeRezervim(rezervim.getId());
		for (ProduktRezervuar produkt : produkteNeRezervim) {
			produkt.getProduktNeTreg().setSasiaNeTreg(produkt.getProduktNeTreg().getSasiaNeTreg() - produkt.getSasia());
			if (!produktRepository.ndryshoProduktNeTreg(produkt.getProduktNeTreg())) {
				return false;
			}
		}
		return rezervimRepository.pranoRezervim(RezervimConverter.convertToEntity(rezervim));
	}

	@Override
	public boolean refuzoRezervim(RezervimModel rezervim) {
		return rezervimRepository.refuzoRezervim(RezervimConverter.convertToEntity(rezervim));
	}

	@Override
	public List<RezervimModel> tregoRezervimeNePritjeKlient(int idKlient) {
		List<RezervimModel> model = new ArrayList<>();
		for (Rezervim rezervim : rezervimRepository.tregoRezervimeNePritjeKlient(idKlient)) {
			model.add(RezervimConverter.convertToModel(rezervim));
		}
		return model;
	}

	@Override
	public List<RezervimModel> tregoRezervimeKlient(int idKlient) {
		List<RezervimModel> model = new ArrayList<>();
		for (Rezervim rezervim : rezervimRepository.tregoRezervimeKlient(idKlient)) {
			model.add(RezervimConverter.convertToModel(rezervim));
		}
		return model;
	}

	@Override
	public List<RezervimModel> tregoRezervimeTeKrijuaraKlient(int idKlient) {
		List<RezervimModel> model = new ArrayList<>();
		for (Rezervim rezervim : rezervimRepository.tregoRezervimeTeKrijuaraKlient(idKlient)) {
			model.add(RezervimConverter.convertToModel(rezervim));
		}
		return model;
	}

	@Override
	public boolean dergoRezervim(RezervimModel rezervim) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		String dbDate = (String) dateFormat.format(date);
		rezervim.setData(dbDate);
		return rezervimRepository.dergoRezervim(RezervimConverter.convertToEntity(rezervim));
	}

	@Override
	public boolean fshiRezervim(RezervimModel rezervim) {
		ProduktRezervuarRepositoryInterface produktRepository = new ProduktRezervuarRepository();
		List<ProduktRezervuar> produkte = new ArrayList<>();
		produkte = produktRepository.tregoProdukteNeRezervim(rezervim.getId());
		for (ProduktRezervuar produkt : produkte) {
			if (!produktRepository.fshiProdukt(produkt)) {
				return false;
			}
		}
		return rezervimRepository.fshiRezervim(RezervimConverter.convertToEntity(rezervim));
	}

	@Override
	public List<Integer> statistikaRezervimeTotaleNeMuaj(int muaji, int viti) {
		List<Rezervim> rezervime = new ArrayList<>();
		List<Integer> rezervimeNeJave = new ArrayList<>();
		int java1 = 0;
		int java2 = 0;
		int java3 = 0;
		int java4 = 0;
		try {
			rezervime = rezervimRepository.gjejRezervimeNeMuaj(muaji);
			for(Rezervim rezervim : rezervime) {
				String[] data = rezervim.getData().split("/");
				if(Integer.parseInt(data[2]) == viti) {
					if(Integer.parseInt(data[1]) < 8)
						java1++;
					else if(Integer.parseInt(data[1]) < 15)
						java2++;
					else if(Integer.parseInt(data[1]) < 22)
						java3++;
					else 
						java4++;
				}
			}
			rezervimeNeJave.add(1, java1);
			rezervimeNeJave.add(2, java2);
			rezervimeNeJave.add(3, java3);
			rezervimeNeJave.add(4, java4);
			return rezervimeNeJave;
		}catch (Exception e) {
		return null;
		}
	}
}
