package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Route;

public class RouteDAO {
	private static final Logger logger = LoggerFactory.getLogger(RouteDAO.class);
	private Connection con = null;

	public RouteDAO(Connection connection) {
		try {
			con = connection;
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
	}//

	public String addRoute(Route rf) throws Exception {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select max(ROUTE_ID	) from rout_details");
			int max = 0;
			if (rs.next()) {
				max = rs.getInt(1);
			}
			PreparedStatement ps = con
					.prepareStatement("insert into rout_details values(" + (max + 1) + ",?,?,?,?,?,?,?,?,?)");
			ps.setString(1, rf.getRoutename());
			ps.setInt(2, rf.getNoofstuds());
			ps.setInt(3, rf.getTotalhalts());
			ps.setString(4, rf.getStartpoint());
			ps.setString(5, rf.getDestpoint());
			ps.setInt(6, rf.getVehicleid());
			ps.setInt(7, rf.getDriverid());
			ps.setInt(8, rf.getHelperid());
			ps.setInt(9, rf.getHaltid());
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
	}// addDriver

	public String changeRoute(Route rf) throws Exception {
		try {
			PreparedStatement ps = con.prepareStatement("select * from rout_details where route_id=?");
			ps.setInt(1, rf.getRouteid());
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			if (rs.next()) {
				rf.setRoutename(rs.getString("route_name"));
				rf.setNoofstuds(rs.getInt("No_studs"));
				rf.setTotalhalts(rs.getInt("total_halts"));
				rf.setStartpoint(rs.getString("start_point"));
				rf.setDestpoint(rs.getString("dest_point"));
				rf.setVehicleid(rs.getInt("vehicle_id"));
				rf.setDriverid(rs.getInt("driver_id"));
				rf.setHelperid(rs.getInt("helper_id"));
				rf.setHaltid(rs.getInt("Halt_id"));
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

	public String updateRoute(Route rf) throws Exception {
		try {
			int i = rf.getRouteid();
			System.out.println(i);
			PreparedStatement ps = con.prepareStatement(
					"update rout_details set route_name=?,no_studs=?,total_halts=?, start_point=?,dest_point=?,vehicle_id=?,driver_id=?,helper_id=?,halt_id=? where route_id="
							+ i);
			ps.setString(1, rf.getRoutename());
			ps.setInt(2, rf.getNoofstuds());
			ps.setInt(3, rf.getTotalhalts());
			ps.setString(4, rf.getStartpoint());
			ps.setString(5, rf.getDestpoint());
			ps.setInt(6, rf.getVehicleid());
			ps.setInt(7, rf.getDriverid());
			ps.setInt(8, rf.getHelperid());
			ps.setInt(9, rf.getHaltid());
			int j = ps.executeUpdate();
			if (j == 1)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "fail";
	}// updatedriver

	public String deleteRoute(Route rf) throws Exception {
		try {
			int n = rf.getRouteid();
			System.out.println("entered record name=" + n);
			Statement st = con.createStatement();
			int i = st.executeUpdate("delete from rout_details where route_id='" + n + "'");
			System.out.println("no.of records deleted:" + i);
			if (i != 0)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "false";
	}// deleteLocation

	public List<Route> myCollection(Route rf) throws Exception {
		List<Route> v = new ArrayList<Route>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select route_id,route_name,no_studs,total_halts,start_point,dest_point,vehicle_id,driver_id,helper_id,halt_id from rout_details where route_id='"
							+ rf.getRouteid() + "'");

			while (rs.next()) {
				Route rdto = new Route();
				rdto.setRouteid(rs.getInt(1));
				rdto.setRoutename(rs.getString(2));
				rdto.setNoofstuds(rs.getInt(3));
				rdto.setTotalhalts(rs.getInt(4));
				rdto.setStartpoint(rs.getString(5));
				rdto.setDestpoint(rs.getString(6));
				rdto.setVehicleid(rs.getInt(7));
				rdto.setDriverid(rs.getInt(8));
				rdto.setHelperid(rs.getInt(9));
				rdto.setHaltid(rs.getInt(10));
				v.add(rdto);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return v;
	}

	public boolean viewTabRoute(Route rf) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(
				"select route_id,route_name,no_studs,total_halts,start_point,dest_point,vehicle_id,driver_id,helper_id,halt_id from rout_details where route_id='"
						+ rf.getRouteid() + "'");
		while (rs.next()) {
			rf.setRouteid(rs.getInt(1));
		} // while
		return false;
	}// viewtabdriver

	public List<Route> getALLRrouts(Route rf) throws Exception {
		List<Route> v = new ArrayList<Route>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from rout_details");
		while (rs.next()) {
			Route rdto = new Route();
			rdto.setRouteid(rs.getInt(1));
			rdto.setRoutename(rs.getString(2));
			rdto.setNoofstuds(rs.getInt(3));
			rdto.setTotalhalts(rs.getInt(4));
			rdto.setStartpoint(rs.getString(5));
			rdto.setDestpoint(rs.getString(6));
			rdto.setVehicleid(rs.getInt(7));
			rdto.setDriverid(rs.getInt(8));
			rdto.setHelperid(rs.getInt(9));
			rdto.setHaltid(rs.getInt(10));
			v.add(rdto);
		}
		return v;
	}

	public List<Route> viewRoutelist(Route rf) throws Exception {
		List<Route> v = new ArrayList<Route>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from rout_details");
		while (rs.next()) {
			Route rdto = new Route();
			rdto.setRouteid(rs.getInt(1));
			rdto.setRoutename(rs.getString(2));
			rdto.setNoofstuds(rs.getInt(3));
			rdto.setTotalhalts(rs.getInt(4));
			rdto.setStartpoint(rs.getString(5));
			rdto.setDestpoint(rs.getString(6));
			rdto.setVehicleid(rs.getInt(7));
			rdto.setDriverid(rs.getInt(8));
			rdto.setHelperid(rs.getInt(9));
			rdto.setHaltid(rs.getInt(10));
			v.add(rdto);
		}
		return v;
	}

	public List<Route> getRIDS() throws Exception {
		List<Route> al = new ArrayList<Route>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select route_id from rout_details");
			while (rs.next()) {
				Route rf = new Route();
				rf.setRouteid(rs.getInt("route_id"));
				al.add(rf);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

}// class
