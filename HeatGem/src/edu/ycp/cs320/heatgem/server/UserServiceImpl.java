package edu.ycp.cs320.heatgem.server;


import java.sql.SQLException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.ycp.cs320.heatgem.client.UserService;
import edu.ycp.cs320.heatgem.shared.User;
import edu.ycp.cs320.heatgem.shared.UserProfile;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
	private static final long serialVersionUID = 1L;

	@Override
	public User logIn(String username, String password) {
		try {
			return DB.instance().logIn(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void addUser(String username, String password, String confirmPassword, String email){
		DB.instance().addUser(username, password, confirmPassword, email);
		
	}
	
//	@Override
//	public void updateBoard() {
//		
//	}

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public UserProfile getUserProfile(String username) {
		return DB.instance().getUserProfile(username);
	}
	
	@Override
	public Boolean updateUserProfile(String username, UserProfile updatedProfile) {
		return DB.instance().updateUserProfile(username, updatedProfile);
	}

	@Override
	public UserProfile findUserProfileByUserId(int id) {
		return DB.instance().findUserProfileByUserId(id);
	}

	@Override
	public int getAmountUsers() {
		// TODO Auto-generated method stub
		return DB.instance().getAmountUsers();
	}

	@Override
	public Boolean deleteUserAccount(String username) {
		// TODO Auto-generated method stub
		return DB.instance().deleteUser(username);
	}

	@Override
	public Boolean uniqueUser(String username) {
		// TODO Auto-generated method stub
		return DB.instance().uniqueUser(username);
	}
}

