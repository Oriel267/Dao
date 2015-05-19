package fr.esiag.isies.pds.dao.bi.finance.staging;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import fr.esiag.isies.pds.model.bi.finance.staging.StagingMedicalAct;
import fr.esiag.isies.pds.model.bi.finance.staging.StagingMedicalRecord;
import fr.esiag.isies.pds.model.bi.finance.staging.StagingOrganization;

public class StagingMedicalRecordDao {
	@SuppressWarnings("deprecation")
	SessionFactory sessionFactory1 = new  Configuration().configure("oriel.bi.staging.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;
	
	public StagingMedicalRecord getById(int id) {
		session = sessionFactory1.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(StagingMedicalRecord.class);
		cr.add(Restrictions.eq("id", id));
		StagingMedicalRecord hp = (StagingMedicalRecord) cr.uniqueResult();		
		session.close();
		return hp;
	}

	public List<StagingMedicalRecord> getAll() {
		session = sessionFactory1.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(StagingMedicalRecord.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<StagingMedicalRecord> list = (List<StagingMedicalRecord>) criteria.list();
		session.close();
		return list;
	}

}
