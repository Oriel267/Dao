package fr.esiag.isies.pds.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import fr.esiag.isies.pds.model.AbstractModel;

/**
 * Utils class permit to create, update, delete and read a cache file. This file
 * is used when database is out of service.
 * 
 * @author PFR
 *
 */
public class CacheFileUtils {

	// TODO ActionEnum

	/**
	 * Name of directory cache file (intern at the DAO Project)
	 */
	private static String cacheDirectory = "app-data/cacheFile/";

	/**
	 * Static class
	 */
	private CacheFileUtils() {
	}

	/**
	 * Add one cache file for the element (item).
	 * 
	 * @param item
	 * @return true if it's OK.
	 */
	public static <T extends AbstractModel> boolean add(T item,
			ActionEnum action) {

		if (item == null) {
			return false;
		}

		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) item.getClass();

		File f = new File(createName(clazz.getSimpleName(), item.getId(),
				action));
		// create a cache file
		if (!createCacheFile(f)) {
			return false;
		}

		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(clazz);
			JAXBElement<T> je = new JAXBElement<T>(new QName(
					clazz.getSimpleName()), clazz, item);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			PrintStream printStream = new PrintStream(f);
			marshaller.marshal(je, printStream);
			printStream.flush();
			printStream.close();
			return true;
		} catch (JAXBException e) {
			// TODO LOGGER
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			// TODO LOGGER
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Create cache File if it doesn't exist.
	 * 
	 * @param cacheFile
	 * @return true if it's OK and false otherwise
	 */
	private static boolean createCacheFile(File cacheFile) {
		try {
			File rep = new File(cacheFile.getParent());
			if (!rep.exists()) {
				rep.mkdirs();
			}
			return cacheFile.createNewFile();
		} catch (IOException e) {
			// TODO LOGGER
			return false;
		}
	}

	/**
	 * Delete all cache files
	 * 
	 * @return true if all files are deleted and false otherwise
	 */
	public static boolean deleteAllCacheFile() {
		File rep = new File(cacheDirectory);
		File[] files = rep.listFiles();
		Boolean test = true;
		for (File f : files) {
			if (f.isDirectory()) {
				for (File f2 : f.listFiles()) {
					if (!f2.delete()) {
						test = false;
					}
				}
			}
			if (!f.delete()) {
				test = false;
			}
		}
		return test;
	}

	/**
	 * Read all "T" object which are in these cache files
	 * 
	 * @param clazz
	 * @return List of T Object
	 * @throws JAXBException
	 */
	public static <T extends AbstractModel> List<T> read(Class<T> clazz) {
		JAXBContext jc;
		String path = cacheDirectory + clazz.getSimpleName() + "/";
		File directory = new File(path);
		if (!(directory.exists() && directory.isDirectory())) {
			return null;
		}
		try {
			List<T> lst = new ArrayList<T>();
			jc = JAXBContext.newInstance(clazz);

			for (File f : directory.listFiles()) {
				if (f.isFile()) {
					StreamSource xml = new StreamSource(f);
					Unmarshaller unmarshaller = jc.createUnmarshaller();
					JAXBElement<T> je = unmarshaller.unmarshal(xml, clazz);
					lst.add(je.getValue());
				}
			}
			return lst;
		} catch (JAXBException e) {
			// TODO Logger
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Create name of cache file. The full name of cache file is : Prefix
	 * (cacheDirectory value), id of item which is managed, the timestamp to
	 * date the file, the action and xml extension => (id_timestamp_action.xml)
	 * 
	 * @return name of a cache file
	 */
	private static String createName(String className, Integer id,
			ActionEnum action) {
		String tmp = cacheDirectory + className + "/";
		tmp += id.toString() + "_";
		tmp += new Date().getTime() + "_";
		tmp += action.getLabel();
		tmp += ".xml";
		return tmp;
	}
}
