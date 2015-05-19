package fr.esiag.isies.pds.dao.referential.organization;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.esiag.isies.pds.dao.AbstractEntityDao;
import fr.esiag.isies.pds.model.referential.organization.Organization;
import fr.esiag.isies.pds.utils.HibernateUtil;
/**
 * This class provide persistence methods for Organization entity 
 * @author JLA SKO OSA ADA JSB
 * 
 */
public class OrganizationDao extends AbstractEntityDao<Organization> {

	@Override
	public Organization getById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Organization item = (Organization) session.get(Organization.class, id);
		item.getServicesSet();
		session.close();
		return item;
	}

	@Override
	public List<Organization> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(Organization.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<Organization> list = (List<Organization>) criteria.list();
		session.close();
		return list;
	}

	public Organization findBySiret(String mySiret){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria =session.createCriteria(Organization.class);
		Organization myOrganization=null;
		try{
			myOrganization=(Organization) criteria.add(Restrictions.eq("siret", mySiret)).uniqueResult();
		}catch (HibernateException e) {
			@SuppressWarnings("unchecked")
			List<Organization> list = (List<Organization>) criteria.add(Restrictions.eq("siret", mySiret)).list();
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<T> list  =(List<T>) session.createCriteria(clazz).list();
		session.close();
		return list;
	}
}