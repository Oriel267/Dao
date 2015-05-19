package fr.esiag.isies.pds.dao;

import java.util.List;

import org.hibernate.Session;

import fr.esiag.isies.pds.model.AbstractType;
import fr.esiag.isies.pds.utils.HibernateUtil;

public abstract class AbstractTypeDao<T extends AbstractType> implements
		IDao<T> {

	public T create(T item) {
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
