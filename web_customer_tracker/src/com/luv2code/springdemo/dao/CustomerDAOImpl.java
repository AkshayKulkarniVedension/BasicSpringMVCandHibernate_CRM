package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create the query
		
		Query <Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute the query and get the result list
		
		List <Customer> customers = query.getResultList();
		// return the list of customers
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theCustomer);
		// save the customer to the database
		
	}

	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer customer = currentSession.get(Customer.class, theId);
		
		return customer;
	}

	@Override
	public void delete(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer customer = currentSession.get(Customer.class, theId);
		
		currentSession.delete(customer);
		
	}


}
