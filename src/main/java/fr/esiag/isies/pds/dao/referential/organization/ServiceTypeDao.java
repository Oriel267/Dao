package fr.esiag.isies.pds.dao.referential.organization;

import java.util.List;

import org.hibernate.Session;

import fr.esiag.isies.pds.dao.AbstractTypeDao;
import fr.esiag.isies.pds.model.referential.organization.ServiceType;
import fr.esiag.isies.pds.utils.HibernateUtil;
/**
 * This class provide persistence methods for Organization Type entity 
 * @author JLA SKO OSA ADA JSB
 * 
 */
public class ServiceTypeDao extends AbstractTypeDao<ServiceType>{
	@Override
	public ServiceType getById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ServiceType srvT=  (ServiceType) session.get(ServiceType.class, id);
		session.close();
		return srvT;
	}


	@Override
	public List<ServiceType> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ServiceType> list = (List<ServiceType>) session.createCriteria(ServiceType.class).list();
		session.close();
		return list;
	}

}

