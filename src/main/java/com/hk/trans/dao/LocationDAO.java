package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Location;

public class LocationDAO {
	private static final Logger logger = LoggerFactory.getLogger(LocationDAO.class);
	private Connection con = null;

	public LocationDAO(Connection connection) {
		try {
			con = connection;
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
	}

	public String addLocation(Location lf) throws Exception {
		try {
			PreparedStatement ps = con.prepareStatement("insert into location_details values(?,?,?)");
			ps.setString(1, lf.getLocname());
			ps.setString(2, lf.getLandmark());
			ps.setString(3, lf.getAddress());
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
	}// addLocation

	public String changeLocation(Location lf) throws Exception {
		try {
			PreparedStatement ps = con
					.prepareStatement("select land_mark,address from location_details where loc_name=?");
			ps.setString(1, lf.getLocname());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				lf.setLandmark(rs.getString("land_mark"));
				lf.setAddress(rs.getString("address"));
				System.out.println("in EditAction");
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

	public String updateLocation(Location lf) throws Exception {
		try {
			PreparedStatement ps = con
					.prepareStatement("update location_details set loc_name=?,land_mark=?,address=? where loc_name='"
							+ lf.getLocname() + "'");
			ps.setString(1, lf.getLocname());
			ps.setString(2, lf.getLandmark());
			ps.setString(3, lf.getAddress());
			int j = ps.executeUpdate();
			if (j != 0)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "fail";
	}//

	public String deleteLocation(Location lf) throws Exception {
		try {
			Statement st = con.createStatement();
			int i = st.executeUpdate("delete from location_details where loc_name='" + lf.getLocname() + "'");
			System.out.println("no.of records deleted:" + i);
			if (i != 0)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "false";
	}//

	public List<Location> myCollection(Location lf) throws Exception {
		List<Location> v = new ArrayList<Location>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select loc_name,land_mark,address from location_details where loc_name='" + lf.getLocname() + "'");
			while (rs.next()) {
				Location ldto = new Location();
				ldto.setLocname(rs.getString(1));
				ldto.setLandmark(rs.getString(2));
				ldto.setAddress(rs.getString(3));
				v.add(ldto);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return v;
	}

	public boolean viewTabLocation(Location lf) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(
				"select Loc_name,land_mark,address from location_details where loc_name='" + lf.getLocname() + "'");
		while (rs.next()) {
			lf.setLocname(rs.getString(1));
		} // while
		return false;
	}//

	public List<Location> getAllLocations() throws Exception {
		List<Location> v = new ArrayList<Location>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from location_details");
		while (rs.next()) {
			Location ldto = new Location();
			ldto.setLocname(rs.getString(1));
			ldto.setLandmark(rs.getString(2));
			ldto.setAddress(rs.getString(3));
			v.add(ldto);
		}
		return v;
	}

	public List<Location> viewlocationlist(Location lf) throws Exception {
		List<Location> v = new ArrayList<Location>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select *from location_details");
		while (rs.next()) {
			Location ldto = new Location();
			ldto.setLocname(rs.getString(1));
			ldto.setLandmark(rs.getString(2));
			ldto.setAddress(rs.getString(3));
			v.add(ldto);
			System.out.println(v.size());
		}
		return v;
	}

	public List<Location> getLocnames() throws Exception {
		List<Location> al = new ArrayList<Location>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select loc_name from location_details");
			while (rs.next()) {
				Location lf = new Location();
				lf.setLocname(rs.getString("loc_name"));
				al.add(lf);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

}// class