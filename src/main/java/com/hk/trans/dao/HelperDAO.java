package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Helper;

public class HelperDAO {
	private static final Logger logger = LoggerFactory.getLogger(HelperDAO.class);
	private Connection con = null;

	public HelperDAO(Connection connection) {
		try {
			con = connection;
		} // try
		catch (Exception e) {
			logger.error("Exception : ", e);
		}
	}// driverdao

	public String addHelper(Helper hf) throws Exception {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select max(HELPER_ID) from help_details");
			int max = 0;
			if (rs.next()) {
				max = rs.getInt(1);
			}

			PreparedStatement ps = con
					.prepareStatement("insert into help_details values(" + (max + 1) + ",?,?,?,?,?,?,?)");

			ps.setString(1, hf.getHelpername());
			ps.setString(2, hf.getDob());
			ps.setString(3, hf.getDoj());
			ps.setFloat(4, hf.getSalary());
			ps.setString(5, hf.getGender());
			ps.setString(6, hf.getLocname());
			ps.setString(7, hf.getAddress());
			int i = ps.executeUpdate();
			if (i == 1)
				return "true";
			return "false";
		} // try
		catch (Exception e) {
			logger.error("Exception : ", e);
			return "false";
		} // catch
		finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		} // finally
	}// addDriver

	public String changeHelper(Helper hf) throws Exception {
		try {
			PreparedStatement ps = con.prepareStatement("select * from help_details where helper_id=?");
			ps.setInt(1, hf.getHelperid());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				hf.setHelpername(rs.getString("helper_name"));
				hf.setDob(rs.getString("dob"));
				hf.setDoj(rs.getString("doj"));
				hf.setSalary(rs.getFloat("salary"));
				hf.setGender(rs.getString("gender"));
				hf.setLocname(rs.getString("loct_name"));
				hf.setAddress(rs.getString("address"));
				return "true";
			} else {
				return "false";
			} // else
		} catch (Exception e) {
			logger.error("Exception : ", e);
			return "false";
		} finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		} // finally
	}// editdriver

	public String updateHelper(Helper hf) throws Exception {
		try {
			int i = hf.getHelperid();
			PreparedStatement ps = con.prepareStatement(
					"update help_details set helper_name=?,dob=?,doj=?,salary=?,gender=?,loct_name=?,address=? where helper_id="
							+ i);
			ps.setString(1, hf.getHelpername());
			ps.setString(2, hf.getDob());
			ps.setString(3, hf.getDoj());
			ps.setFloat(4, hf.getSalary());
			ps.setString(5, hf.getGender());
			ps.setString(6, hf.getLocname());
			ps.setString(7, hf.getAddress());
			System.out.println("in updateAction");
			int j = ps.executeUpdate();
			if (j == 1)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "fail";
	}//

	public String deleteHelper(Helper hf) throws Exception {
		try {
			int n = hf.getHelperid();
			System.out.println("entered record id=" + n);
			Statement st = con.createStatement();
			int i = st.executeUpdate("delete from help_details where helper_id=" + n);
			System.out.println("no.of records deleted:" + i);
			if (i != 0)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "false";
	}// deleteLocation

	public List<Helper> myCollection(Helper hf) throws Exception {
		List<Helper> v = new ArrayList<Helper>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select helper_id,helper_name,dob,doj,salary,gender,loct_name,address  from help_details where loct_name='"
							+ hf.getLocname() + "'");
			while (rs.next()) {
				System.out.println("--mycollection rs.next()---");
				Helper hdto = new Helper();
				hdto.setHelperid(rs.getInt(1));
				hdto.setHelpername(rs.getString(2));
				hdto.setDob(rs.getString(3));
				hdto.setDoj(rs.getString(4));
				hdto.setSalary(rs.getFloat(5));
				hdto.setGender(rs.getString(6));
				hdto.setLocname(rs.getString(7));
				hdto.setAddress(rs.getString(8));
				v.add(hdto);
			}

		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return v;
	}

	public boolean viewTabHelper(Helper hf) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(
				"select helper_id,helper_name,dob,doj,salary,gender,loct_name,address from help_details where loct_name='"
						+ hf.getLocname() + "'");
		while (rs.next()) {
			hf.setLocname(rs.getString(7));
		} // while
		return false;
	}//

	public List<Helper> getAllHelpers() throws Exception {
		List<Helper> helperList = new ArrayList<Helper>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from help_details");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getString(4) + "\t");
			System.out.print(rs.getInt(5) + "\t");
			System.out.print(rs.getString(6) + "\t");
			System.out.print(rs.getString(7) + "\t");
			System.out.print(rs.getString(8));

			Helper hdto = new Helper();
			hdto.setHelperid(rs.getInt(1));
			hdto.setHelpername(rs.getString(2));
			hdto.setDob(rs.getString(3));
			hdto.setDoj(rs.getString(4));
			hdto.setSalary(rs.getInt(5));
			hdto.setGender(rs.getString(6));
			hdto.setLocname(rs.getString(7));
			hdto.setAddress(rs.getString(8));
			helperList.add(hdto);
		}
		return helperList;
	}

	public List<Helper> viewhelperlist(Helper hf) throws Exception {
		List<Helper> v = new ArrayList<Helper>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select *from help_details");
		while (rs.next()) {
			Helper hdto = new Helper();
			hdto.setHelperid(rs.getInt(1));
			hdto.setHelpername(rs.getString(2));
			hdto.setDob(rs.getString(3));
			hdto.setDoj(rs.getString(4));
			hdto.setSalary(rs.getInt(5));
			hdto.setGender(rs.getString(6));
			hdto.setLocname(rs.getString(7));
			hdto.setAddress(rs.getString(8));
			v.add(hdto);
			System.out.println(v.size());
		}
		return v;
	}

	public List<Helper> getHIDS() throws Exception {
		List<Helper> al = new ArrayList<Helper>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select helper_id from help_details");
			while (rs.next()) {
				Helper hf = new Helper();
				hf.setHelperid(rs.getInt("helper_id"));
				al.add(hf);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

	public List<Helper> getLocnames() throws Exception {
		List<Helper> al = new ArrayList<Helper>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select distinct(loct_name) from help_details");
			while (rs.next()) {
				Helper hf = new Helper();
				hf.setLocname(rs.getString("loct_name"));
				al.add(hf);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

}// class
