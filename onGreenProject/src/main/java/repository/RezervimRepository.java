package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entity.Rezervim;
import entity.Status;
import entity.User;
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
	
	public List<Rezervim> tregoRezervimeNePritjeKlient(int idKlient){
		List<Rezervim> rezervime = new ArrayList<Rezervim>();
		try {
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","pranuar");
			status = (Status)que.getSingleResult();
			Status stat = new Status();
			Query quer = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","derguar");
			stat = (Status)que.getSingleResult();
			
			Query query = em.createQuery("Select r from Rezervim r where idclient=:idKlient and valid=:valid and "
					+ " idstatus=:idstatus or idstatus=:id");
			query.setParameter("idKlient",idKlient);
			query.setParameter("valid", Boolean.TRUE);
			query.setParameter("idstatus", status.getId());
			query.setParameter("id", stat.getId());
			rezervime = query.getResultList();
			return rezervime;
		}catch (Exception e) {
			System.out.println(e);
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
			rezervim.setMesazhi("Rezervim i perfunduar .");
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
			rezervim.setMesazhi("Rezervimi eshte pranuar.");
			em.merge(rezervim);
			tx.commit();
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	@Override
	public boolean refuzoRezervim(Rezervim rezervim) {
		try {
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","krijuar");
			status = (Status)que.getSingleResult();
			tx.begin();
			rezervim.setStatus(status);
			rezervim.setMesazhi("Rezervimi eshte refuzuar.");
			em.merge(rezervim);
			tx.commit();
			return true;
		}catch (Exception e) {
			System.out.println(e);
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
	
	@Override
	public Rezervim krijoRezervim(int idKlient,int idShites,float cmimi) {
		try {
			Rezervim rezervim = new Rezervim();
			rezervim.setShites(em.find(User.class,idShites));
			rezervim.setUser(em.find(User.class,idKlient));
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","krijuar");
			status = (Status)que.getSingleResult();
			rezervim.setStatus(status);
			rezervim.setValid(Boolean.TRUE);
			rezervim.setCmimiTotal(cmimi);
			tx.begin();
			em.persist(rezervim);
			tx.commit();
			return rezervim;
		}catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Rezervim gjejRezervimNePritje(int idKlient, int idShites) {
		Rezervim rezervim = new Rezervim();
		try {
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","krijuar");
			status = (Status)que.getSingleResult();
			
			Query query = em.createQuery("Select r from Rezervim r where idclient=:idKlient and idShites=:idShites and "
					+ " valid=:valid and idstatus=:idstatus");
			query.setParameter("idKlient",idKlient);
			query.setParameter("idShites", idShites);
			query.setParameter("valid",Boolean.TRUE);
			query.setParameter("idstatus",status.getId());
			rezervim = (Rezervim)query.getSingleResult();
			return rezervim;
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean ndryshoRezervim(Rezervim rezervim) {
		try {
			tx.begin();
			em.persist(rezervim);
			tx.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Rezervim> tregoRezervimeTeKrijuaraKlient(int idKlient) {
		List<Rezervim> rezervime = new ArrayList<Rezervim>();
		try {
			Status status = new Status();
			Query que = em.createQuery("Select s from Status s where emri=:emri");
			que.setParameter("emri","krijuar");
			status = (Status)que.getSingleResult();
			
			Query query = em.createQuery("Select r from Rezervim r where idclient=:idKlient and valid=:valid and "
					+ " idstatus =:idstatus");
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
	public boolean dergoRezervim(Rezervim rezervim) {
		try {			
			tx.begin();
			rezervim.setValid(Boolean.TRUE);
			rezervim.setStatus(em.find(Status.class,1)); // derguar status
			em.merge(rezervim);
			tx.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean fshiRezervim(Rezervim rezervim) {
		try {			
			tx.begin();
			rezervim.setValid(Boolean.FALSE);
			em.merge(rezervim);
			tx.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
