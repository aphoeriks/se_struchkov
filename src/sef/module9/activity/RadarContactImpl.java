package sef.module9.activity;

/**
 * Implementation of a RadarContact
 * 
 * @author John Doe
 *
 */
public class RadarContactImpl implements RadarContact {

	
	/**
	 * Creates a RadarContact with the specified ID, bearing and distance.  
	 * 
	 * @param contactID the contact's ID
	 * @param bearing the contact's bearing
	 * @param distance the contact's distance
	 */
	String contacID;
	double bearing;
	double distance;
	public RadarContactImpl(String contactID, double bearing, double distance){
		this.setContactID(contactID);
		this.setBearing(bearing);
		this.setDistance(distance);
		
	}
	

	/* (non-Javadoc)
	 * @see sef.module8.activity.RadarContact#getBearing()
	 */
	public final double getBearing() {
		return this.bearing;
	}
	

	/* (non-Javadoc)
	 * @see sef.module8.activity.RadarContact#setBearing(double)
	 */
	public final void setBearing(double bearing) {
		this.bearing = (360+bearing%360)%360;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.RadarContact#getDistance()
	 */
	public final double getDistance() {
		return this.distance;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.RadarContact#setDistance(double)
	 */
	public final void setDistance(double distance) {
		if(distance < 0){
			this.distance = 0;
		}else {
			this.distance = distance;
		}
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.RadarContact#getContactID()
	 */
	public final String getContactID() {
		return this.contacID;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.RadarContact#setContactID(java.lang.String)
	 */
	public final void setContactID(String contactID) {
		this.contacID = contactID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.contacID;
	}
}
