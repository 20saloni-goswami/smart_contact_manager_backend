package com.example.smart_contact_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {

	// Sign Up 
	public Map save(User user) {
		String name = user.getName();
		String emailid = user.getEmailid();
		String password = user.getPassword();
		String mobilenumber = user.getMobilenumber();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_contact_manager","root","1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = "select * from registration where emailId = '"+emailid+"'";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HashMap map = new HashMap<>();
		try {
			if(rs.next()) {
				map.put("Message","you are already signed up do login");
				return map;
			}
			else {
				String query1 = "insert into registration values(?,?,?,?)";
				PreparedStatement pstmt1 = con.prepareStatement(query1);
				pstmt1.setString(1, name);
				pstmt1.setString(2, emailid);
				pstmt1.setString(3, password);
				pstmt1.setString(4, mobilenumber);
				int i = pstmt1.executeUpdate();
					if(i>=1) {
						map.put("Message", "You are signed-up sucessfully");
						return map;
					}
					else {
						map.put("Messege", "Something went wrong try again later");
						return map;
					}
					}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Log in
	public Map login(User user) {
		String emailid = user.getEmailid();
		String password = user.getPassword();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_contact_manager","root","1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = "Select password from registration where emailid='"+emailid+"'";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HashMap map = new HashMap();
		try {
			if(rs.next()) {
				try {
					if(rs.getString("password").equals(password))
					{
						map.put("Message","Password Correct login successfully");
						return map;
					}
					else {
						map.put("Message", "Password Wrong Kindly Update your Password");
						return map;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				map.put("Message", "You are not registered Yet do register before login");
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Forget Password
	public Map forgetPassword(User user) throws ClassNotFoundException, SQLException {
		String emailid = user.getEmailid();
		String password = user.getPassword();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_contact_manager","root","1234");
		Statement stmt = con.createStatement();
		String query = "Select password from registration where emailid='"+emailid+"'";
		ResultSet rs = stmt.executeQuery(query);
		HashMap map = new HashMap();
		if(rs.next()) {
			if(rs.getString("password").equals(password)) {
				map.put("Message","your password is correct something went wrong");
				return map;
			}
			else {
				String query1 = "update registration set password=? where emailid='"+emailid+"'";
				PreparedStatement pstmt = con.prepareStatement(query1);
				pstmt.setString(1, password);
				int i = pstmt.executeUpdate();
				if(i>=1) {
					map.put("Message","your password updated successfully do login");
					return map;
				}
				else {
					map.put("Message", "Something went wrong try again later");
					return map;
				}
			}
		}
		return null;
	}

	// add Contact api
	public Map addContact(User user) throws ClassNotFoundException, SQLException {
		String name=user.getName();
		String emailid=user.getEmailid();
		String mobilenumber=user.getMobilenumber();
		String work=user.getWork();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_contact_manager","root","1234");	
		String query ="insert into contact (name,emailid,mobilenumber,work) values(?,?,?,?)";
		PreparedStatement stmt= con.prepareStatement(query);
		stmt.setString(1,name);
		stmt.setString(2,emailid);
		stmt.setString(3,mobilenumber);
		stmt.setString(4,work);
		int i=stmt.executeUpdate();
		HashMap map= new HashMap<>();
		System.out.println(mobilenumber);
		if(i>=1)
		{
		     map.put("Message", "Your Contact is added successfully");
			 return map;
		}
		else
			{
				map.put("Message", "Something went wrong, please try again");
				return map;
		}
	
	}

	
	// Showing Contact Api
	
	public List getContact() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_contact_manager", "root", "1234");
		Statement stmt=con.createStatement();
		String query= "Select * from contact";
		ResultSet rs=stmt.executeQuery(query);
		List<User> list;
		list=new ArrayList<>();
		while(rs.next()) {
			User user=new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmailid(rs.getString("emailid"));
			user.setMobilenumber(rs.getString("mobilenumber"));
			user.setWork(rs.getString("work"));
			list.add(user);
		}
		return list;
	}

	// delete Contact Api
	
	public Map delete(String mobilenumber) throws ClassNotFoundException, SQLException {
		System.out.println(mobilenumber);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_contact_manager", "root", "1234");	
		String query ="delete from contact where mobilenumber="+mobilenumber+"";
		PreparedStatement stmt= con.prepareStatement(query);
		int i=stmt.executeUpdate();
		HashMap map= new HashMap<>();
		if(i>=1)
		{
		     map.put("Message", "Your Contact is deleted successfully");
			 return map;
		}
		else
		{
			map.put("Message", "Something went wrong, please try again");
		    return map;
		}
	}

	// updateContact Api
	
	public Map updateContact(User user) throws ClassNotFoundException, SQLException {

		int id=user.getId();
		String name=user.getName();
		String emailid=user.getEmailid();
		String mobilenumber=user.getMobilenumber();
		String work=user.getWork();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_contact_manager", "root", "1234");	
		String query ="update contact set name=?, emailid=?,mobilenumber=?,work=? where mobilenumber="+mobilenumber+"";
		PreparedStatement stmt= con.prepareStatement(query);
//		stmt.setInt(1,id);
		stmt.setString(1,name);
		stmt.setString(2,emailid);
		stmt.setString(3,mobilenumber);
		stmt.setString(4,work);
		int i=stmt.executeUpdate();
		HashMap map= new HashMap<>();
		if(i>=1)
		{
		     map.put("Message", "Your Contact is updated successfully");
			 return map;
		}
		else
			{
				map.put("Message", "Something went wrong, please try again");
				return map;
		}
	}

	// feedback api
	
	public Map feedbackForm(User user) throws ClassNotFoundException, SQLException{
		   String emailid=user.getEmailid();
		   String feed=user.getFeed();
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_contact_manager", "root", "1234");	
		   String query ="insert into feedback (emailid,feed) values(?,?)";
		   PreparedStatement pstmt= con.prepareStatement(query);
		   pstmt.setString(1,emailid);
		   pstmt.setString(2, feed);
		   int i=pstmt.executeUpdate();
		   HashMap map=new HashMap();
		   if(i>=1)
		   {
			   map.put("Message", "your feedback is submitted successfully!! Thank you for your submission.");
		   }
		   else
		   {
			   map.put("Message", "OOps, something went wrong please, try again");
		   }
	   return map;	   
	}
}



