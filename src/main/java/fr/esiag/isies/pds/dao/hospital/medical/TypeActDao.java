package fr.esiag.isies.pds.dao.hospital.medical;

import java.util.List;

import org.hibernate.Session;

import fr.esiag.isies.pds.dao.AbstractTypeDao;
import fr.esiag.isies.pds.model.hospital.medical.TypeAct;
import fr.esiag.isies.pds.utils.HibernateUtil;

public class TypeActDao extends AbstractTypeDao<TypeAct> {

	@Override
	public TypeAct getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TypeAct> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<TypeAct> lst = (List<TypeAct>) session.createCriteria(TypeAct.class).list();
		session.close();
		return lst;
		
	}

}
