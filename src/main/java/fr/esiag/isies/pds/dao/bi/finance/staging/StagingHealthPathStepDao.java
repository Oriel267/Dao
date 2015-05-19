package fr.esiag.isies.pds.dao.bi.finance.staging;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.esiag.isies.pds.dao.IDao;
import fr.esiag.isies.pds.model.bi.finance.staging.StagingHealthPathStep;


public class StagingHealthPathStepDao implements IDao<StagingHealthPathStep>{
	@SuppressWarnings("deprecation")
	SessionFactory sessionFactory = new  Configuration().configure("oriel.bi.staging.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;

	public StagingHealthPathStep getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StagingHealthPathStep> getAll() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(StagingHealthPathStep.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<StagingHealthPathStep> list = (List<StagingHealthPathStep>) criteria.list();
		session.close();
		return list;
	}

	public StagingHealthPathStep create(StagingHealthPathStep item) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();	
		return item;
	}

	public StagingHealthPathStep deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int truncateTable() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete from StagingHealthPathStep");
	    int result =  query.executeUpdate();
	    session.close();	
	    return result;
	}
	

}