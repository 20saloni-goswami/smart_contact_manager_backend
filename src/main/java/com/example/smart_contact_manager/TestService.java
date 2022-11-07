package com.example.smart_contact_manager;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Autowired
	TestRepository testRepository;

	// Sign up api
	public Map save(User user) {
		Map map = testRepository.save(user);
		return map;
	}

	// log in api 
	public Map login(User user) {
		Map map = testRepository.login(user);
		return map;
	}

	// forget password api
	public Map forgetPassword(User user) throws ClassNotFoundException, SQLException {
		Map map = testRepository.forgetPassword(user);
		return map;
	}

	// add Contact api
	public Map addContact(User user) throws ClassNotFoundException, SQLException {
		Map map =testRepository.addContact(user);
		return map;
	}

	 // get Contact api
	public List<User> getContact() throws ClassNotFoundException, SQLException {
		List list = testRepository.getContact();
		return list;
	}

	// get delete api
	
	public Map delete(String mobilenumber) throws ClassNotFoundException, SQLException {
		Map map = testRepository.delete(mobilenumber);
		return map;
	}

	// get Update api
	
	
	public Map updateContact(User user) throws ClassNotFoundException, SQLException {
		Map map = testRepository.updateContact(user);
		return map;
	}

	// feedback api
	public Map feedbackForm(User user) throws ClassNotFoundException, SQLException {
		Map map = testRepository.feedbackForm(user);
		return map;
	}

}
