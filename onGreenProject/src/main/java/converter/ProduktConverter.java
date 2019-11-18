package converter;

import entity.Produkt;
import model.ProduktModel;

public class ProduktConverter {

	public static Produkt convertToProduktEntity(ProduktModel model) {
		Produkt entity = new Produkt();
		entity.setIdprodukt(model.getId());
		entity.setEmri(model.getEmri());
		entity.setCmimi(model.getCmimi());
		entity.setValid(model.isValid());
		return entity;
	}
	
	public static ProduktModel convertToProduktModel(Produkt entity) {
		ProduktModel model = new ProduktModel();
		model.setId(entity.getIdprodukt());
		model.setEmri(entity.getEmri());
		model.setCmimi(entity.getCmimi());
		model.setValid(entity.isValid());
		return model;
	}
}
