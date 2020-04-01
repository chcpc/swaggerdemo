package com.chcpc.swaggerssm.controller;

import com.chcpc.swaggerssm.entity.Customer;
import com.chcpc.swaggerssm.entity.PageBean;
import com.chcpc.swaggerssm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class CustomerController {
	@Autowired
	CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	@RequestMapping("/deleteCustomerByCid.do")
	public ModelAndView deleteCustomerByCid(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		try {
			String cid = request.getParameter("cid");		
			customerService.deleteCustomerByCid(cid);
			mv.setViewName("redirect:/getCustomersByConditionPage.do");
			return mv;
		}catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("/msg.jsp");
			return mv;
		}
	}
	
	@RequestMapping("/getCustomerByCid.do")
	public ModelAndView showByCid(HttpServletRequest request, HttpServletResponse response){
		System.out.println("进入");
		ModelAndView mv=new ModelAndView();
		try {
			String cid = request.getParameter("cid");
			Customer customer = customerService.getCustomerByCid(cid);
			if(customer!=null){
	
				mv.addObject(customer);
				mv.setViewName("/jsp/customerModify.jsp");			
			}
			return mv;
		}catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("/msg.jsp");
			return mv;
		}
	}
	@RequestMapping("/updateCustomer.do")
	public ModelAndView updateCustomer(HttpServletRequest request, 
			HttpServletResponse response ,Customer customer) 
			throws ServletException, IOException {
		ModelAndView mv=new ModelAndView();
		try {
			customerService.updateCustomer(customer);
			mv.setViewName("redirect:/getCustomersByConditionPage.do");
			return mv;
		}catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("/msg.jsp");
			return mv;
		}
	}
	@RequestMapping("/getCustomersByConditionPage.do")
	public ModelAndView getCustomersByConditionPage(HttpServletRequest request, 
			HttpServletResponse response ,Customer customer) 
			throws ServletException, IOException {
		System.out.println("ces1");
		ModelAndView mv=new ModelAndView();
		try {
			int pageIndex=getPageIndex(request);
			int pageSize=10;
			PageBean<Customer> pb = customerService.getCustomersConditionByPage(pageIndex, pageSize, customer);
//			System.out.println("Controller--pb111="+pb);
			pb.setUrl(getUrl(request));
//			System.out.println("Controller--pb222="+pb);
			mv.addObject("pb", pb);
			mv.addObject("customer", customer);
			mv.setViewName("/jsp/list.jsp");
			return mv;
		}catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("/msg.jsp");
			return mv;
		}
	}
	private String getUrl(HttpServletRequest request) {
		//	获取request中的项目请求路径
		//	获取
		String contextPath = request.getContextPath();
		System.out.println("conetexrPath:"+contextPath);
		String servletPath = request.getServletPath();
		System.out.println("servletPath:"+servletPath);
		String queryString = request.getQueryString();
		System.out.println("queryString:"+queryString);
		if( queryString!=null && queryString.indexOf("&pageIndex=")!=-1) {
			queryString=queryString.substring(0, queryString.indexOf("&pageIndex="));
		}
		System.out.println("getURL-----"+(contextPath+servletPath+"?"+queryString));
		return contextPath+servletPath+"?"+queryString;
	}
	
	private int getPageIndex(HttpServletRequest request) {
		if(request.getParameter("pageIndex")==null || request.getParameter("pageIndex").equals("")) {
			return 1;
		}
		return Integer.parseInt(request.getParameter("pageIndex"));
	}
	@RequestMapping("/test.do")
	public String ce(HttpServletRequest request, HttpServletResponse response){
		System.out.println("cew");
		return "ces1";
	}
	
}
