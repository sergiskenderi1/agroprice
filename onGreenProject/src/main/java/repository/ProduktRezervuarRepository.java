package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entity.ProduktRezervuar;
import entity.ProduktNeTreg;
import util.HibernateUtil;

public class ProduktRezervuarRepository implements ProduktRezervuarRepositoryInterface{
	
	private static EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private static EntityTransaction tx = em.getTransaction();
	
	@Override
	public boolean verifikoProduktPerRezervime(int idProduktNeTreg) {
		List<ProduktRezervuar> produkteTeRezervuara = new ArrayList<>();
		try {
			Query query = em.createQuery("Select p from ProduktRezervuar p where id_produktnetreg=:id and valid=:valid");
			query.setParameter("id",idProduktNeTreg);
			query.setParameter("valid", Boolean.TRUE);
			produkteTeRezervuara = query.getResultList();
			if(!produkteTeRezervuara.isEmpty()) {
			return true;
			}
			else 
			return false;
		}catch(Exception e) {
		return false;
		}
	}
	
	@Override
	public List<ProduktRezervuar> tregoProdukteNeRezervim(int idRezervim) {
		try {
			List<ProduktRezervuar> list = new ArrayList<>();
			Query query = em.createQuery("Select p from ProduktRezervuar p where id_rezervim=:id and valid=:valid");
			query.setParameter("id", idRezervim);
			query.setParameter("valid", Boolean.TRUE);
			list = query.getResultList();
			return list;
		}catch (Exception e) {
			return null;
		}
	}

}
