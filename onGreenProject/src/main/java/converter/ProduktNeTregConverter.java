package converter;

import entity.ProduktNeTreg;
import model.ProduktNeTregModel;

public class ProduktNeTregConverter {

	public static ProduktNeTreg convertToEntity(ProduktNeTregModel model) {
		ProduktNeTreg entity = new ProduktNeTreg();
		entity.setId(model.getId());
		entity.setNjesiMatese(model.getNjesiMatese());
		entity.setProdukt(model.getProdukt());
		entity.setSasiaNeTreg(model.getSasiaNeTreg());
		entity.setTregu(model.getTregu());
		entity.setUser(model.getUser());
		entity.setValid(model.isValid());
		return entity;
	}
	
	public static ProduktNeTregModel convertToModel(ProduktNeTreg entity) {
		ProduktNeTregModel model = new ProduktNeTregModel();
	    model.setId(entity.getId());
	    model.setNjesiMatese(entity.getNjesiMatese());
	    model.setProdukt(entity.getProdukt());
	    model.setSasiaNeTreg(entity.getSasiaNeTreg());
	    model.setTregu(entity.getTregu());
	    model.setUser(entity.getUser());
	    model.setValid(entity.isValid());
	    return model;
	}
}
