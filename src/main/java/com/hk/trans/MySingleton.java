/**
 * 
 */
package com.hk.trans;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Hari Kiran
 *
 */
public class MySingleton {

	public static Connection getConnection() {
		System.out.println("---------MySingleton-------");
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trmanagement", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

}
