package com.luv2code.springdemo.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory; 

	@Override
	public List<Customer> getCustomers() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by last_name", Customer.class);
		
		
		
		return theQuery.getResultList();
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(customer);	
	}

	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(Customer.class,theId);	
	
		
	}

	@Override
	public void delete(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
		
		
	}

	@Override
	public List<Customer> searchByFirstAndLastName(String theSearchName) {
		if(theSearchName==null || theSearchName.trim().length()==0) {
			return Collections.emptyList();
		}
		
		Query<Customer> theQuery = null;
		Session currentSession = sessionFactory.getCurrentSession();
		theSearchName = theSearchName.trim();
		if(theSearchName.contains(" ")) {
			
			theSearchName =  theSearchName.replaceAll(" ","");
			theQuery = currentSession.createQuery("from Customer "
					+ "where lower(concat(first_name,last_name))=:theSearchName or "
					+ "lower(concat(last_name,first_name))=:theSearchName",Customer.class);
			theQuery.setParameter("theSearchName", theSearchName.toLowerCase());
			return theQuery.getResultList();
		}else {
			theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
            return theQuery.getResultList();
		}
		
		
		
	}

}
