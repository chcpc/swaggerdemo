package com.chcpc.swaggerssm.service;


import com.chcpc.swaggerssm.entity.Customer;
import com.chcpc.swaggerssm.entity.PageBean;

public interface CustomerService {
	public PageBean<Customer> getCustomersConditionByPage(int pageIndex, int pageSize, Customer customer);

	public void deleteCustomerByCid(String cid);

	public Customer getCustomerByCid(String cid);

	public void updateCustomer(Customer customer);

}
