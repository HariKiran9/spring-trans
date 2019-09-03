package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Emp;

public class EmpDAO {

	private static final Logger logger = LoggerFactory.getLogger(EmpDAO.class);
	private Connection con = null;

	public EmpDAO(Connection connection) {
		try {
			con = connection;
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
	}

	public List<Emp> viewEmployees() throws Exception {
		List<Emp> empList = new ArrayList<Emp>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM emp_details");
		while (rs.next()) {
			Emp empdto = new Emp();
			empdto.setName(rs.getString("name"));
			empdto.setDesg(rs.getString("desg"));
			empdto.setMailid(rs.getString("mailid"));
			empdto.setPhno(rs.getInt("phno"));
			empdto.setQual(rs.getString("qual"));
			empdto.setExp(rs.getInt("exp"));
			empdto.setSal(rs.getInt("sal"));
			empdto.setAddr(rs.getString("addr"));
			empList.add(empdto);
		}
		return empList;
	}

	public List<Emp> viewemployeeslist(Emp erf) throws Exception {
		List<Emp> empList = new ArrayList<Emp>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM emp_details");
		while (rs.next()) {
			Emp empdto = new Emp();
			empdto.setName(rs.getString("name"));
			empdto.setDesg(rs.getString("desg"));
			empdto.setMailid(rs.getString("mailid"));
			empdto.setPhno(rs.getInt("phno"));
			empdto.setQual(rs.getString("qual"));
			empdto.setExp(rs.getInt("exp"));
			empdto.setSal(rs.getInt("sal"));
			empdto.setAddr(rs.getString("addr"));
			empList.add(empdto);
		}
		return empList;
	}

	public Emp getEmployeeByEmailId(String mailId) throws Exception {
		Emp employee = new Emp();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM emp_details WHERE mailid = '" + mailId + "'");
		while (rs.next()) {
			employee.setName(rs.getString("name"));
			employee.setDesg(rs.getString("desg"));
			employee.setMailid(rs.getString("mailid"));
			employee.setPhno(rs.getInt("phno"));
			employee.setQual(rs.getString("qual"));
			employee.setExp(rs.getInt("exp"));
			employee.setSal(rs.getInt("sal"));
			employee.setAddr(rs.getString("addr"));

		} // while
		return employee;
	}

}
