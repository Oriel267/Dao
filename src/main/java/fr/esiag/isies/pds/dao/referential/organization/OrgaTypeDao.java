package fr.esiag.isies.pds.dao.referential.organization;

import java.util.List;

import org.hibernate.Session;

import fr.esiag.isies.pds.dao.AbstractTypeDao;
import fr.esiag.isies.pds.model.referential.organization.OrgaType;
import fr.esiag.isies.pds.utils.HibernateUtil;
/**
 * This class provide persistence methods for Organization Type entity 
 * @author JLA SKO OSA ADA JSB
 * 
 */
public class OrgaTypeDao extends AbstractTypeDao<OrgaType> {

	@Override
	public OrgaType getById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
        OrgaType orgT=  (OrgaType) session.get(OrgaType.class, id);
		session.close();
		return orgT;
	}

	
	@Override
	public List<OrgaType> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<OrgaType> list = (List<OrgaType>) session.createCriteria(OrgaType.class).list();
		session.close();
		return list;
	}
	
	
	

}
