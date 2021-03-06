package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.NjesiMatese;
import entity.Produkt;
import util.HibernateUtil;

public class ProduktRepository implements ProduktRepositoryInterface{

	private static EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private static EntityTransaction tx = em.getTransaction();
	
	@Override
	public List<Produkt> tregoProduktet() {
		List<Produkt> products = new ArrayList<>();
		try {
			TypedQuery<Produkt> query = em.createQuery("Select p from Produkt p where valid=:valid", Produkt.class);
			query.setParameter("valid", Boolean.TRUE);
			products = query.getResultList();
			return products;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean krijoProdukt(Produkt produkt,String njesiMatese) {
		try {
			NjesiMatese njesi = new NjesiMatese();
			Query query = em.createQuery("Select n from NjesiMatese n where emri=:emri");
			query.setParameter("emri", njesiMatese);
			njesi =(NjesiMatese) query.getSingleResult();
			tx.begin();
			produkt.setValid(Boolean.TRUE);
			produkt.setNjesiMatese(njesi);
			em.persist(produkt);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Produkt gjejProduktNgaEmri(String emri) {
		Produkt product = new Produkt();
		try {
			TypedQuery<Produkt> productQuery = em
					.createQuery("Select p from Produkt p where valid=:valid and emri=:emri ", Produkt.class);
			productQuery.setParameter("valid", Boolean.TRUE);
			productQuery.setParameter("emri", emri);
			product = productQuery.getSingleResult();
			return product;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean ndryshoProdukt(Produkt produkt,String njesiMatese) {
		try {
			NjesiMatese njesi = new NjesiMatese();
			Query query = em.createQuery("Select n from NjesiMatese n where emri=:emri");
			query.setParameter("emri", njesiMatese);
			njesi =(NjesiMatese) query.getSingleResult();
			tx.begin();
			produkt.setNjesiMatese(njesi);
			em.merge(produkt);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean fshiProdukt(Produkt produkt) {
		try {
			tx.begin();
			produkt.setValid(Boolean.FALSE);
			em.merge(produkt);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public List<NjesiMatese> tregoNjesiMatese() {
		List<NjesiMatese> njesiMatese = new ArrayList<>();
		try {
			Query query = em.createQuery("Select n from NjesiMatese n");
			njesiMatese = query.getResultList();
			return njesiMatese;
		}catch (Exception e) {
			return null;
		}
	}
}
