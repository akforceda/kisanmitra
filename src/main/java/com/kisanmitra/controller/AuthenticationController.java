package com.kisanmitra.controller;





import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import com.kisanmitra.connection.MyConnection;
import com.kisanmitra.dao.AuthenticationDao;
import com.kisanmitra.dto.User;
import com.kisanmitra.service.AuthenticationService;
import com.kisanmitra.service.AuthenticationServiceImplementation;


@Controller
public class AuthenticationController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	private AuthenticationService authenticationService =new AuthenticationServiceImplementation() ;
	
	@GetMapping("/signin")
	public ModelAndView signinPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		if(request.getSession().getAttribute("UserName")!=null) {
			Integer i = (Integer)request.getSession().getAttribute("RollID");
			if(i==3) 
			{
					mv.setViewName("admin");
				
			}
			else if(i==1) 
			{
				mv.setViewName("farmer");
			}
			else if(i==2) 
			{
				mv.setViewName("customer");
			}
			
			else {
				mv.setViewName("farmerAndcustomer");
			}
			
		}else
		    mv.setViewName("signin");
		
		return mv;
	}
	
	
	
	@PostMapping("/signin")
	public ModelAndView validateUser(@Valid User user, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		try {
			MyConnection obj = new MyConnection();
			obj.setJdbcTemplate(jdbcTemplate);
			
			User dbuser = authenticationService.loginUser(user);
		
		if(dbuser!=null) {
			request.getSession().setAttribute("UserName",dbuser.getUserId());
			request.getSession().setAttribute("RollID",dbuser.getRoleId());
			if(dbuser.getRoleId()==3) 
			{
					mv.setViewName("admin");
				
			}
			
			else if(dbuser.getRoleId()==1) 
			{
				mv.setViewName("farmer");
			}
			else if(dbuser.getRoleId()==2) 
			{
				mv.setViewName("customer");
			}
			else {
				mv.setViewName("farmerAndcustomer");
			}
		}
		else
		    mv.setViewName("signin");
	 
		return mv;
		
		} catch(Exception e) 
		{
			mv.setViewName("signin");
		}
		
		return mv;
		
	}
	
	
	@GetMapping("/signup")
	 
	public ModelAndView signUpPage() {
		MyConnection obj = new MyConnection();
		obj.setJdbcTemplate(jdbcTemplate);
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signup");
		
		
		return mv;
	}
	
	
	@PostMapping("/signup")
	public ModelAndView registerUserToDb(User user) {
		
		boolean b = authenticationService.createUser(user);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signup");
		
		
		return mv;
	}
	
	@GetMapping("/signout")
	public ModelAndView signOut(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		request.getSession().invalidate();
		mv.setViewName("signin");
		return mv;
	}
	
	@GetMapping("/farmerhomepage")
	public ModelAndView farmerhome(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("farmer");
		return mv;
	}
	
	@GetMapping("/customerhomepage")
	public ModelAndView customer(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("customer");
		return mv;
	}
	
	
	
}