package fr.esiag.isies.pds.dao.bi.finance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.esiag.isies.pds.model.bi.finance.DimDate;
import fr.esiag.isies.pds.model.bi.finance.DimOrganization;

public class DimDateDao {
	SessionFactory sessionFactory = new  Configuration().configure("oriel.fictifbi.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;

	public DimDate getById(int id) {
		

		return null;
	}

	public List<DimDate> getAll() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(DimDate.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<DimDate> list = (List<DimDate>) criteria.list();
		session.close();
		return list;
	}
}
