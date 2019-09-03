/*
 * Created on Feb 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.hk.trans.model;

/**
 * @author Administrator
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Driver {
	/** Driverid property */
	private int driverid;

	/** Gender property */
	private String gender;

	/** Licenceno property */
	private String licenceno;

	/** Qualification property */
	private String qualification;

	/** Address property */
	private String address;

	/** Salary property */
	private float salary;

	/** Doj property */
	private String doj;

	/** Licencetype property */
	private String licencetype;

	/** Experience property */
	private String experience;

	/** Dob property */
	private String dob;

	/** Drivername property */
	private String drivername;
	private String locname;

	// --------------------------------------------------------- Methods

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */

	/**
	 * Returns the Driverid.
	 * 
	 * @return int
	 */
	public int getDriverid() {
		return driverid;
	}

	/**
	 * Set the Driverid.
	 * 
	 * @param Driverid The Driverid to set
	 */
	public void setDriverid(int driverid) {
		this.driverid = driverid;
	}

	/**
	 * Returns the Gender.
	 * 
	 * @return String
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Set the Gender.
	 * 
	 * @param Gender The Gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns the Licenceno.
	 * 
	 * @return String
	 */
	public String getLicenceno() {
		return licenceno;
	}

	/**
	 * Set the Licenceno.
	 * 
	 * @param Licenceno The Licenceno to set
	 */
	public void setLicenceno(String licenceno) {
		this.licenceno = licenceno;
	}

	/**
	 * Returns the Qualification.
	 * 
	 * @return String
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * Set the Qualification.
	 * 
	 * @param Qualification The Qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * Returns the Address.
	 * 
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the Address.
	 * 
	 * @param Address The Address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns the Salary.
	 * 
	 * @return float
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * Set the Salary.
	 * 
	 * @param Salary The Salary to set
	 */
	public void setSalary(float salary) {
		this.salary = salary;
	}

	/**
	 * Returns the Doj.
	 * 
	 * @return String
	 */
	public String getDoj() {
		return doj;
	}

	/**
	 * Set the Doj.
	 * 
	 * @param Doj The Doj to set
	 */
	public void setDoj(String doj) {
		this.doj = doj;
	}

	/**
	 * Returns the Licencetype.
	 * 
	 * @return String
	 */
	public String getLicencetype() {
		return licencetype;
	}

	/**
	 * Set the Licencetype.
	 * 
	 * @param Licencetype The Licencetype to set
	 */
	public void setLicencetype(String licencetype) {
		this.licencetype = licencetype;
	}

	/**
	 * Returns the Experience.
	 * 
	 * @return String
	 */
	public String getExperience() {
		return experience;
	}

	/**
	 * Set the Experience.
	 * 
	 * @param Experience The Experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}

	/**
	 * Returns the Dob.
	 * 
	 * @return String
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Set the Dob.
	 * 
	 * @param Dob The Dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * Returns the Drivername.
	 * 
	 * @return String
	 */
	public String getDrivername() {
		return drivername;
	}

	/**
	 * Set the Drivername.
	 * 
	 * @param Drivername The Drivername to set
	 */
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public void setLocname(String locname) {
		this.locname = locname;
	}

	public String getLocname() {
		return locname;
	}

}
