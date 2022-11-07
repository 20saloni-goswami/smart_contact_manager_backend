package com.example.smart_contact_manager;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestController {
	
	@Autowired 
	TestService testService;
	
	// sign up
	@PostMapping("/signup")
	public Map signup(User user) {
		return testService.save(user);
	}
	
	// login
	@PostMapping("/login")
	public Map login(User user) {
		return testService.login(user);
	}
	 
	// forget Password
	@PostMapping("/forgetpasword")
	public Map forgetPassword(User user) throws ClassNotFoundException, SQLException {
		return testService.forgetPassword(user);
	}
	
	//add Contact
	
	@PostMapping("/addContact")
	public Map addContact(User user) throws ClassNotFoundException, SQLException
	{
		return testService.addContact(user);
	}
	
	// showing all the contacts 
	
	@GetMapping("/showContact")
	public List<User> showContact() throws ClassNotFoundException, SQLException
	{
		return testService.getContact();
	}
	
	// Delete contact
	@PostMapping("/deleteContact")
	public Map deleteContact(String mobilenumber) throws ClassNotFoundException, SQLException
	{
		return testService.delete(mobilenumber);
	}
	
	// Update contacts
	
	@PostMapping("/updateContact")
	public Map updateContact(User user) throws ClassNotFoundException, SQLException
	{
		return testService.updateContact(user);
	}
	
	// feedback api
	
	@PostMapping("/feedback")
	public Map feedbackForm(User user) throws ClassNotFoundException, SQLException
	{
		return testService.feedbackForm(user);
	}
	}

