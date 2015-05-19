package fr.esiag.isies.pds.dao.bi.finance.staging;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import fr.esiag.isies.pds.model.bi.finance.staging.StagingMedicalAct;
//import fr.esiag.isies.pds.model.healthPath.HealthPath;


public class StagingMedicalActDao {
	@SuppressWarnings("deprecation")
	SessionFactory sessionFactory = new  Configuration().configure("oriel.bi.staging.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;
	
	public StagingMedicalAct getById(int id) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(StagingMedicalAct.class);
		cr.add(Restrictions.eq("id", id));
		StagingMedicalAct hp = (StagingMedicalAct) cr.uniqueResult();		
		session.close();
		return hp;
	}
	public List<StagingMedicalAct> getByTypeAct (int idTypeAct){
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from StagingMedicalAct WHERE idtypeAct.id = :idTypeAct");
		query.setParameter("idTypeAct", idTypeAct);
		List<StagingMedicalAct> lst = (List<StagingMedicalAct>) query.list();
		session.close();
		return lst;
	}
}
