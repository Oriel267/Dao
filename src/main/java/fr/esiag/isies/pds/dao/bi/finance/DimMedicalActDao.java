package fr.esiag.isies.pds.dao.bi.finance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.esiag.isies.pds.model.bi.finance.DimMedicalAct;
import fr.esiag.isies.pds.model.bi.finance.DimOrganization;

public class DimMedicalActDao {
	SessionFactory sessionFactory = new  Configuration().configure("oriel.fictifbi.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;

	
	public DimMedicalAct getById(int id) {
		

		return null;
	}

	
	public List<DimMedicalAct> getAll() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(DimMedicalAct.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<DimMedicalAct> list = (List<DimMedicalAct>) criteria.list();
		session.close();
		return list;
	}
}
