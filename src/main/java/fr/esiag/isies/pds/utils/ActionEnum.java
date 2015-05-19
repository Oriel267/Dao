package fr.esiag.isies.pds.utils;

/**
 * Enum which represent action in database (create, update, delete).
 * @author PFR
 *
 */
public enum ActionEnum {
	CREATE("create"), UPDATE("update"), DELETE("delete");
	
	/**
	 * Label of action
	 */
	private String label;
	
	private ActionEnum(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
