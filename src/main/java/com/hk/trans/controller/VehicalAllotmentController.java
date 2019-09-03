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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hk.trans.dao.AllotVehDAO;
import com.hk.trans.dao.BillDAO;
import com.hk.trans.model.AllotVeh;

/**
 * @author Hari Kiran
 *
 */
@RestController
@RequestMapping(value = "/vehicalallotment", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicalAllotmentController {

	private static final Logger logger = LoggerFactory.getLogger(VehicalAllotmentController.class);

	@Autowired
	DataSource dataSource;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getVIDS() throws Exception {
		AllotVehDAO allotVehicalDAO = new AllotVehDAO(dataSource.getConnection());
		return new ResponseEntity<>(allotVehicalDAO.getAllVehicals(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<? extends Object> addVehicalAlloment(@RequestBody AllotVeh allotVeh) throws Exception {
		AllotVehDAO allotVehicalDAO = new AllotVehDAO(dataSource.getConnection());
		return new ResponseEntity<>(allotVehicalDAO.addAllotVeh(allotVeh), HttpStatus.OK);
	}

}
