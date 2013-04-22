package edu.ycp.cs320.heatgem.server;

import edu.ycp.cs320.heatgem.shared.User;
import edu.ycp.cs320.heatgem.shared.UserProfile;

public interface IDatabase {
	/**
	 * Look up a User object by username and password.
	 * 
	 * @param username the username
	 * @param password the password
	 * @return the User object, or null if there is no such user (or password is incorrect)
	 */
	public User logIn(String username, String password);
	
	public void addUser(String username, String password, String confirmPassword,
			String email);
	
	public UserProfile getUserProfile(String username);
	
	public boolean updateUserProfile(String username, UserProfile updatedProfile);
}