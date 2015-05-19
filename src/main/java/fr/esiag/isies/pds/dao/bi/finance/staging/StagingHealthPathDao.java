package fr.esiag.isies.pds.dao.bi.finance.staging;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.esiag.isies.pds.model.bi.finance.staging.StagingHealthPath;

public class StagingHealthPathDao{
	SessionFactory sessionFactory = new  Configuration().configure("oriel.bi.staging.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;

	public StagingHealthPath getById(int id) {
		

		return null;
	}

	public List<StagingHealthPath> getAll() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(StagingHealthPath.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<StagingHealthPath> list = (List<StagingHealthPath>) criteria.list();
		session.close();
		return list;
	}
	
	public void create(StagingHealthPath healthPath) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(healthPath);
			session.getTransaction().commit();
			session.close();	
	}
	public void update(StagingHealthPath ea){
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(ea);
		session.getTransaction().commit();
		session.close();
		
		
	}
	public int truncateTable(){
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete from StagingHealthPath");
	    int result =  query.executeUpdate();
	    session.close();
	    return result;
	}

}