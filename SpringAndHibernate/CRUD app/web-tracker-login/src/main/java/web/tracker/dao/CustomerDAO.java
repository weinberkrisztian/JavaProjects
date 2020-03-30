package web.tracker.dao;

import java.text.Normalizer;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import web.tracker.entity.Customer;

@Repository
public class CustomerDAO implements CustomerDAOIF {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		
		Session customerSession=sessionFactory.getCurrentSession();
		
		Query<Customer> getCustomersQuery=customerSession.createQuery("from Customer order by lastName",Customer.class);
		
		List<Customer> customerList=getCustomersQuery.getResultList();
		
		
		return customerList;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session customerSession=sessionFactory.getCurrentSession();
		
		customerSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		Session customerSession=sessionFactory.getCurrentSession();
		
		Query<Customer> getCustomerQuery=customerSession.createQuery("from Customer where id=:theActualId",Customer.class);
		
		getCustomerQuery.setParameter("theActualId", theId);
		Customer customer=getCustomerQuery.getSingleResult();
		
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
	
		Session customerSession=sessionFactory.getCurrentSession();
		
		Query deleteQuery=customerSession.createQuery("delete from Customer where id=:theActualId");
		
		deleteQuery.setParameter("theActualId", theId);
		
		
		deleteQuery.executeUpdate();
		
		
		
		
		
	}

	@Override
	public List<Customer> searchCustomers(String theParam) {
		Session customerSession=sessionFactory.getCurrentSession();
		
		Query<Customer> customersQuery=customerSession.createQuery("from Customer where firstName LIKE :param OR lastName LIKE :param"
				+ " OR email LIKE :param", Customer.class);
		
		customersQuery.setParameter("param","%"+ theParam + "%");
		List <Customer> customers=customersQuery.getResultList();
		
		
		return customers;
	}
	
	



}
