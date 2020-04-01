package com.chcpc.swaggerssm.dao;

import com.chcpc.swaggerssm.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomerDao {
	//	查询用户数量
	public int selectCustomersConditionByPageTotalRecored(@Param("customer") Customer customer);
	//	查询用户集合
	public List<Customer> selectCustomersConditionByPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("customer") Customer customer);
	//	表示层还是PageBean<Customer>，service的方法返回PageBean<T>，PageBean的装配在Service完成

	public void deleteCustomerByCid(String cid);
	
	public Customer getCustomerByCid(String cid);
	public void updateCustomer(@Param("customer") Customer customer);
}
