package edu.ycp.cs320.heatgem.server;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.heatgem.shared.User;

public class FakeDatabase implements IDatabase {
	private List<User> userList;
	
	public FakeDatabase() {
		userList = new ArrayList<User>();
		
		// Create example users
		User user = new User();
		user.setUsername("alice");
		user.setPassword("abc");
		
		User user2 = new User();
		user2.setUsername("bob");
		user2.setPassword("xyz");
		
		userList.add(user);
		userList.add(user2);
	}

	@Override
	public User logIn(String username, String password) {
		for (User user : userList) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	@Override
	public void addUser(String username, String password,String confirmPassword, String email) {
		// TODO: implement
		
	}

}
