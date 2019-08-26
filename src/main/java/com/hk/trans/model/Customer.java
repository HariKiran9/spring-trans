/**
 * 
 */
package com.hk.trans.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author harikiran
 *
 */
@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	// "customer_seq" is Oracle sequence name.
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
	@SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "CUST_SEQ")
	Long id;

	String name;

	String email;

	@Column(name = "CREATED_DATE")
	Date date;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String name, String email, Date date) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.date = date;
	}

	@Override
	public String toString() {
		String str = "Customer{id:" + id + ", name" + name + ", email:" + email + ", date:" + date + "}";
		return str;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
