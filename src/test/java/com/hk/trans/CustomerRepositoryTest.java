/**
 * 
 */
package com.hk.trans;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.hk.trans.dao.CustomerRepository;
import com.hk.trans.model.Customer;

/**
 * @author harikiran
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository repository;

	@Test
	public void testFindByName() {

		entityManager.persist(new Customer(1L, "Hari", "hari.chebrol@gmail.com", new Date()));

		List<Customer> customers = repository.findByEmail("hari.chebrol@gmail.com");
		assertEquals(1, customers.size());

		assertThat(customers).extracting(Customer::getName).containsOnly("Hari");

	}

}
