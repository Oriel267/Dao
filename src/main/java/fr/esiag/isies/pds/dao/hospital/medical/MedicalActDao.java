package fr.esiag.isies.pds.dao.hospital.medical;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.esiag.isies.pds.dao.AbstractEntityDao;
import fr.esiag.isies.pds.model.hospital.medical.MedicalAct;
import fr.esiag.isies.pds.utils.HibernateUtil;

public class MedicalActDao extends AbstractEntityDao <MedicalAct> {

		@Override
		public MedicalAct getById(int id) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			MedicalAct mediAct=  (MedicalAct) session.get(MedicalAct.class, id);
			session.close();
			return mediAct;
		}
		/**
		 * use to retrieve ccam act by well known code
		 * @param code
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public List <MedicalAct> getByCode(String code) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List <MedicalAct> lst =(List<MedicalAct>) session.createCriteria(MedicalAct.class)
								.add(Restrictions.eq("codeAct", code))
								.list();
			session.close();
			return lst;
		}
			/**
			 * method to get all the act from ccam referential
			 */
		@SuppressWarnings("unchecked")
		@Override
		public List<MedicalAct> getAll() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List<MedicalAct> lst = (List<MedicalAct>) session.createCriteria(MedicalAct.class).list();
			session.close();
			return lst;
			
		}
		/**
		 * method used to search the primary act 
		 * @param model
		 * @return
		 */
		
		@SuppressWarnings("unchecked")
		public List<MedicalAct> getActFromModel(String model){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List <MedicalAct> lst =(List<MedicalAct>) session.createCriteria(MedicalAct.class)
								.add(Restrictions.eq("isPrincipal", 1))
								.add((Restrictions.like("nameAct", "%"+model+"%")))
								.list();
			session.close();
			return lst;
		}
		
}
