package fr.esiag.isies.pds.dao.bi.finance.staging;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.esiag.isies.pds.model.bi.finance.staging.StagingServiceType;

public class StagingServiceTypeDao {
	@SuppressWarnings("deprecation")
	SessionFactory sessionFactory1 = new  Configuration().configure("oriel.bi.staging.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;
	
	public StagingServiceType getById(int id) {
		session = sessionFactory1.openSession();
		session.beginTransaction();
		StagingServiceType item = (StagingServiceType) session.get(StagingServiceType.class, id);
		session.close();
		return item;
	}

}
