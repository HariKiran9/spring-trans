/*
 * Created on Jan 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.hk.trans.model;

/**
 * @author Administrator
 *
 */
public class Bill {

	private int billno;
	private int sid;
	private String fromdate;
	private String todate;
	private String distance;
	private String dueamt;
	private String duedate;
	private String paidamt;

	public Bill() {
	}

	public int getBillno() {
		return billno;
	}

	public void setBillno(int billno) {
		this.billno = billno;
	}

	/**
	 * @return the sid
	 */
	public int getSid() {
		return sid;
	}

	/**
	 * @param sid the sid to set
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}

	/**
	 * @return the fromdate
	 */
	public String getFromdate() {
		return fromdate;
	}

	/**
	 * @param fromdate the fromdate to set
	 */
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	/**
	 * @return the todate
	 */
	public String getTodate() {
		return todate;
	}

	/**
	 * @param todate the todate to set
	 */
	public void setTodate(String todate) {
		this.todate = todate;
	}

	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}

	/**
	 * @return the dueamt
	 */
	public String getDueamt() {
		return dueamt;
	}

	/**
	 * @param dueamt the dueamt to set
	 */
	public void setDueamt(String dueamt) {
		this.dueamt = dueamt;
	}

	/**
	 * @return the duedate
	 */
	public String getDuedate() {
		return duedate;
	}

	/**
	 * @param duedate the duedate to set
	 */
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	/**
	 * @return the paidamt
	 */
	public String getPaidamt() {
		return paidamt;
	}

	/**
	 * @param paidamt the paidamt to set
	 */
	public void setPaidamt(String paidamt) {
		this.paidamt = paidamt;
	}

}// class
