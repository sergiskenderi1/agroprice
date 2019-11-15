package converter;

import entity.Tregu;
import model.TreguModel;

public class TreguConverter {

	public static Tregu convertToEntity(TreguModel model) {
		Tregu entity = new Tregu();
		entity.setAdresa(model.getAdresa());
		entity.setCelular(model.getCelular());
		entity.setEmri(model.getEmri());
		entity.setId(model.getId());
		entity.setValid(model.isValid());
		return entity;
	}
	
	public static TreguModel convertToModel(Tregu entity) {
		TreguModel model = new TreguModel();
		model.setAdresa(entity.getAdresa());
		model.setCelular(entity.getCelular());
		model.setEmri(entity.getEmri());
		model.setId(entity.getId());
		model.setValid(entity.isValid());
		return model;
	}
}
