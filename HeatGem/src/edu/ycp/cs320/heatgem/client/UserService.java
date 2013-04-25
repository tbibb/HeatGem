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
	
	public UserProfile getUserProfile(String username, String password, String email, int level, int experience, int losses, int wins, int highScore);
	
	public Boolean updateUserProfile(String username, UserProfile updatedProfile);
	
	public UserProfile leaderBoard(String username, int level, int experience, int losses, int wins, int highScore);
}
