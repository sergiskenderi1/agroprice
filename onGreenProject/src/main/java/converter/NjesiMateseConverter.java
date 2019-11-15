package converter;

import entity.NjesiMatese;
import model.NjesiMateseModel;

public class NjesiMateseConverter {

	public static NjesiMatese convertToNjesiMatese(NjesiMateseModel model) {
		NjesiMatese entity = new NjesiMatese();
		entity.setEmri(model.getEmri());
		entity.setId(model.getId());
		entity.setPershkrim(model.getPershkrim());
		return entity;
	}
	
	public static NjesiMateseModel convertToNjesiMateseEntity(NjesiMatese entity) {
		NjesiMateseModel model = new NjesiMateseModel();
		model.setEmri(entity.getEmri());
		model.setId(entity.getId());
		model.setPershkrim(entity.getPershkrim());
		return model;
	}
}
