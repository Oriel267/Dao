package fr.esiag.isies.pds.dao.bi.finance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.esiag.isies.pds.dao.AbstractEntityDao;
import fr.esiag.isies.pds.model.bi.finance.DimOrganization;
import fr.esiag.isies.pds.model.bi.finance.DimServiceType;

public class DimServiceTypeDao{
	SessionFactory sessionFactory = new  Configuration().configure("oriel.fictifbi.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;

	public DimServiceType getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<DimServiceType> getAll() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(DimServiceType.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<DimServiceType> list = (List<DimServiceType>) criteria.list();
		session.close();
		return list;
	}

}
