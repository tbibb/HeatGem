package edu.ycp.cs320.heatgem.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.ycp.cs320.heatgem.client.UserService;
import edu.ycp.cs320.heatgem.shared.User;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
	private static final long serialVersionUID = 1L;

	@Override
	public User logIn(String username, String password) {
		return DB.instance().logIn(username, password);
	}
	
	@Override
	public void addUser(String username, String password, String confirmPassword, String email){
		
		
	}

}