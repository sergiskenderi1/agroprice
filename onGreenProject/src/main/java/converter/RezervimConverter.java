package converter;

import entity.Rezervim;
import model.RezervimModel;

public class RezervimConverter {

	public static Rezervim convertToEntity(RezervimModel model) {
		Rezervim entity = new Rezervim();
		entity.setId(model.getId());
		entity.setCmimiTotal(model.getCmimiTotal());
		entity.setData(model.getData());
		entity.setMesazhi(model.getMesazhi());
		entity.setStatus(model.getStatus());
		entity.setUser(model.getUser());
		entity.setValid(model.isValid());
		return entity;
	}
	
	public static RezervimModel convertToModel(Rezervim entity) {
		RezervimModel model = new RezervimModel();
		model.setId(entity.getId());
		model.setCmimiTotal(entity.getCmimiTotal());
		model.setData(entity.getData());
		model.setMesazhi(entity.getMesazhi());
		model.setStatus(entity.getStatus());
		model.setUser(entity.getUser());
		model.setValid(entity.isValid());
		return model;
	}
}
