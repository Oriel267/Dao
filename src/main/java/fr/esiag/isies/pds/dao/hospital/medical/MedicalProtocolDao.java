package fr.esiag.isies.pds.dao.hospital.medical;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.esiag.isies.pds.dao.AbstractEntityDao;
import fr.esiag.isies.pds.model.hospital.medical.MedicalAct;
import fr.esiag.isies.pds.model.hospital.medical.MedicalProtocol;
import fr.esiag.isies.pds.utils.HibernateUtil;
/**
 * Dao for Medical protocol
 * @author Glawdys
 *
 */
public class MedicalProtocolDao extends AbstractEntityDao <MedicalProtocol> {

	@Override
	public MedicalProtocol getById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		MedicalProtocol mediProto=  (MedicalProtocol) session.get(MedicalProtocol.class, id);
		session.close();
		return mediProto;
	}
	/**
	 * use to retrieve a protocol by its normalized code
	 * @param code
	 * @return
	 */
	public MedicalProtocol getByCode(String code) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		MedicalProtocol mediProto=  (MedicalProtocol) session.get(MedicalProtocol.class, code);
		session.close();
		return mediProto;
	}
	
	/**
	 * 	Get all protocols
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MedicalProtocol> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<MedicalProtocol> lst = (List<MedicalProtocol>) session.createCriteria(MedicalProtocol.class).list();
		session.close();
		return lst;
		
	}
	/**
	 * method uses to search all subAct linked to a principal act
	 * @param idPrincipalMedicalAct
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<MedicalProtocol> getSubAct(int idPrincipalMedicalAct){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		/*Criteria criteria = session.createCriteria(MedicalProtocol.class).add(Restrictions.eq("idPrincipalMedicalAct",idPrincipalMedicalAct));
		List<MedicalProtocol> lst = criteria.list();*/
		Query query = session
					.createQuery("from MedicalProtocol where idPrincipalMedicalAct = :idPrincipalMedicalAct ");
		query.setParameter("idPrincipalMedicalAct", idPrincipalMedicalAct);
		List<MedicalProtocol> lst = (List<MedicalProtocol>) query.list();
		session.close();
		return lst;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<MedicalProtocol> getSubAct(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List <MedicalProtocol> lst =(List<MedicalProtocol>) session.createCriteria(MedicalProtocol.class)
							.add(Restrictions.eq("idPrincipalMedicalAct", 213))
							.list();
		session.close();
		return lst;
	}*/
}

