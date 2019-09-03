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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hk.trans.dao.DriverDAO;
import com.hk.trans.model.Driver;

/**
 * @author Hari Kiran
 *
 */
@RestController
@RequestMapping(value = "/driver", produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverController {

	private static final Logger logger = LoggerFactory.getLogger(BillController.class);

	@Autowired
	DataSource dataSource;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getDrivers() throws Exception {
		DriverDAO driverDAO = new DriverDAO(dataSource.getConnection());
		return new ResponseEntity<>(driverDAO.getDriversList(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getDriverDetailsById(@PathVariable(value = "id") Integer driverId)
			throws Exception {
		logger.info("Driver Id : {driverId} ", driverId);
		Driver driver = new Driver();
		driver.setDriverid(driverId);
		DriverDAO driverDAO = new DriverDAO(dataSource.getConnection());
		return new ResponseEntity<>(driverDAO.viewTabDriver(driver), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<? extends Object> create(@RequestBody Driver driver) throws Exception {
		DriverDAO driverDAO = new DriverDAO(dataSource.getConnection());
		return new ResponseEntity<>(driverDAO.addDriver(driver), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<? extends Object> update(@RequestBody Driver driver) throws Exception {
		DriverDAO driverDAO = new DriverDAO(dataSource.getConnection());
		return new ResponseEntity<>(driverDAO.updateDriver(driver), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<? extends Object> delete(@PathVariable(value = "id") int driverId) throws Exception {
		Driver driver = new Driver();
		driver.setDriverid(driverId);
		DriverDAO driverDAO = new DriverDAO(dataSource.getConnection());
		return new ResponseEntity<>(driverDAO.deleteDriver(driver), HttpStatus.OK);
	}
}
