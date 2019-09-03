package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Registration;

public class RegistrationDAO {
	private static final Logger logger = LoggerFactory.getLogger(LocationDAO.class);
	private Connection con = null;

	public RegistrationDAO(Connection connection) {
		try {
//			con = ds.getConnection();
			con = connection;
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
	}

	public boolean insertReg(Registration erf) throws Exception {
		Statement st = con.createStatement();

		int i = st.executeUpdate("insert into reg_details values('" + erf.getName() + "','" + erf.getPassword() + "','"
				+ erf.getPredesg() + "','" + erf.getMailid() + "','" + erf.getPhno() + "','" + erf.getQual() + "','"
				+ erf.getExp() + "','" + erf.getPreinst() + "'," + erf.getPresal() + ",'" + erf.getExptsal() + "','"
				+ erf.getAddr() + "')");
		int m = st
				.executeUpdate("insert into user_details values('" + erf.getName() + "','" + erf.getPassword() + "')");

		System.out.println("record inserted successfully : " + m);
		if (i == 1) {
			PreparedStatement ps = con.prepareStatement("insert into emp_details values(?,?,?,?,?,?,?,?)");
			ps.setString(1, erf.getName());
			ps.setString(2, "employee");
			ps.setString(3, erf.getMailid());
			ps.setInt(4, erf.getPhno());
			ps.setString(5, erf.getQual());
			ps.setInt(6, erf.getExp());
			ps.setInt(7, erf.getExptsal());
			ps.setString(8, erf.getAddr());
			int j = ps.executeUpdate();
			if (j == 1) {
				st = con.createStatement();
				int k = st.executeUpdate("delete from pending_details where name='" + erf.getName() + "'");
				if (k > 0) {
					System.out.println("---record inserted successfully into reg_details--");
				}
			}
			return true;
		}
		return false;
	}

	public boolean insertPend(Registration erf) throws Exception {
		Statement st = con.createStatement();
		int i = st.executeUpdate("insert into pending_details values('" + erf.getName() + "','" + erf.getPassword()
				+ "','" + erf.getPredesg() + "','" + erf.getMailid() + "','" + erf.getPhno() + "','" + erf.getQual()
				+ "','" + erf.getExp() + "','" + erf.getPreinst() + "'," + erf.getPresal() + ",'" + erf.getExptsal()
				+ "','" + erf.getAddr() + "')");
		System.out.println("record inserted successfully");
		if (i == 1) {
			return true;
		}
		return false;
	}

	public boolean blockEmployee(Registration erf) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from reg_details where name='" + erf.getName() + "'");
		while (rs.next()) {
			erf.setName(rs.getString(1));
			erf.setPassword(rs.getString(2));
			erf.setPredesg(rs.getString(3));
			erf.setMailid(rs.getString(4));
			erf.setPhno(rs.getInt(5));
			erf.setQual(rs.getString(6));
			erf.setExp(rs.getInt(7));
			erf.setPreinst(rs.getString(8));
			erf.setPresal(rs.getInt(9));
			erf.setExptsal(rs.getInt(10));
			erf.setAddr(rs.getString(11));
		}
		st = con.createStatement();
		int i = st.executeUpdate("delete from reg_details where name='" + erf.getName() + "'");
		System.out.println("--after delete query--");
		if (i == 1) {
			System.out.println("--Employee blocked successfully---");
			PreparedStatement ps = con.prepareStatement("insert into block_details values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, erf.getName());
			ps.setString(2, erf.getPassword());
			ps.setString(3, erf.getPredesg());
			ps.setString(4, erf.getMailid());
			ps.setInt(5, erf.getPhno());
			ps.setString(6, erf.getQual());
			ps.setInt(7, erf.getExp());
			ps.setString(8, erf.getPreinst());
			ps.setInt(9, erf.getPresal());
			ps.setInt(10, erf.getExptsal());
			ps.setString(11, erf.getAddr());
			int j = ps.executeUpdate();
			if (j == 1) {
				System.out.println("---record successfully inserted into empblockdetails---");
				return true;
			}
		}
		return false;
	}

	public boolean unblockEmployee(String name) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from block_details where name='" + name + "'");
		System.out.println(rs + "---in the unblockemployee method----");
		while (rs.next()) {
			Registration erf = new Registration();
			erf.setName(rs.getString(1));
			erf.setPassword(rs.getString(2));
			erf.setPredesg(rs.getString(3));
			erf.setMailid(rs.getString(4));
			erf.setPhno(rs.getInt(5));
			erf.setQual(rs.getString(6));
			erf.setExp(rs.getInt(7));
			erf.setPreinst(rs.getString(8));
			erf.setPresal(rs.getInt(9));
			erf.setExptsal(rs.getInt(10));
			erf.setAddr(rs.getString(11));

			PreparedStatement ps = con.prepareStatement("insert into reg_details values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, erf.getName());
			ps.setString(2, erf.getPassword());
			ps.setString(3, erf.getPredesg());
			ps.setString(4, erf.getMailid());
			ps.setInt(5, erf.getPhno());
			ps.setString(6, erf.getQual());
			ps.setInt(7, erf.getExp());
			ps.setString(8, erf.getPreinst());
			ps.setInt(9, erf.getPresal());
			ps.setInt(10, erf.getExptsal());
			ps.setString(11, erf.getAddr());
			int i = ps.executeUpdate();
			if (i == 1) {
				st = con.createStatement();
				int k = st.executeUpdate("delete from block_details where name='" + name + "'");
				if (k > 0) {
					System.out.println("---record deleted from block details------");
					System.out.println("---record inserted successfully into reg_details--");
				}
			}
			return true;
		}
		return false;
	}

	public List<Registration> viewpendingemployees(Registration erf) throws Exception {
		List<Registration> v = new ArrayList<Registration>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from pending_details");
		while (rs.next()) {
			Registration empdto = new Registration();
			empdto.setName(rs.getString(1));
			empdto.setPassword(rs.getString(2));
			empdto.setPredesg(rs.getString(3));
			empdto.setMailid(rs.getString(4));
			empdto.setPhno(rs.getInt(5));
			empdto.setQual(rs.getString(6));
			empdto.setExp(rs.getInt(7));
			empdto.setPreinst(rs.getString(8));
			empdto.setPresal(rs.getInt(9));
			empdto.setExptsal(rs.getInt(10));
			empdto.setAddr(rs.getString(11));
			v.add(empdto);
		}
		return v;
	}

	public List<Registration> pendingemployeeslist(Registration erf) throws Exception {
		List<Registration> v = new ArrayList<Registration>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from pending_details");
		while (rs.next()) {
			Registration empdto = new Registration();
			empdto.setName(rs.getString(1));
			empdto.setPassword(rs.getString(2));
			empdto.setPredesg(rs.getString(3));
			empdto.setMailid(rs.getString(4));
			empdto.setPhno(rs.getInt(5));
			empdto.setQual(rs.getString(6));
			empdto.setExp(rs.getInt(7));
			empdto.setPreinst(rs.getString(8));
			empdto.setPresal(rs.getInt(9));
			empdto.setExptsal(rs.getInt(10));
			empdto.setAddr(rs.getString(11));
			v.add(empdto);
		}
		return v;
	}

	public List<Registration> getUsernames() throws Exception {
		List<Registration> al = new ArrayList<Registration>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select user_name from user_details");
			while (rs.next()) {
				Registration rf = new Registration();
				rf.setName(rs.getString("user_name"));
				al.add(rf);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

	public List<Registration> getNames() throws Exception {
		List<Registration> al = new ArrayList<Registration>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select name from Block_details");
			while (rs.next()) {
				Registration rf = new Registration();
				rf.setName(rs.getString("name"));
				al.add(rf);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

}
