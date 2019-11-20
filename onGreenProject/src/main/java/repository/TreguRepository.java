package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import entity.ProduktNeTreg;
import entity.Tregu;
import entity.User;
import util.HibernateUtil;

public class TreguRepository implements TreguRepositoryInterface{

	private static EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private static EntityTransaction tx = em.getTransaction();
	
	@Override
	public List<Tregu> tregoTregjet() {
		List<Tregu> tregjet = new ArrayList<Tregu>();
		try {
			TypedQuery<Tregu> treguQuery = 
					em.createQuery("Select t from Tregu t where valid=:valid  ", Tregu.class);
			treguQuery.setParameter("valid", Boolean.TRUE);
			tregjet = treguQuery.getResultList();
			return tregjet;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Tregu gjejTregNgaEmri(String emri) {
		try {
			Tregu tregu = new Tregu();
			TypedQuery<Tregu> treguQuery = em
			.createQuery("Select t from Tregu t where emri=:name AND valid=:valid",
							Tregu.class);
			treguQuery.setParameter("name", emri);
			treguQuery.setParameter("valid", Boolean.TRUE);
			tregu = treguQuery.getSingleResult();
			return tregu;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean krijoTreg(Tregu tregu) {
		try {
			tx.begin();
			tregu.setValid(Boolean.TRUE);
			em.persist(tregu);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean ndryshoTreg(Tregu tregu) {
		try {
			tx.begin();
			em.merge(tregu);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean fshiTreg(Tregu tregu) {
		try {
			tx.begin();
			tregu.setValid(Boolean.FALSE);
			em.merge(tregu);
			tx.commit();
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean verifikoTreg(Tregu tregu) {
		try {
			List<ProduktNeTreg> produkt = new ArrayList<>();
			TypedQuery<ProduktNeTreg> produktQuery = em.createQuery(
					"Select pt from ProduktNeTreg pt where treguId=:id AND valid=:valid", ProduktNeTreg.class);
			produktQuery.setParameter("treguId", tregu.getIdtregu());
			produktQuery.setParameter("valid", Boolean.TRUE);
			produkt = produktQuery.getResultList();

			List<User> shites = new ArrayList<>();
			TypedQuery<User> userQuery = em
					.createQuery("Select u from User u where idstore=:idstore AND idrole=:idrole", User.class);
			userQuery.setParameter("idstore",tregu.getIdtregu());
			userQuery.setParameter("idrole", 15);
			shites = userQuery.getResultList();

			if (produkt.isEmpty() && shites.isEmpty()) {
				return true;
			} else
				return false;
		}catch(Exception ex) {
			return false;
		}
	}
	
	@Override
	public boolean gjejTregNgaID(int idTregu) {
		Tregu tregu = new Tregu();
		try {

			TypedQuery<Tregu> storeQuery = em.createQuery("Select t from Tregu t where idtregu=:id AND valid=:valid",
					Tregu.class);
			storeQuery.setParameter("id", idTregu);
			storeQuery.setParameter("valid", Boolean.TRUE);
			tregu = storeQuery.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
