package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entity.Roli;
import entity.User;
import util.HibernateUtil;

public class UserRepository implements UserRepositoryInterface{

	private static EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private static EntityTransaction tx = em.getTransaction();
	
	@Override
	public boolean verifyLogin(String username, String fjalekalimi) {
		try {
			Query query = em.createQuery(
					"Select u from User u where u.username=:us and " + "u.fjalekalimi=:pass and" + 
			" valid=:valid");
			query.setParameter("us", username);
			query.setParameter("pass", fjalekalimi);
			query.setParameter("valid", Boolean.TRUE);
			User user = (User) query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public User getUserByUsername(String username) {

		try {
			Query query = em.createQuery("Select u from User u where username=:user and "
					+ "valid=:valid");
			query.setParameter("user", username);
			query.setParameter("valid", Boolean.TRUE);
			User user = (User) query.getSingleResult();
			return user;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	@Override
	public boolean addUser(User user) {
		try {
			tx.begin();
			Query query = em.createQuery("Select r from Roli r where emri=:e");
			query.setParameter("e","klient");
			Roli roli = (Roli)query.getSingleResult();
			user.setValid(Boolean.TRUE);
			user.setRole(roli);
			em.persist(user);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean verifyUsername(String username) {
		try {
			Query query = em.createQuery("Select u from User u where u.username=:user");
			query.setParameter("user", username);
			User user = (User) query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public boolean verifyEmail(String email) {
		try {
			Query query = em.createQuery("Select u from User u where u.email=:email");
			query.setParameter("email", email);
			User user = (User) query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
}
