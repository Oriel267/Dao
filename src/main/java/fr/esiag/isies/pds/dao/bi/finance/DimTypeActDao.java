package fr.esiag.isies.pds.dao.bi.finance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.esiag.isies.pds.model.bi.finance.DimOrganization;
import fr.esiag.isies.pds.model.hospital.medical.TypeAct;
import fr.esiag.isies.pds.utils.HibernateUtil;

public class DimTypeActDao {
	SessionFactory sessionFactory = new  Configuration().configure("oriel.fictifbi.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;
	
	public TypeAct getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public List<TypeAct> getAll() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(TypeAct.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<TypeAct> list = (List<TypeAct>) criteria.list();
		session.close();
		return list;
		
	}

}
