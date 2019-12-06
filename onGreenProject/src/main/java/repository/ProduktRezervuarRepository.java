package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entity.ProduktRezervuar;
import entity.Rezervim;
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
			else {
				for(ProduktRezervuar produkte : produkteTeRezervuara) {
					if(!produkte.getRezervim().isValid() || (produkte.getRezervim().getStatus().getEmri().equals("derguar")
							 && produkte.getRezervim().getStatus().getEmri().equals("pranuar"))){
						return true;
					}
				}
			return false;
			}
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

	@Override
	public boolean rezervoProdukt(ProduktRezervuar produktRezervuar) {
		try {
			tx.begin();
			produktRezervuar.setValid(Boolean.TRUE);
			em.persist(produktRezervuar);
			tx.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean ndryshoProduktNeRezervim(ProduktRezervuar produktRezervuar) {
		try {
			tx.begin();
			produktRezervuar.setValid(Boolean.TRUE);
			em.merge(produktRezervuar);
			tx.commit();
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
    @Override
    public boolean fshiProdukt(ProduktRezervuar produkt) {
    	try {
    		tx.begin();
    		produkt.setValid(Boolean.FALSE);
    		em.merge(produkt);
    		tx.commit();
    		return true;
    	}catch (Exception e) {
			return false;
		}
    }
    
    @Override
    public int tregoSasiRezervuar(ProduktNeTreg produkt) {
    	try {
			List<ProduktRezervuar> list = new ArrayList<>();
			Query query = em.createQuery("Select p from ProduktRezervuar p where id_produktnetreg=:id and valid=:valid");
			query.setParameter("id", produkt.getId());
			query.setParameter("valid", Boolean.TRUE);
			list = query.getResultList();
			int sasia = 0;
			for(ProduktRezervuar produktet : list){
				if(produktet.getRezervim().getStatus().getEmri().equals("perfunduar"))
				sasia += produktet.getSasia();
			}
			return sasia;
		}catch (Exception e) {
			return 0;
		}
    }
}
