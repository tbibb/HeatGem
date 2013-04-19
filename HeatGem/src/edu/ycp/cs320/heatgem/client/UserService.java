package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.ycp.cs320.heatgem.shared.User;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
	public User logIn(String username, String password);
	
	public void addUser(String username, String password, String confirmPassword, String email);
	
	//public void updateBoard();
}
