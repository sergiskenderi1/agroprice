package converter;

import entity.Status;
import model.StatusModel;

public class StatusConverter {

	public static Status convertToEntity(StatusModel model) {
		Status entity = new Status();
		entity.setId(model.getId());
		entity.setEmri(model.getEmri());
		entity.setPershkrim(model.getPershkrim());
		return entity;
	}
	
	public static StatusModel convertToModel(Status entity) {
		StatusModel model = new StatusModel();
		model.setId(entity.getId());
		model.setEmri(entity.getEmri());
		model.setPershkrim(entity.getPershkrim());
		return model;
	}
}
