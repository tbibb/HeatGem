package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.ycp.cs320.heatgem.shared.User;
import edu.ycp.cs320.heatgem.shared.UserProfile;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
	public User logIn(String username, String password);
	
	public void addUser(String username, String password, String confirmPassword, String email);
	
	public void deleteUserAccount(String username);
	
	public UserProfile getUserProfile(String username);
	
	public UserProfile findUserProfileByUserId(int id);
	
	int getAmountUsers();
	
	public Boolean updateUserProfile(String username, UserProfile updatedProfile);
}
