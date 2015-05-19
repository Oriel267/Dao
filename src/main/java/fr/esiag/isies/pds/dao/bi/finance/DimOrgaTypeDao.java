package fr.esiag.isies.pds.dao.bi.finance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.esiag.isies.pds.model.bi.finance.DimOrganization;
import fr.esiag.isies.pds.model.referential.organization.OrgaType;

public class DimOrgaTypeDao{
	SessionFactory sessionFactory = new  Configuration().configure("oriel.fictifbi.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;
	
	public OrgaType getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrgaType> getAll() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(OrgaType.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<OrgaType> list = (List<OrgaType>) criteria.list();
		session.close();
		return list;
	}

}
