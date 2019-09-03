/**
 * 
 */
package com.hk.trans.controller;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hk.trans.dao.EmpDAO;

/**
 * @author Hari Kiran
 *
 */
@RestController
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	DataSource dataSource;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getEmployees() throws Exception {
		EmpDAO empDAO = new EmpDAO(dataSource.getConnection());
		return new ResponseEntity<>(empDAO.viewEmployees(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getEmployeeByMailId(@PathVariable(value = "emailId") String mailId)
			throws Exception {
		logger.info("Email Id : {mailId} ", mailId);
		EmpDAO empDAO = new EmpDAO(dataSource.getConnection());
		return new ResponseEntity<>(empDAO.getEmployeeByEmailId(mailId), HttpStatus.OK);
	}

}
