package com.hk.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.trans.model.Bill;

/**
 * @author Administrator
 *
 */
public class BillDAO {

	private static final Logger logger = LoggerFactory.getLogger(BillDAO.class);

	Connection connection = null;

	public BillDAO(Connection connection) {
		try {
			this.connection = connection;
		} // try
		catch (Exception e) {
		}
	}// billdao

	public String insertBill(Bill bf) throws Exception {
		logger.info(" Student Id : " + bf.getSid());
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select max(BILL_NO) from bill_details");
			int max = 0;
			if (rs.next()) {
				max = rs.getInt(1);
			}
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into bill_details values(" + (max + 1) + ",?,?,?,?,?,?,?)");

			preparedStatement.setInt(1, bf.getSid());
			preparedStatement.setString(2, bf.getFromdate());
			preparedStatement.setString(3, bf.getTodate());
			preparedStatement.setString(4, bf.getDistance());
			preparedStatement.setString(5, bf.getDueamt());
			preparedStatement.setString(6, bf.getDuedate());
			preparedStatement.setString(7, bf.getPaidamt());
			int i = preparedStatement.executeUpdate();
			System.out.println("in execute");
			if (i == 1)
				return "true";
			return "false";
		} // try
		catch (Exception e) {
			logger.error(" Exception : ", e);
			return null;
		} // catch
		finally {
			try {
				connection.close();
			} catch (Exception e) {
			}
		} // finally
	}//

	public String updateBill(Bill bf) throws Exception {
		logger.info(" Student Id : " + bf.getSid());
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE bill_details SET sid = ?, from_date = ?, to_date = ?, distance = ?, due_amt = ?, due_date = ?, paid_amt = ? WHERE bill_no = ?");
			preparedStatement.setInt(1, bf.getSid());
			preparedStatement.setString(2, bf.getFromdate());
			preparedStatement.setString(3, bf.getTodate());
			preparedStatement.setString(4, bf.getDistance());
			preparedStatement.setString(5, bf.getDueamt());
			preparedStatement.setString(6, bf.getDuedate());
			preparedStatement.setString(7, bf.getPaidamt());
			preparedStatement.setInt(8, bf.getBillno());
			int i = preparedStatement.executeUpdate();
			logger.info(" Bill Details {id} is update {} ", bf.getBillno(), i);
			if (i > 0)
				return "true";
			return "false";
		} catch (Exception e) {
			logger.error(" Exception : ", e);
			return null;
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
			}
		} // finally
	}

	public boolean deleteBill(int billNo) throws Exception {
		logger.info(" Student Id : " + billNo);
		boolean isDeleted = false;
		try {
			PreparedStatement preparedStmt = connection.prepareStatement("DELETE FROM bill_details WHERE bill_no = ?");
			preparedStmt.setInt(1, billNo);
			isDeleted = preparedStmt.execute();
			isDeleted = true;
		} catch (Exception e) {
			isDeleted = false;
			logger.error(" Exception : ", e);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
			}
		} // finally
		return isDeleted;
	}

	public List<Bill> myCollection(Bill bf) throws Exception {
		List<Bill> bills = new ArrayList<Bill>();
		try {
			Statement statement = connection.createStatement();
			logger.info(" Student Id : " + bf.getSid());
			ResultSet resultSet = statement.executeQuery(
					"select bill_no, sid, from_date, to_date, distance, due_amt, due_date, paid_amt from bill_details where sid="
							+ bf.getSid());
			while (resultSet.next()) {
				Bill bill = new Bill();
				bill.setBillno(resultSet.getInt(1));
				bill.setSid(resultSet.getInt(2));
				bill.setFromdate(resultSet.getString(3));
				bill.setTodate(resultSet.getString(4));
				bill.setDistance(resultSet.getString(5));
				bill.setDueamt(resultSet.getString(6));
				bill.setDuedate(resultSet.getString(7));
				bill.setPaidamt(resultSet.getString(8));
				bills.add(bill);
			}
		} catch (Exception e) {
			logger.error(" Exception : ", e);
		}
		logger.info(" Size of Bills :  : " + bills.size());
		return bills;
	}

	public Bill getBillDetailsById(int billId) throws Exception {
		Bill bill = new Bill();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT bill_no, sid, from_date, to_date, distance, due_amt, due_date, paid_amt FROM bill_details WHERE bill_no = "
							+ billId);
			while (resultSet.next()) {
				bill.setBillno(resultSet.getInt(1));
				bill.setSid(resultSet.getInt(2));
				bill.setFromdate(resultSet.getString(3));
				bill.setTodate(resultSet.getString(4));
				bill.setDistance(resultSet.getString(5));
				bill.setDueamt(resultSet.getString(6));
				bill.setDuedate(resultSet.getString(7));
				bill.setPaidamt(resultSet.getString(8));
			}
		} catch (Exception e) {
			logger.error(" Exception : ", e);
		} finally {

		} // finally
		return bill;
	}

	public boolean viewTabBill(Bill bf) throws Exception {
		logger.info(" Student Id : " + bf.getSid());
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"SELECT bill_no, sid, from_date, to_date, distance, due_amt, due_date, paid_amt FROM bill_details WHERE sid = "
						+ bf.getSid());
		while (resultSet.next()) {
			bf.setSid(resultSet.getInt(2));

		} // while
		return false;
	}//

	public List<Bill> getSID() throws Exception {
		List<Bill> bills = new ArrayList<Bill>();
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select s_id from Student_details");
			while (rs.next()) {
				Bill bf = new Bill();
				bf.setSid(rs.getInt("s_id"));
				bills.add(bf);
			}
		} catch (Exception e) {
			logger.error(" Exception : ", e);
		}
		return bills;
	}

}// class
