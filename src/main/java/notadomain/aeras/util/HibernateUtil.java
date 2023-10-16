package notadomain.aeras.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final HibernateUtil instance = new HibernateUtil();
	private EntityManagerFactory emf;
	
	private HibernateUtil() {
		Configuration conf = new Configuration().configure("/META-INF/hibernate.cfg.xml");
		emf = conf.buildSessionFactory();
	}
	
	public static HibernateUtil getInstance() {
		return instance;
	}
	
	public EntityManager getEntityManager() {
		return this.emf.createEntityManager();
	}
}
