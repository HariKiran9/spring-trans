package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Driver;

public class DriverDAO {

	private static final Logger logger = LoggerFactory.getLogger(DriverDAO.class);

	private Connection con = null;

	public DriverDAO(Connection connection) {
		try {
			con = connection;
		} // try
		catch (Exception e) {
		}
	}//

	public String addDriver(Driver df) throws Exception {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(DRIVER_ID) FROM drv_details");
			int max = 0;
			if (rs.next()) {
				max = rs.getInt(1);
			}
			logger.info("Max S_ID : " + max);

			PreparedStatement ps = con
					.prepareStatement("INSERT INTO drv_details VALUES (" + (max + 1) + ",?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, df.getDrivername());
			ps.setString(2, df.getDob());
			ps.setString(3, df.getGender());
			ps.setString(4, df.getQualification());
			ps.setString(5, df.getExperience());
			ps.setString(6, df.getLicenceno());
			ps.setString(7, df.getLicencetype());
			ps.setString(8, df.getDoj());
			ps.setFloat(9, df.getSalary());
			ps.setString(10, df.getLocname());
			ps.setString(11, df.getAddress());
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

	public String changeDriver(Driver df) throws Exception {
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM drv_details WHERE driver_id=?");
			ps.setInt(1, df.getDriverid());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				df.setDrivername(rs.getString("driver_name"));
				df.setDob(rs.getString("dob"));
				df.setGender(rs.getString("gender"));
				df.setQualification(rs.getString("qualification"));
				df.setExperience(rs.getString("experience"));
				df.setLicenceno(rs.getString("licence_no"));
				df.setLicencetype(rs.getString("licence_type"));
				df.setDoj(rs.getString("doj"));
				df.setSalary(rs.getFloat("salary"));
				df.setLocname(rs.getString("loct_name"));
				df.setAddress(rs.getString("address"));
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
	}//

	public String updateDriver(Driver df) throws Exception {
		try {
			int driverId = df.getDriverid();
			logger.info("Driver Id : " + driverId);
			PreparedStatement ps = con.prepareStatement(
					"UPDATE drv_details SET driver_name = ?, dob = ?, gender = ?, qualification = ?, experience = ?, licence_no = ?, licence_type = ?, doj = ?, salary = ?, loct_name = ?, address = ? WHERE driver_id = "
							+ driverId);
			ps.setString(1, df.getDrivername());
			ps.setString(2, df.getDob());
			ps.setString(3, df.getGender());
			ps.setString(4, df.getQualification());
			ps.setString(5, df.getExperience());
			ps.setString(6, df.getLicenceno());
			ps.setString(7, df.getLicencetype());
			ps.setString(8, df.getDoj());
			ps.setFloat(9, df.getSalary());
			ps.setString(11, df.getLocname());
			ps.setString(10, df.getAddress());
			int j = ps.executeUpdate();
			if (j == 1)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "fail";
	}//

	public String deleteDriver(Driver df) throws Exception {
		String isDeleted = "false";
		try {
			int n = df.getDriverid();
			Statement st = con.createStatement();
			int i = st.executeUpdate("DELETE FROM drv_details WHERE driver_id=" + n);
			logger.info("no.of records deleted : " + i);
			if (i != 0)
				isDeleted = "true";
		} catch (Exception e) {
			isDeleted = "false";
			logger.error("Exception : ", e);
		}
		return isDeleted;
	}// deleteLocation

	public List<Driver> myCollection(Driver df) throws Exception {
		List<Driver> driverList = new ArrayList<Driver>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT driver_id, driver_name, dob, gender, qualification, experience, licence_no, licence_type, doj, salary, loct_name, address FROM drv_details WHERE licence_type = '"
							+ df.getLicencetype() + "'");
			while (rs.next()) {
				Driver ddto = new Driver();
				ddto.setDriverid(rs.getInt(1));
				ddto.setDrivername(rs.getString(2));
				ddto.setDob(rs.getString(3));
				ddto.setGender(rs.getString(4));
				ddto.setQualification(rs.getString(5));
				ddto.setExperience(rs.getString(6));
				ddto.setLicenceno(rs.getString(7));
				ddto.setLicencetype(rs.getString(8));
				ddto.setDoj(rs.getString(9));
				ddto.setSalary(rs.getFloat(10));
				ddto.setLocname(rs.getString(11));
				ddto.setAddress(rs.getString(12));
				driverList.add(ddto);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return driverList;
	}

	public boolean viewTabDriver(Driver df) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(
				"SELECT driver_id, driver_name, dob, gender, qualification, experience, licence_no, licence_type, doj, salary, loct_name, address FROM drv_details WHERE licence_type = '"
						+ df.getLicencetype() + "'");
		while (rs.next()) {
			df.setLicencetype(rs.getString(8));
		} // while
		return false;
	}// viewtabdriver

	public List<Driver> getDriversList() throws Exception {
		List<Driver> driversList = new ArrayList<Driver>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM drv_details");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getString(4) + "\t");
			System.out.print(rs.getString(5) + "\t");
			System.out.print(rs.getString(6) + "\t");
			System.out.print(rs.getString(7) + "\t");
			System.out.print(rs.getString(8) + "\t");
			System.out.print(rs.getString(9) + "\t");
			System.out.print(rs.getInt(10) + "\t");
			System.out.print(rs.getString(11) + "\t");
			System.out.print(rs.getString(12));
			Driver ddto = new Driver();
			ddto.setDriverid(rs.getInt("driver_id"));
			ddto.setDrivername(rs.getString("driver_name"));
			ddto.setDob(rs.getString("dob"));
			ddto.setGender(rs.getString("gender"));
			ddto.setQualification(rs.getString("qualification"));
			ddto.setExperience(rs.getString("experience"));
			ddto.setLicenceno(rs.getString("licence_no"));
			ddto.setLicencetype(rs.getString("licence_type"));
			ddto.setDoj(rs.getString("doj"));
			ddto.setSalary(rs.getInt("salary"));
			ddto.setLocname(rs.getString("loct_name"));
			ddto.setAddress(rs.getString("address"));
			driversList.add(ddto);
		}
		return driversList;
	}

	public List<Driver> viewDriversList(Driver df) throws Exception {
		List<Driver> driverList = new ArrayList<Driver>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM drv_details");
		while (rs.next()) {
			Driver ddto = new Driver();
			ddto.setDriverid(rs.getInt(1));
			ddto.setDrivername(rs.getString(2));
			ddto.setDob(rs.getString(3));
			ddto.setGender(rs.getString(4));
			ddto.setQualification(rs.getString(5));
			ddto.setExperience(rs.getString(6));
			ddto.setLicenceno(rs.getString(7));
			ddto.setLicencetype(rs.getString(8));
			ddto.setDoj(rs.getString(9));
			ddto.setSalary(rs.getInt(10));
			ddto.setLocname(rs.getString(11));
			ddto.setAddress(rs.getString(12));
			driverList.add(ddto);
		}
		return driverList;
	}

	public List<Driver> getDIDS() throws Exception {
		List<Driver> al = new ArrayList<Driver>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT driver_id FROM drv_details");
			while (rs.next()) {
				Driver df = new Driver();
				df.setDriverid(rs.getInt("driver_id"));
				al.add(df);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

	public List<Driver> getLicenceTypes() throws Exception {
		List<Driver> al = new ArrayList<Driver>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT(licence_type) FROM drv_details");
			while (rs.next()) {
				Driver df = new Driver();
				df.setLicencetype(rs.getString("licence_type"));
				al.add(df);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

}// class
