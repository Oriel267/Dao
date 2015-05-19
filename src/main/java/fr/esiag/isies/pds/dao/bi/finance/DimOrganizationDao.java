package fr.esiag.isies.pds.dao.bi.finance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.esiag.isies.pds.dao.AbstractEntityDao;
import fr.esiag.isies.pds.model.bi.finance.DimOrganization;

public class DimOrganizationDao extends AbstractEntityDao<DimOrganization> {
	SessionFactory sessionFactory = new  Configuration().configure("oriel.fictifbi.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;

	@Override
	public DimOrganization getById(int id) {
		

		return null;
	}

	@Override
	public List<DimOrganization> getAll() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(DimOrganization.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<DimOrganization> list = (List<DimOrganization>) criteria.list();
		session.close();
		return list;
	}

}
