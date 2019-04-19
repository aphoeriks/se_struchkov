package sef.module9.activity;

import sef.module8.activity.AccountException;

import java.util.*;

/**
 * Implementation of a Radar 
 * 
 *
 */
public class RadarImpl implements Radar{

	protected HashMap<String, RadarContact> contacts;
	
	/**
	 *  Constructs a new Radar 
	 */
	public RadarImpl(){
		this.contacts = new HashMap<String,RadarContact>();
	}
	
	
	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#addContact(sef.module8.activity.RadarContact)
	 */
	public RadarContact addContact(RadarContact contact) {
		if(contact == null){return null;}
		return this.contacts.put(contact.getContactID(),contact);
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContact(java.lang.String)
	 */
	public RadarContact getContact(String id) {
		return this.contacts.get(id);
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContactCount()
	 */
	public int getContactCount() {
		
		return this.contacts.size();
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#removeContact(java.lang.String)
	 */
	public RadarContact removeContact(String id) {
		return this.contacts.remove(id);
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts()
	 */
	public List<RadarContact> returnContacts() {
		return new ArrayList(contacts.values());
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts(java.util.Comparator)
	 */
	public List<RadarContact> returnContacts(Comparator<RadarContact> comparator) {
		List list = new ArrayList(contacts.values());
		Collections.sort(list, comparator);
		return list;
//		return Collections.sort(arg0, new DistanceComparator());
	}

	
}
