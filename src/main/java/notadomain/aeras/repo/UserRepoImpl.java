package notadomain.aeras.repo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import notadomain.aeras.model.User;
import notadomain.aeras.util.HibernateUtil;

public class UserRepoImpl implements UserRepo {

	@Override
	public void addUserToDatabase(User user) {
		EntityManager em = HibernateUtil.getInstance().getEntityManager(); 
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		EntityManager em = HibernateUtil.getInstance().getEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cr = cb.createQuery(User.class);
		Root<User> root = cr.from(User.class);
		
		TypedQuery<User> query = em.createQuery(cr.where(cb.equal(root.get("username"),username)));
		
		user = query.getResultStream().findFirst().orElse(null);
		
		em.getTransaction().commit();
		em.close();
		return user;
	}

	@Override
	public User getUserById(int id) {
		User user = null;
		EntityManager em = HibernateUtil.getInstance().getEntityManager();
		em.getTransaction().begin();
		
		user = em.find(User.class, id);
		
		em.getTransaction().commit();
		return user;
	}
	
	@Override
	public void updateUser(User user) {
		EntityManager em = HibernateUtil.getInstance().getEntityManager();
		em.getTransaction().begin();
		
		em.merge(user);
		
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteUser(User user) {
		EntityManager em = HibernateUtil.getInstance().getEntityManager();
		em.getTransaction().begin();
		
		em.remove(user);
		
		em.getTransaction().commit();
		em.close();
	}


}
