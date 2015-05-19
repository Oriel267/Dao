package fr.esiag.isies.pds.dao.referential.organization;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.esiag.isies.pds.dao.AbstractEntityDao;
import fr.esiag.isies.pds.model.referential.organization.Organization;
import fr.esiag.isies.pds.model.referential.organization.Service;
import fr.esiag.isies.pds.utils.HibernateUtil;

public class ServiceDao extends AbstractEntityDao<Service>{

	@Override
	public Service getById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Service srv=  (Service) session.get(Service.class, id);
		session.close();
		return srv;
	}

	@Override
	public List<Service> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Service> list = (List<Service>) session.createCriteria(Service.class).list();
		session.close();
		return list;
	}
	
	public Service addServices(Service service){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Organization orga = (Organization)new OrganizationDao().getById(service.getIdOrganizaton());
		orga.setServicesSet(service.getServices());
		session.update(orga);
		session.close();
		return service;
	}
	
	@SuppressWarnings("unchecked")

	public List<Service> getServicesByOrga(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createQuery("from Service where idOrganization = :id ");
		query.setParameter("id", id);
		List<Service> lst = (List<Service>)query.list();
		session.close();
		return lst;
	}

}
