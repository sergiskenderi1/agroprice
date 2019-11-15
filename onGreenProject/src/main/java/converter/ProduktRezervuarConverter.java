package converter;

import entity.ProduktRezervuar;
import model.ProduktRezervuarModel;

public class ProduktRezervuarConverter {

	public static ProduktRezervuar convertToEntity(ProduktRezervuarModel model) {
		ProduktRezervuar entity = new ProduktRezervuar();
		entity.setId(model.getId());
		entity.setCmimi(model.getCmimi());
		entity.setProduktNeTreg(model.getProduktNeTreg());
		entity.setRezervim(model.getRezervim());
		entity.setSasia(model.getSasia());
		entity.setValid(entity.isValid());
		return entity;
	}
	
	public static ProduktRezervuarModel convertToModel(ProduktRezervuar entity) {
		ProduktRezervuarModel model = new ProduktRezervuarModel();
		model.setId(entity.getId());
		model.setCmimi(entity.getCmimi());
		model.setProduktNeTreg(entity.getProduktNeTreg());
		model.setRezervim(entity.getRezervim());
		model.setSasia(entity.getSasia());
		model.setValid(entity.isValid());
		return model;
	}
}
