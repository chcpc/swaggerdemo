package com.chcpc.swaggerssm.service.impl;

import com.chcpc.swaggerssm.dao.CustomerDao;
import com.chcpc.swaggerssm.entity.Customer;
import com.chcpc.swaggerssm.entity.PageBean;
import com.chcpc.swaggerssm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CustomerServiceImpl implements CustomerService {
	//1.注解注入域属性值--按类型
	@Autowired
	private CustomerDao customerDao;
	
	public CustomerDao getCustomerDao() {
		return customerDao;
	}
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public PageBean<Customer> getCustomersConditionByPage(int pageIndex, int pageSize, Customer customer) {
		//	取筛选信息总条数
		int totalRecored = customerDao.selectCustomersConditionByPageTotalRecored(customer);
		//	获取筛选用户集List
		List<Customer> customers = customerDao.selectCustomersConditionByPage((pageIndex-1)*pageSize, pageSize, customer);
		//	将所有信息封装进PageBean当中
		PageBean<Customer> pageBean=new PageBean<Customer>();
		pageBean.setBeanList(customers);
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalRecored(totalRecored);
		//	该方法设置起始页和末页
		pageBean.setPageBeginAndEnd();
		System.out.println(pageBean);
		return pageBean;
	}
	@Override
	public void deleteCustomerByCid(String cid) {
		customerDao.deleteCustomerByCid(cid);

	}
	@Override
	public Customer getCustomerByCid(String cid) {
		Customer customer = customerDao.getCustomerByCid(cid);
		return customer;
	}
	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}
	
}
