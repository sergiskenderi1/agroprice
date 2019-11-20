package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entity.Produkt;
import entity.ProduktNeTreg;
import util.HibernateUtil;

public class ProduktNeTregRepository implements ProduktNeTregRepositoryInterface{

	private static EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private static EntityTransaction tx = em.getTransaction();
	
	@Override
	public List<ProduktNeTreg> gjejProduktNeTregNgaId(int idProdukt) {
		List<ProduktNeTreg> produktNeTreg = new  ArrayList<>();
		try {
			Query query = em.createQuery("Select p from ProduktNeTreg p where idProdukt=:idProdukt AND valid=:valid");
			query.setParameter("idProdukt", idProdukt);
			query.setParameter("valid", Boolean.TRUE);
			produktNeTreg = query.getResultList();
			return produktNeTreg;
		}catch (Exception e) {
			return null;
		}
	}
}
