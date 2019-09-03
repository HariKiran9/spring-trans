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

import com.hk.trans.dao.BillDAO;
import com.hk.trans.model.Bill;

/**
 * @author Hari Kiran
 *
 */
@RestController
@RequestMapping(value = "/bill", produces = MediaType.APPLICATION_JSON_VALUE)
public class BillController {

	private static final Logger logger = LoggerFactory.getLogger(BillController.class);

	@Autowired
	DataSource dataSource;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getBills() throws Exception {
		BillDAO bdao = new BillDAO(dataSource.getConnection());
		return new ResponseEntity<>(bdao.getSID(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getBillById(@PathVariable(value = "id") Integer id) throws Exception {
		logger.info("Id : {id} ", id);
		BillDAO bdao = new BillDAO(dataSource.getConnection());
		return new ResponseEntity<>(bdao.getBillDetailsById(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<? extends Object> create(@RequestBody Bill bill) throws Exception {
		BillDAO bdao = new BillDAO(dataSource.getConnection());
		return new ResponseEntity<>(bdao.insertBill(bill), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<? extends Object> update(@RequestBody Bill bill) throws Exception {
		BillDAO bdao = new BillDAO(dataSource.getConnection());
		return new ResponseEntity<>(bdao.updateBill(bill), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<? extends Object> delete(@PathVariable(value = "id") int billNo) throws Exception {
		logger.info(" Bill No : " + billNo);
		BillDAO bdao = new BillDAO(dataSource.getConnection());
		return new ResponseEntity<>(bdao.deleteBill(billNo), HttpStatus.OK);
	}

}
