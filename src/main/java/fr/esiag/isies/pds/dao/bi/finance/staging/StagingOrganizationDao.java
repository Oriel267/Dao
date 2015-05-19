package fr.esiag.isies.pds.dao.bi.finance.staging;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import fr.esiag.isies.pds.model.bi.finance.staging.StagingOrganization;
import fr.esiag.isies.pds.model.bi.finance.staging.StagingHospital;
import fr.esiag.isies.pds.model.referential.organization.Organization;
/**
 * This class provide persistence methods for Organization entity 
 * @author OSA
 * 
 */
public class StagingOrganizationDao{
	
	@SuppressWarnings("deprecation")
	SessionFactory sessionFactory1 = new  Configuration().configure("oriel.bi.staging.hibernate.cfg.xml").buildSessionFactory();
	Session session = null;
	
	public StagingOrganization getById(int id) {
		session = sessionFactory1.openSession();
		session.beginTransaction();
		StagingOrganization item = (StagingOrganization) session.get(StagingOrganization.class, id);
		item.getServicesSet();
		session.close();
		return item;
	}

	public List<StagingOrganization> getAll() {
		session = sessionFactory1.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(StagingOrganization.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<StagingOrganization> list = (List<StagingOrganization>) criteria.list();
		session.close();
		return list;
	}
	public StagingOrganization findBySiret(String mySiret){
		session = sessionFactory1.openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(Organization.class);
		StagingOrganization myOrganization=null;
		try{
			myOrganization=(StagingOrganization) criteria.add(Restrictions.eq("siret", mySiret)).uniqueResult();
		}catch (HibernateException e) {
			@SuppressWarnings("unchecked")
			List<StagingOrganization> list = (List<StagingOrganization>) criteria.add(Restrictions.eq("siret", mySiret)).list();
			myOrganization = list.get(0);
		}
		session.close();
		return myOrganization;	
	}

	/**
	 * get by type
	 * @return get All organisation which are T Type
	 */
	public <T> List<T> getAllByType(Class<T> clazz){
		session = sessionFactory1.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<T> list  =(List<T>) session.createCriteria(clazz).list();
		session.close();
		return list;
	}
	
	public void create(StagingOrganization actualStagingOrganization) {
		actualStagingOrganization.setCreateDate(new Date());
			session = sessionFactory1.openSession();
			session.beginTransaction();
			session.persist(actualStagingOrganization);
			session.getTransaction().commit();
			session.close();	
	}

	public void createHospital(StagingHospital actualStagingOrganization) {
		actualStagingOrganization.setCreateDate(new Date());
			session = sessionFactory1.openSession();
			session.beginTransaction();
			session.persist(actualStagingOrganization);
			session.getTransaction().commit();
			session.close();	
	}
} 