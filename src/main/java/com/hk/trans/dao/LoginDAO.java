package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Login;

public class LoginDAO {

	private static final Logger logger = LoggerFactory.getLogger(LoginDAO.class);
	private Connection con = null;

	public LoginDAO(Connection connection) {
		try {
//			con = connection.getConnection();
			con = connection;
			logger.info("conection established : " + con);
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
	}

	public boolean user(Login lf) throws Exception {
		try {
			Statement st = con.createStatement();
			logger.info(con + "conection established in login method");
			logger.info("[LoginDAO] User Name : " + lf.getUsername());
			logger.info("[LoginDAO] Password : " + lf.getPassword());
			ResultSet rs = st.executeQuery("select * from reg_details where name='" + lf.getUsername()
					+ "' and password='" + lf.getPassword() + "'");
			if (rs.next()) {
				logger.info("***************inexecuteDao**************");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean admin(Login lf) throws Exception {
		try {
			Statement st = con.createStatement();
			logger.info(con + "conection established in login method");
			logger.info("[LoginDAO] Username : " + lf.getUsername());
			logger.info("[LoginDAO] Password : " + lf.getPassword());
			ResultSet rs = st.executeQuery("select * from admin_details where name='" + lf.getUsername()
					+ "' and password='" + lf.getPassword() + "'");
			// System.out.println(rs.next());
			if (rs.next()) {
				logger.info("***************inexecuteDao**************");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String EditPassword(Login lf) throws Exception {
		try {
			logger.info("in login DAO:" + lf.getUsername());
			PreparedStatement pst = con.prepareStatement("select * from reg_details where name=?");
			pst.setString(1, lf.getUsername());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				lf.setUsername(rs.getString("name"));
				lf.setPassword(rs.getString("password"));
				logger.info("in EditAction");
				return "true";
			} else {
				return "false";
			}
		} finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		} // finally
	}

	public String ChangePassword(Login lf) throws Exception {
		int i = 0;
		try {
			PreparedStatement pst = con
					.prepareStatement("update reg_details set name=?,password=? where name='" + lf.getUsername() + "'");
			pst.setString(1, lf.getUsername());
			pst.setString(2, lf.getPassword());
			logger.info("in updateAction");
			i = pst.executeUpdate();
			if (i != 0)
				return "true";
		} catch (Exception e) {
		}
		return "fail";
	}

	public List<Login> getUsernames() throws Exception {
		List<Login> al = new ArrayList<Login>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select name from reg_details");
			while (rs.next()) {
				Login lf = new Login();
				lf.setUsername(rs.getString("name"));
				al.add(lf);
			}
		} catch (Exception e) {
		}
		return al;
	}

}