package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Maintenance;

public class MaintenanceDAO {
	private static final Logger logger = LoggerFactory.getLogger(MaintenanceDAO.class);
	private Connection con = null;

	public MaintenanceDAO(Connection connection) {
		try {
			con = connection;
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
	}//

	public String addMaintenance(Maintenance mf) throws Exception {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select max(BILL_NO) from maintenance_details");
			int max = 0;
			if (rs.next()) {
				max = rs.getInt(1);
			}
			PreparedStatement ps = con
					.prepareStatement("insert into maintenance_details values(" + (max + 1) + ",?,?,?,?)");
			ps.setInt(1, mf.getVehicleid());
			ps.setString(2, mf.getFuelqty());
			ps.setString(3, mf.getBilldate());
			ps.setString(4, mf.getAmount());
			int i = ps.executeUpdate();
			if (i == 1)
				return "true";
			return "false";
		} catch (Exception e) {
			logger.error("Exception : ", e);
			return null;
		} finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		} // finally
	}//

	public List<Maintenance> myCollection(Maintenance mf) throws Exception {
		List<Maintenance> v = new ArrayList<Maintenance>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select vehicle_id,fuel_quantity,bill_date,bill_no,amount from maintenance_details where vehicle_id="
							+ mf.getVehicleid());
			while (rs.next()) {
				System.out.println("--mycollection rs.next()---");
				Maintenance mdto = new Maintenance();
				mdto.setVehicleid(rs.getInt(1));
				mdto.setFuelqty(rs.getString(2));
				mdto.setBilldate(rs.getString(3));
				mdto.setBillno(rs.getInt(4));
				mdto.setAmount(rs.getString(5));
				v.add(mdto);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return v;
	}

	public boolean viewTabMaintenance(Maintenance mf) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(
				"select vehicle_id,fuel_quantity,bill_date,bill_no,amount from maintenance_details where vehicle_id="
						+ mf.getVehicleid());
		while (rs.next()) {
			mf.setVehicleid(rs.getInt(1));
		} // while
		return false;
	}//

	public List<Maintenance> viewmaintenances(Maintenance mf) throws Exception {
		List<Maintenance> v = new ArrayList<Maintenance>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from maintenance_details");
		while (rs.next()) {
			Maintenance mdto = new Maintenance();
			mdto.setBillno(rs.getInt(1));
			mdto.setVehicleid(rs.getInt(2));
			mdto.setFuelqty(rs.getString(3));
			mdto.setBilldate(rs.getString(4));
			mdto.setAmount(rs.getString(5));
			v.add(mdto);
		}
		return v;
	}

	public List<Maintenance> viewMaintenancelist(Maintenance mf) throws Exception {
		List<Maintenance> v = new ArrayList<Maintenance>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select *from maintenance_details");
		while (rs.next()) {
			Maintenance mdto = new Maintenance();
			mdto.setBillno(rs.getInt(1));
			mdto.setVehicleid(rs.getInt(2));
			mdto.setFuelqty(rs.getString(3));
			mdto.setBilldate(rs.getString(4));
			mdto.setAmount(rs.getString(5));
			v.add(mdto);
		}
		return v;
	}

	public List<Maintenance> getVIDS() throws Exception {
		List<Maintenance> al = new ArrayList<Maintenance>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select vehicle_id from maintenance_details");
			while (rs.next()) {
				Maintenance mf = new Maintenance();
				mf.setVehicleid(rs.getInt("vehicle_id"));
				al.add(mf);
			}
		} catch (Exception e) {
		}
		return al;
	}

}// class
