package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.ycp.cs320.heatgem.shared.User;
import edu.ycp.cs320.heatgem.shared.UserProfile;

public interface UserServiceAsync {

	void logIn(String username, String password, AsyncCallback<User> callback);

	void addUser(String username, String password, String confirmPassword,
			String email, AsyncCallback<Void> callback);

	void deleteUserAccount(String username, AsyncCallback<Void> callback);

	void getUserProfile(String username, String password, String email, int level, int experience, int losses, int wins, int highScore, AsyncCallback<UserProfile> callback);

	void updateUserProfile(String username, UserProfile updatedProfile,
			AsyncCallback<Boolean> callback);

	void leaderBoard(String username, int level, int experience,
			int losses, int wins, int highScore,
			AsyncCallback<UserProfile> callback);
	
	//void updateBoard();

}
