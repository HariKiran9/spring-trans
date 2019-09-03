package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import com.hk.trans.model.Halt;

public class HaltDAO {
	Connection con = null;
	Statement st = null;
	PreparedStatement ps = null;
	Halt hdto = null;
	ResultSet rs = null;
	Vector<Halt> v = new Vector<Halt>();
	int n = 0;
	int j = 0;

	public HaltDAO(Connection connection) {
		try {
			con = connection;
			System.out.println("connection");
		} // try
		catch (Exception e) {
		}
	}// haltdao

	public String addHalt(Halt hf) throws Exception {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select max(HALT_ID) from halt_details");
			int max = 0;
			if (rs.next()) {
				max = rs.getInt(1);
			}
			System.out.println("Max S_ID : " + max);
			ps = con.prepareStatement("insert into halt_details values(" + (max + 1) + ",?,?,?)");
			ps.setString(1, hf.getHaltname());
			ps.setInt(2, hf.getInstuds());
			ps.setInt(3, hf.getOutstuds());
			System.out.println("in ps");
			int i = ps.executeUpdate();
			System.out.println("in execute");
			if (i == 1)
				return "true";
			return "false";
		} // try
		catch (Exception e) {
			e.printStackTrace();
			return null;
		} // catch
		finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		} // finally
	}// addHalt

	public String changeHalt(Halt hf) throws Exception {
		try {
			ps = con.prepareStatement("select * from halt_details where halt_id=?");
			ps.setInt(1, hf.getHaltid());
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			if (rs.next()) {
				hf.setHaltname(rs.getString("halt_name"));
				hf.setInstuds(rs.getInt("in_studs"));
				hf.setOutstuds(rs.getInt("out_studs"));
				System.out.println("in EditAction");
				return "true";
			} // if
			else {
				return "false";
			}
		} // try
		finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		} // finally
	}// changehalts

	public String updateHalt(Halt hf) throws Exception {
		try {

			int i = hf.getHaltid();
			System.out.println(i);
			ps = con.prepareStatement("update halt_details set halt_name=?,in_studs=?,out_studs=? where halt_id=" + i);
			ps.setString(1, hf.getHaltname());
			ps.setInt(2, hf.getInstuds());
			ps.setInt(3, hf.getOutstuds());
			System.out.println("in updateAction");
			j = ps.executeUpdate();
			if (j == 1)
				return "true";
		} // try
		catch (Exception e) {
		}
		return "false";
	}// updatehalt

	public Collection<Halt> myCollection(Halt hf) throws Exception {
		try {
			System.out.println("--mycollection---");
			st = con.createStatement();
			rs = st.executeQuery("select halt_id,halt_name,in_studs,out_studs from halt_details where halt_name='"
					+ hf.getHaltname() + "'");
			while (rs.next()) {
				System.out.println("--mycollection rs.next()---");
				hdto = new Halt();
				hdto.setHaltid(rs.getInt(1));
				hdto.setHaltname(rs.getString(2));
				hdto.setInstuds(rs.getInt(3));
				hdto.setOutstuds(rs.getInt(4));
				v.add(hdto);
				System.out.println(v.size());
				System.out.println("-- end of mycollection---");
			}

		} catch (Exception e) {
		}
		System.out.println("V:");
		return v;
	}

	public boolean viewTabHalt(Halt hf) throws Exception {
		System.out.println("connection in tabdriver");
		st = con.createStatement();
		rs = st.executeQuery("select halt_id,halt_name,in_studs,out_studs  from halt_details where halt_id='"
				+ hf.getHaltid() + "'");
		System.out.println("---EXEcutequerey--");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			hf.setHaltname(rs.getString(2));

		} // while
		return false;
	}// viewtabhalt

	public String deleteHalt(Halt hf) throws Exception {
		try {
			n = hf.getHaltid();
			System.out.println("entered record name=" + n);
			st = con.createStatement();
			int i = st.executeUpdate("delete from halt_details where halt_id='" + n + "'");
			System.out.println("no.of records deleted:" + i);
			if (i != 0)
				return "true";
		} // try

		catch (Exception e) {
		}
		return "false";
	}// deleteHalt

	public boolean setHalt(Halt hf) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from halt_details");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getInt(3) + "\t");
			System.out.print(rs.getInt(4) + "\t");
			return true;
		} // while
		return false;
	}// SetHaltForm

	public Collection<Halt> viewHaltsList(Halt hf) throws Exception {
		st = con.createStatement();
		rs = st.executeQuery("select * from halt_details");
		while (rs.next()) {
			Halt hdto = new Halt();
			hdto.setHaltid(rs.getInt("halt_id"));
			hdto.setHaltname(rs.getString(2));
			hdto.setInstuds(rs.getInt(3));
			hdto.setOutstuds(rs.getInt(4));
			v.add(hdto);
			System.out.println("halt_details Size : " + v.size());
		} // while
		return v;
	}// viewhaltlist

	public Collection<Halt> getHIDS() throws Exception {
		ArrayList<Halt> al = new ArrayList<Halt>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select halt_id from halt_details");
			while (rs.next()) {
				Halt hf = new Halt();
				hf.setHaltid(rs.getInt("halt_id"));
				al.add(hf);
			}
		} catch (Exception e) {
		}
		return al;
	}

}// class
