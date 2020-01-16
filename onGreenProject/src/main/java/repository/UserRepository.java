package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.Rezervim;
import entity.Roli;
import entity.Tregu;
import entity.User;
import util.HibernateUtil;

public class UserRepository implements UserRepositoryInterface {

	private static EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private static EntityTransaction tx = em.getTransaction();

	@Override
	public boolean verifyLogin(String username, String fjalekalimi) {
		try {
			Query query = em.createQuery(
					"Select u from User u where u.username=:us and " + "u.fjalekalimi=:pass and" + " valid=:valid");
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
			Query query = em.createQuery("Select u from User u where username=:user and " + "valid=:valid");
			query.setParameter("user", username);
			query.setParameter("valid", Boolean.TRUE);
			User user = (User) query.getSingleResult();
			return user;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public User getUserById(int idUser) {
		try {
			Query query = em.createQuery("Select u from User u where iduseri=:user and " + "valid=:valid");
			query.setParameter("user", idUser);
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
			query.setParameter("e", "klient");
			Roli roli = (Roli) query.getSingleResult();
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

	@Override
	public boolean verifyUsernameToEdit(String username, int id) {
		try {
			Query query = em.createQuery("Select u from User u where u.username=:user ");
			query.setParameter("user", username);
			User user = (User) query.getSingleResult();
			if (user.getIduseri() == id)
				return false;

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
	
	@Override
	public boolean verifyEmailToEdit(String email, int id) {
		try {
			Query query = em.createQuery("Select u from User u where u.email=:email");
			query.setParameter("email", email);
			User user = (User) query.getSingleResult();
			if(user.getIduseri() == id)
				return false;
			
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}

	@Override
	public List<User> tregoShitesit(int idTregu) {
		List<User> employees = new ArrayList<>();

		try {
			Query query = em.createQuery("Select r from Roli r where emri=:e");
			query.setParameter("e", "shites");
			Roli roli = (Roli) query.getSingleResult();

			TypedQuery<User> employeeQuery = em.createQuery(
					"Select u from User u where valid=:valid and roli=:idrole and id_tregu=:id", User.class);
			employeeQuery.setParameter("valid", Boolean.TRUE);
			employeeQuery.setParameter("idrole", roli.getIdroli());
			employeeQuery.setParameter("id", idTregu);
			employees = employeeQuery.getResultList();
			return employees;
		} catch (Exception e) {
			System.out.print(e);
			return null;
		}
	}

	@Override
	public List<User> tregoTeGjitheShitesit() {
		List<User> employees = new ArrayList<>();

		try {
			Query query = em.createQuery("Select r from Roli r where emri=:e");
			query.setParameter("e", "shites");
			Roli roli = (Roli) query.getSingleResult();

			TypedQuery<User> employeeQuery = em.createQuery(
					"Select u from User u where valid=:valid and roli=:idrole", User.class);
			employeeQuery.setParameter("valid", Boolean.TRUE);
			employeeQuery.setParameter("idrole", roli.getIdroli());
			employees = employeeQuery.getResultList();
			return employees;
		} catch (Exception e) {
			System.out.print(e);
			return null;
		}
	}

	@Override
	public boolean krijoShites(User shites, int idTregu) {
		Roli role = new Roli();
		Tregu tregu = new Tregu();
		try {
			Query query = em.createQuery("Select r from Roli r where emri=:e");
			query.setParameter("e", "shites");
			role = (Roli) query.getSingleResult();
			tx.begin();
			shites.setValid(Boolean.TRUE);
			tregu.setIdtregu(idTregu);
			shites.setRole(role);
			shites.setTregu(tregu);
			em.persist(shites);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean editUser(User user) {
		try {
			tx.begin();
			em.merge(user);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean fshiUser(User user) {
		try {
			tx.begin();
			user.setValid(Boolean.FALSE);
			em.merge(user);
			tx.commit();
			return true;

		} catch (Exception e) {
			return false;
		}
	}
}
