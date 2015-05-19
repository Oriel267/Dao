package fr.esiag.isies.pds.dao;

import fr.esiag.isies.pds.model.AbstractModel;

public interface IDao<T extends AbstractModel> {

	/**
	 * Persist the item in DataBase
	 * 
	 * @param item
	 * @return item which was inserted
	 */
	public T create(T item);

	/**
	 * Delete an item by the id
	 * @param id
	 * @return item was deleted
	 */
	public T deleteById(int id);

	/**
	 * Get an item by the id
	 * 
	 * @param id
	 * @return the item which was requested
	 */
	public T getById(int id);
}
