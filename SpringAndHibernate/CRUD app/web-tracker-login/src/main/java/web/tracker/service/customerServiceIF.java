package web.tracker.service;

import java.util.List;

import web.tracker.entity.Customer;

public interface customerServiceIF {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomer(String theParam);


}
