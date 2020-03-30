package web.tracker.dao;

import java.util.List;

import web.tracker.entity.Customer;

public interface CustomerDAOIF {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theParam);


}
