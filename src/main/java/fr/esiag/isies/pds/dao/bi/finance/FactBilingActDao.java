package fr.esiag.isies.pds.dao.bi.finance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.esiag.isies.pds.model.bi.finance.DimOrganization;
import fr.esiag.isies.pds.model.bi.finance.factBilingAct;

public class FactBilingActDao {
	SessionFactory sessionFactory = new  Configuration().configure("oriel.fictifbi.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;


	public factBilingAct getById(int id) {
		

		return null;
	}


	public List<factBilingAct> getAll() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(factBilingAct.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<factBilingAct> list = (List<factBilingAct>) criteria.list();
		session.close();
		return list;
	}
	public int truncateTable(){
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete from factBilingAct");
	    int result =  query.executeUpdate();
	    session.close();
	    return result;
	}
	
	public factBilingAct create(factBilingAct item) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(item);
		session.getTransaction().commit();
		session.close();
		return item;
	}
}
