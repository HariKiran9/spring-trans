package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Registration;

public class SubAdminDAO {

	private static final Logger logger = LoggerFactory.getLogger(SubAdminDAO.class);
	Connection con = null;

	public SubAdminDAO(DataSource ds) {
		try {
			con = ds.getConnection();
		} catch (Exception e) {
			logger.error(" Exception : ", e);
		}
	}

	public boolean insertAdmin(Registration erf) throws Exception {
		Statement st = con.createStatement();

		int i = st.executeUpdate("insert into sub_admin values('" + erf.getName() + "','" + erf.getPassword() + "','"
				+ erf.getPredesg() + "','" + erf.getMailid() + "','" + erf.getPhno() + "','" + erf.getQual() + "','"
				+ erf.getExp() + "','" + erf.getPreinst() + "'," + erf.getPresal() + ",'" + erf.getExptsal() + "','"
				+ erf.getAddr() + "')");
		logger.info("record inserted successfully");
		PreparedStatement ps = null;
		if (i == 1) {
			ps = con.prepareStatement("insert into emp_details values(?,?,?,?,?,?,?,?)");
			ps.setString(1, erf.getName());
			ps.setString(2, "subadmin");
			ps.setString(3, erf.getMailid());
			ps.setInt(4, erf.getPhno());
			ps.setString(5, erf.getQual());
			ps.setInt(6, erf.getExp());
			ps.setInt(7, erf.getExptsal());
			ps.setString(8, erf.getAddr());
			int j = ps.executeUpdate();
			logger.info("Record Inserted {j}", j);
			return true;
		}
		return false;
	}
}
