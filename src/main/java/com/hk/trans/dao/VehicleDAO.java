package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Vehicle;

public class VehicleDAO {

	private static final Logger logger = LoggerFactory.getLogger(RouteDAO.class);
	private Connection con = null;

	public VehicleDAO(Connection connection) {
		try {
			con = connection;
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
	}// cont

	public String addVehicle(Vehicle vf) throws Exception {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select max(VEHICLE_ID) from veh_details");
			int max = 0;
			if (rs.next()) {
				max = rs.getInt(1);
			}
			PreparedStatement ps = con
					.prepareStatement("insert into veh_details values(" + (max + 1) + ",?,?,?,?,?,?)");

			ps.setString(1, vf.getVehiclename());
			ps.setInt(2, vf.getCapacity());
			ps.setInt(3, vf.getMilage());
			ps.setInt(4, vf.getTotaltrips());
			ps.setInt(5, vf.getDriverid());
			ps.setInt(6, vf.getHelperid());
			int i = ps.executeUpdate();
			if (i == 1) {
				return "true";
			}
			return "false";
		} catch (Exception e) {
			logger.error("Exception : ", e);
			return null;
		} // catch
	}//

	public String changeVehicle(Vehicle vf) throws Exception {
		try {
			PreparedStatement ps = con.prepareStatement("select * from veh_details where vehicle_id=?");
			ps.setInt(1, vf.getVehicleid());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vf.setVehiclename(rs.getString("vehicle_name"));
				vf.setCapacity(rs.getInt("capacity"));
				vf.setMilage(rs.getInt("milage"));
				vf.setTotaltrips(rs.getInt("total_trips"));
				vf.setDriverid(rs.getInt("driver_id"));
				vf.setHelperid(rs.getInt("helper_id"));
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

	public String updateVehicle(Vehicle vf) throws Exception {
		try {
			int i = vf.getVehicleid();
			PreparedStatement ps = con.prepareStatement(
					"update veh_details set vehicle_name=?,capacity=?,milage=?, total_trips=?,driver_id=?,helper_id=? where vehicle_id="
							+ i);
			ps.setString(1, vf.getVehiclename());
			ps.setInt(2, vf.getCapacity());
			ps.setInt(3, vf.getMilage());
			ps.setInt(4, vf.getTotaltrips());
			ps.setInt(5, vf.getDriverid());
			ps.setInt(6, vf.getHelperid());
			int j = ps.executeUpdate();
			if (j == 1)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
			return "false";
		}
		return "fail";
	}//

	public String deleteVehicle(Vehicle vf) throws Exception {
		try {
			int n = vf.getVehicleid();
			Statement st = con.createStatement();
			int i = st.executeUpdate("delete from veh_details where vehicle_id='" + n + "'");
			System.out.println("no.of records deleted:" + i);
			if (i != 0)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "false";
	}//

	public List<Vehicle> myCollection(Vehicle vf) throws Exception {
		List<Vehicle> v = new ArrayList<Vehicle>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select vehicle_id,vehicle_name,capacity,milage,total_trips,driver_id,helper_id from veh_details where capacity="
							+ vf.getCapacity());
			while (rs.next()) {
				Vehicle vdto = new Vehicle();
				vdto.setVehicleid(rs.getInt(1));
				vdto.setVehiclename(rs.getString(2));
				vdto.setCapacity(rs.getInt(3));
				vdto.setMilage(rs.getInt(4));
				vdto.setTotaltrips(rs.getInt(5));
				vdto.setDriverid(rs.getInt(6));
				vdto.setHelperid(rs.getInt(7));
				v.add(vdto);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return v;
	}

	public boolean viewTabVehicle(Vehicle vf) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(
				"select vehicle_id,vehicle_name,capacity,milage,total_trips,driver_id,helper_id from veh_details where capacity="
						+ vf.getCapacity());
		while (rs.next()) {
			vf.setMilage(rs.getInt(4));
		} // while
		return false;
	}

	public List<Vehicle> getAllVehicles() throws Exception {
		List<Vehicle> v = new ArrayList<Vehicle>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM veh_details");
		while (rs.next()) {
			Vehicle vdto = new Vehicle();
			vdto.setVehicleid(rs.getInt(1));
			vdto.setVehiclename(rs.getString(2));
			vdto.setCapacity(rs.getInt(3));
			vdto.setMilage(rs.getInt(4));
			vdto.setTotaltrips(rs.getInt(5));
			vdto.setDriverid(rs.getInt(6));
			vdto.setHelperid(rs.getInt(7));
			v.add(vdto);
		}
		return v;
	}

	public List<Vehicle> viewVehiclelist(Vehicle vf) throws Exception {
		List<Vehicle> v = new ArrayList<Vehicle>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM veh_details");
		while (rs.next()) {
			Vehicle vdto = new Vehicle();
			vdto.setVehicleid(rs.getInt(1));
			vdto.setVehiclename(rs.getString(2));
			vdto.setCapacity(rs.getInt(3));
			vdto.setMilage(rs.getInt(4));
			vdto.setTotaltrips(rs.getInt(5));
			vdto.setDriverid(rs.getInt(6));
			vdto.setHelperid(rs.getInt(7));
			v.add(vdto);
		}
		return v;
	}

	public List<Vehicle> getVIDS() throws Exception {
		List<Vehicle> al = new ArrayList<Vehicle>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select vehicle_id from veh_details");
			while (rs.next()) {
				Vehicle vf = new Vehicle();
				vf.setVehicleid(rs.getInt("vehicle_id"));
				al.add(vf);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

	public List<Vehicle> getCapacity() throws Exception {
		List<Vehicle> al = new ArrayList<Vehicle>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select capacity from veh_details");
			while (rs.next()) {
				Vehicle vf = new Vehicle();
				vf.setCapacity(rs.getInt("capacity"));
				al.add(vf);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

}
