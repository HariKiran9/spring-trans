/**
 * 
 */
package com.hk.trans.dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hk.trans.model.Customer;

/**
 * @author harikiran
 * 
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	public List<Customer> findByEmail(String email);

	public List<Customer> findByDate(Date date);

	// custom query example and return a stream
	@Query("select c from Customer c where c.email = :email")
	public Stream<Customer> findByEmailReturnStream(@Param("email") String email);
}
