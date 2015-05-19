package fr.esiag.isies.pds.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import fr.esiag.isies.pds.model.AbstractEntity;
import fr.esiag.isies.pds.utils.HibernateUtil;

/**
 * Generic Dao which permit to do general actions whith database
 * 
 * @author JLA & PFR
 * @param <T extends AbstractEntity> Generic Class
 */
public abstract class AbstractEntityDao<T extends AbstractEntity> implements
		IDao<T> {

	/**
	 * Set the createDate at the date of today before inserted in database
	 */
	public T create(T item) {
		item.setCreateDate(new Date());
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(item);
		session.getTransaction().commit();
		session.close();
		return item;
	}

	
	public abstract T getById(int id);
	
	public abstract List<T> getAll();

	public T deleteById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		T item = getById(id);
		session.delete(item);
		session.getTransaction().commit();
		session.close();
		return item;
	}
}
