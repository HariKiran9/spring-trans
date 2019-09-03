package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.AllotVeh;

public class AllotVehDAO {
	private static final Logger logger = LoggerFactory.getLogger(AllotVehDAO.class);
	private Connection con = null;

	public AllotVehDAO(Connection connection) {
		try {
			con = connection;
		} // try
		catch (Exception e) {
		}
	}//

	public List<AllotVeh> getAllVehicals() throws Exception {
		List<AllotVeh> vehicals = new ArrayList<AllotVeh>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Allot_veh_details");
			while (rs.next()) {
				AllotVeh adto = new AllotVeh();
				adto.setVehicleid(rs.getInt("vehicle_id"));
				adto.setRouteid(rs.getInt("route_id"));
				adto.setDname(rs.getString("driver_name"));
				vehicals.add(adto);
			}

		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return vehicals;
	}

	public String addAllotVeh(AllotVeh avf) throws Exception {
		try {
			PreparedStatement ps = con.prepareStatement("insert into Allot_veh_details values(?,?,?)");
			ps.setInt(1, avf.getVehicleid());
			ps.setInt(2, avf.getRouteid());
			ps.setString(3, avf.getDname());
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
	}//

	public List<AllotVeh> myCollection(AllotVeh avf) throws Exception {
		List<AllotVeh> v = new ArrayList<AllotVeh>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select vehicle_id,route_id,driver_name from Allot_veh_details where vehicle_id="
							+ avf.getVehicleid());
			while (rs.next()) {
				AllotVeh adto = new AllotVeh();
				adto.setVehicleid(rs.getInt(1));
				adto.setRouteid(rs.getInt(2));
				adto.setDname(rs.getString(3));
				v.add(adto);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return v;
	}

	public boolean viewTabAllotVeh(AllotVeh avf) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st
				.executeQuery("select vehicle_id, route_id, driver_name from allot_veh_details where vehicle_id="
						+ avf.getVehicleid());
		while (rs.next()) {
			avf.setVehicleid(rs.getInt(1));
		} // while
		return false;
	}//

	public List<AllotVeh> getVIDS() throws Exception {
		List<AllotVeh> al = new ArrayList<AllotVeh>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select vehicle_id from Allot_veh_details");
			while (rs.next()) {
				AllotVeh af = new AllotVeh();
				af.setVehicleid(rs.getInt("vehicle_id"));
				al.add(af);
			}
		} catch (Exception e) {
		}
		return al;
	}

}// class
