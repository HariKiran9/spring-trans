package com.hk.trans.model;

public class AllotVeh {

	private int routeid;
	private String dname;
	private int vehicleid;

	public AllotVeh() {
	}

	/**
	 * Returns the routeid.
	 * 
	 * @return int
	 */
	public int getRouteid() {
		return routeid;
	}

	/**
	 * Set the routeid.
	 * 
	 * @param routeid The routeid to set
	 */
	public void setRouteid(int routeid) {
		this.routeid = routeid;
	}

	/**
	 * Returns the dname.
	 * 
	 * @return String
	 */
	public String getDname() {
		return dname;
	}

	/**
	 * Set the dname.
	 * 
	 * @param dname The dname to set
	 */
	public void setDname(String dname) {
		this.dname = dname;
	}

	/**
	 * Returns the vid.
	 * 
	 * @return int
	 */
	public int getVehicleid() {
		return vehicleid;
	}

	/**
	 * Set the vid.
	 * 
	 * @param vid The vid to set
	 */
	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}

}
