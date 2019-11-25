package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entity.Rezervim;
import entity.Status;
import util.HibernateUtil;

public class RezervimRepository implements RezervimRepositoryInterface{

	private static EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private static EntityTransaction tx = em.getTransaction();
	
	@Override
	public List<Rezervim> tregoRezervimePerfunduaraShites(int idShites) {
		List<Rezervim> rezervime = new ArrayList<Rezervim>();
		try {
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","perfunduar");
			status = (Status)que.getSingleResult();
			
			Query query = em.createQuery("Select r from Rezervim r where idShites=:idShites and valid=:valid and "
					+ " idstatus=:idstatus");
			query.setParameter("idShites",idShites);
			query.setParameter("valid", Boolean.TRUE);
			query.setParameter("idstatus", status.getId());
			rezervime = query.getResultList();
			return rezervime;
		}catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Rezervim> tregoRezervimeShites(int idShites) {
		List<Rezervim> rezervime = new ArrayList<Rezervim>();
		try {
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","derguar");
			status = (Status)que.getSingleResult();
			
			Query query = em.createQuery("Select r from Rezervim r where idShites=:idShites and valid=:valid and "
					+ " idstatus=:idstatus");
			query.setParameter("idShites",idShites);
			query.setParameter("valid", Boolean.TRUE);
			query.setParameter("idstatus", status.getId());
			rezervime = query.getResultList();
			return rezervime;
		}catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Rezervim> tregoRezervimeNePritjeShites(int idShites) {
		List<Rezervim> rezervime = new ArrayList<Rezervim>();
		try {
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","pranuar");
			status = (Status)que.getSingleResult();
			
			Query query = em.createQuery("Select r from Rezervim r where idShites=:idShites and valid=:valid and "
					+ " idstatus=:idstatus");
			query.setParameter("idShites",idShites);
			query.setParameter("valid", Boolean.TRUE);
			query.setParameter("idstatus", status.getId());
			rezervime = query.getResultList();
			return rezervime;
		}catch (Exception e) {
			return null;
		}
	}
	
	
	@Override
	public List<Rezervim> tregoRezervimeNePritjeKlient(int idKlient) {
		List<Rezervim> rezervime = new ArrayList<Rezervim>();
		try {
			System.out.println("AAAAAA " + idKlient);
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","perfunduar");
			status = (Status)que.getSingleResult();
			
			Query query = em.createQuery("Select r from Rezervim r where idclient=:idKlient and valid=:valid and "
					+ " idstatus !=:idstatus");
			query.setParameter("idKlient",idKlient);
			query.setParameter("valid", Boolean.TRUE);
			query.setParameter("idstatus", status.getId());
			rezervime = query.getResultList();
			return rezervime;
		}catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean konfirmoRezervim(Rezervim rezervim) {
		try {
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","perfunduar");
			status = (Status)que.getSingleResult();
			tx.begin();
			rezervim.setStatus(status);
			em.merge(rezervim);
			tx.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean pranoRezervim(Rezervim rezervim) {
		try {
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","pranuar");
			status = (Status)que.getSingleResult();
			tx.begin();
			rezervim.setStatus(status);
			em.merge(rezervim);
			tx.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public List<Rezervim> tregoRezervimeKlient(int idKlient) {
		List<Rezervim> rezervime = new ArrayList<Rezervim>();
		try {
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","perfunduar");
			status = (Status)que.getSingleResult();
			
			Query query = em.createQuery("Select r from Rezervim r where idclient=:id and valid=:valid and "
					+ " idstatus=:idstatus");
			query.setParameter("id",idKlient);
			query.setParameter("valid", Boolean.TRUE);
			query.setParameter("idstatus", status.getId());
			rezervime = query.getResultList();
			return rezervime;
		}catch (Exception e) {
			return null;
		}
	}
}
