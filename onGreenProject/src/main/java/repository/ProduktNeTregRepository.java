package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entity.Produkt;
import entity.ProduktNeTreg;
import entity.Tregu;
import entity.User;
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
	
	@Override
	public List<ProduktNeTreg> tregoProdukteNgaShites(int idShites) {
		List<ProduktNeTreg> produktNeTreg = new  ArrayList<>();
		try {
			Query query = em.createQuery("Select p from ProduktNeTreg p where idshites=:idShites AND valid=:valid");
			query.setParameter("idShites", idShites);
			query.setParameter("valid", Boolean.TRUE);
			produktNeTreg = query.getResultList();
			return produktNeTreg;
		}catch (Exception e) {
			System.out.print("U KTHYE NULL " + e );
			return null;
		}
	}
	
	@Override
	public boolean ndryshoProduktNeTreg(ProduktNeTreg produktNeTreg) {
		try {
			tx.begin();
			em.merge(produktNeTreg);
			tx.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean fshiProduktNeTreg(int idProduktNeTreg) {
		try {
            ProduktNeTreg produktNeTreg = new ProduktNeTreg();
            Query query = em.createQuery("Select p from ProduktNeTreg p where id=:id and valid=:valid");
            query.setParameter("id", idProduktNeTreg);
            query.setParameter("valid", Boolean.TRUE);
            produktNeTreg = (ProduktNeTreg)query.getSingleResult();
			tx.begin();
			produktNeTreg.setValid(Boolean.FALSE);
			tx.commit();
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	@Override
	public boolean shtoProduktNeTreg(ProduktNeTreg produktNeTreg) {
		try {
			tx.begin();
			produktNeTreg.setValid(Boolean.TRUE);
			em.persist(produktNeTreg);
			tx.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public List<ProduktNeTreg> tregoProdukteNeTregje() {
		List<ProduktNeTreg> produktNeTreg = new  ArrayList<>();
		try {
			Query query = em.createQuery("Select p from ProduktNeTreg p where valid=:valid");
			query.setParameter("valid", Boolean.TRUE);
			produktNeTreg = query.getResultList();
			return produktNeTreg;
		}catch (Exception e) {
			return null;
		}
	}
}
