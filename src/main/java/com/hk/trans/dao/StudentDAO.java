package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Student;

public class StudentDAO {
	private static final Logger logger = LoggerFactory.getLogger(RouteDAO.class);
	private Connection con = null;

	public StudentDAO(Connection connection) {
		try {
			con = connection;
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
	}

	public List<Student> getSID() throws Exception {
		List<Student> al = new ArrayList<Student>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT s_id FROM Student_details");
			while (rs.next()) {
				Student rf = new Student();
				rf.setSid(rs.getInt("s_id"));
				al.add(rf);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

	public List<Student> getCls() throws Exception {
		List<Student> al = new ArrayList<Student>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT(cls) FROM Student_details");
			while (rs.next()) {
				Student rf = new Student();
				rf.setCls(rs.getString("cls"));
				al.add(rf);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return al;
	}

	public String insertStudent(Student sdf) throws Exception {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(S_ID) FROM Student_details");
			int max = 1;
			if (rs.next()) {
				max = rs.getInt(1);
			}
			System.out.println("Max S_ID : " + max);

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO student_details VALUES(" + (max + 1) + ", ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, sdf.getFirstname());
			ps.setString(2, sdf.getLastname());
			ps.setString(3, sdf.getFname());
			ps.setString(4, sdf.getDob());
			ps.setString(5, sdf.getCls());
			ps.setString(6, sdf.getSec());
			ps.setString(7, sdf.getGender());
			ps.setString(8, sdf.getLoc());
			ps.setString(9, sdf.getAddr());
			int i = ps.executeUpdate();
			if (i == 1)
				return "true";
			return "false";
		} catch (Exception e) {
			logger.error("Exception : ", e);
			return null;
		}
	}

	public String EditStudent(Student edf) throws Exception {
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM student_details WHERE s_id = ?");
			pst.setInt(1, edf.getSid());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				edf.setFirstname(rs.getString("f_name"));
				edf.setLastname(rs.getString("l_name"));
				edf.setFname(rs.getString("fname"));
				edf.setDob(rs.getString("dob"));
				edf.setCls(rs.getString("cls"));
				edf.setSec(rs.getString("sec"));
				edf.setGender(rs.getString("gender"));
				edf.setLoc(rs.getString("loc"));
				edf.setAddr(rs.getString("addr"));
				return "true";
			} else {
				return "false";
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
			return "false";
		} finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		} // finally
	}

	public String UpdateStudent(Student udf) throws Exception {
		try {
			int s = udf.getSid();
			PreparedStatement pst = con.prepareStatement(
					"UPDATE student_details SET F_name = ?, l_name = ?, fname = ?, dob = ?, cls = ?, sec = ?, gender = ?, loc = ?, addr = ? WHERE s_id ="
							+ s);
			pst.setString(1, udf.getFirstname());
			pst.setString(2, udf.getLastname());
			pst.setString(3, udf.getFname());
			pst.setString(4, udf.getDob());
			pst.setString(5, udf.getCls());
			pst.setString(6, udf.getSec());
			pst.setString(7, udf.getGender());
			pst.setString(8, udf.getLoc());
			pst.setString(9, udf.getAddr());
			int i = pst.executeUpdate();
			if (i != 0)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "fail";
	}

	public String DeleteStudent(Student dsf) throws Exception {
		try {
			int j = dsf.getSid();
			System.out.println("entered record id=" + j);
			Statement st = con.createStatement();
			int i = st.executeUpdate("DELETE FROM student_details where s_id=" + j);
			System.out.println("no.of records deleted:" + i);
			if (i != 0)
				return "true";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "false";
	}

	public String ViewStudent(Student vdf) throws Exception {
		try {
			int j = 0;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM student_details where s_id=" + vdf.getSid());
			while (rs.next()) {
				j = j + 1;
				vdf.setSid(rs.getInt(1));
				vdf.setFirstname(rs.getString(2));
				vdf.setLastname(rs.getString(3));
				vdf.setFname(rs.getString(4));
				vdf.setDob(rs.getString(5));
				vdf.setCls(rs.getString(6));
				vdf.setSec(rs.getString(7));
				vdf.setGender(rs.getString(8));
				vdf.setLoc(rs.getString(9));
				vdf.setAddr(rs.getString(10));
			}
			if (j != 1)
				return "false";
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "true";
	}

	public String ViewTable(Student vtf) throws Exception {
		List<Student> student = new ArrayList<Student>();
		try {
			HttpServletRequest request = null;
			int k = 0;
			Statement st = con.createStatement();
			System.out.println("entered class:" + vtf.getCls());
			ResultSet rs = st
					.executeQuery("SELECT f_name, l_name, fname, dob, gender, sec FROM student_details WHERE cls = '"
							+ vtf.getCls() + "'");
			while (rs.next()) {
				k = k + 1;
				vtf.setFirstname(rs.getString("firstname"));
				vtf.setLastname(rs.getString("lastname"));
				vtf.setFname(rs.getString("fname"));
				vtf.setDob(rs.getString("dob"));
				vtf.setGender(rs.getString("gender"));
				vtf.setSec(rs.getString("sec"));
				System.out.println("firstname:" + vtf.getFirstname() + "\n" + "lastname:" + vtf.getLastname() + "\n"
						+ "fname:" + vtf.getFname() + "\n" + "dob:" + vtf.getDob() + "\n" + "gender:" + vtf.getGender()
						+ "\n" + "sect:" + vtf.getSec());
				student.add(vtf);
			}

			request.setAttribute("student", student);
			if (k != 0)
				return "true";

		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return "false";
	}

	public List<Student> mycollection(Student vtf) throws Exception {
		List<Student> v = new ArrayList<Student>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select s_id,f_name,l_name,fname,dob,cls,sec,gender,loc,addr  from student_details where cls='"
							+ vtf.getCls() + "'");
			while (rs.next()) {
				Student umdet = new Student();
				umdet.setSid(rs.getInt(1));
				umdet.setFirstname(rs.getString(2));
				umdet.setLastname(rs.getString(3));
				umdet.setFname(rs.getString(4));
				umdet.setDob(rs.getString(5));
				umdet.setCls(rs.getString(6));
				umdet.setSec(rs.getString(7));
				umdet.setGender(rs.getString(8));
				umdet.setLoc(rs.getString(9));
				umdet.setAddr(rs.getString(10));
				v.add(umdet);
			}
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		return v;
	}

	public boolean Viewmessages(Student umf) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(
				"SELECT s_id, f_name, l_name, fname, dob, cls, sec, gender, loc, addr FROM student_details WHERE cls = '"
						+ umf.getCls() + "'");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getString(4) + "\t");
			System.out.print(rs.getString(5) + "\t");
			System.out.println(rs.getString(6));
			System.out.println(rs.getString(7));
			System.out.println(rs.getString(8));
			System.out.println(rs.getString(9));
			System.out.println(rs.getString(10));
			umf.setCls(rs.getString(6));
		}
		return false;
	}

	public List<Student> getAllStudents() throws Exception {
		List<Student> v = new ArrayList<Student>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM student_details");
		while (rs.next()) {
			Student stdto = new Student();
			stdto.setSid(rs.getInt(1));
			stdto.setFirstname(rs.getString(2));
			stdto.setLastname(rs.getString(3));
			stdto.setFname(rs.getString(4));
			stdto.setDob(rs.getString(5));
			stdto.setCls(rs.getString(6));
			stdto.setSec(rs.getString(7));
			stdto.setGender(rs.getString(8));
			stdto.setLoc(rs.getString(9));
			stdto.setAddr(rs.getString(10));
			v.add(stdto);
		}
		return v;
	}

	public List<Student> viewstudentlist(Student sf) throws Exception {
		List<Student> v = new ArrayList<Student>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM student_details");
		while (rs.next()) {
			Student stdto = new Student();
			stdto.setSid(rs.getInt(1));
			stdto.setFirstname(rs.getString(2));
			stdto.setLastname(rs.getString(3));
			stdto.setFname(rs.getString(4));
			stdto.setDob(rs.getString(5));
			stdto.setCls(rs.getString(6));
			stdto.setSec(rs.getString(7));
			stdto.setGender(rs.getString(8));
			stdto.setLoc(rs.getString(9));
			stdto.setAddr(rs.getString(10));
			v.add(stdto);
		}
		return v;
	}
}
