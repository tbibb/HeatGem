package edu.ycp.cs320.heatgem.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.heatgem.shared.User;
import edu.ycp.cs320.heatgem.server.DB;
import edu.ycp.cs320.heatgem.server.ITransaction;
import edu.ycp.cs320.heatgem.server.FakeDatabase;

public class FakeDatabase implements IDatabase {
	private List<User> userList;
	
	private static final String DATASTORE = "/home/kpike/heatgemdb";
	
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

	private class DatabaseConnection {
		public Connection conn;
		public int refCount;
	}
	
	private final ThreadLocal<DatabaseConnection> connHolder = new ThreadLocal<DatabaseConnection>();
	
	private DatabaseConnection getConnection() throws SQLException {
		DatabaseConnection dbConn = connHolder.get();
		if (dbConn == null) {
			dbConn = new DatabaseConnection();
			dbConn.conn = DriverManager.getConnection("jdbc:derby:" + DATASTORE + ";create=true");
			dbConn.refCount = 0;
			connHolder.set(dbConn);
		}
		dbConn.refCount++;
		return dbConn;
	}
	
	private void releaseConnection(DatabaseConnection dbConn) throws SQLException {
		dbConn.refCount--;
		if (dbConn.refCount == 0) {
			try {
				dbConn.conn.close();
			} finally {
				connHolder.set(null);
			}
		}
	}
	
	
	private<E> E databaseRun(ITransaction<E> transaction) throws SQLException {
		// FIXME: retry if transaction times out due to deadlock
		
		DatabaseConnection dbConn = getConnection();
		
		try {
			boolean origAutoCommit = dbConn.conn.getAutoCommit();
			try {
				dbConn.conn.setAutoCommit(false);
				
				E result = transaction.run(dbConn.conn);
				dbConn.conn.commit();
				return result;

			} finally {
				dbConn.conn.setAutoCommit(origAutoCommit);
			}
		} finally {
			releaseConnection(dbConn);
		}
	}
	
	
	public void createTables() throws SQLException {
		
			databaseRun(new ITransaction<Boolean>() {
				@Override
				public Boolean run(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					
					try {
						stmt = conn.prepareStatement(
								//FIX ME
								
								"create table add_user (" +
								
								")"
						);
						
						stmt.executeUpdate();
					} finally {
						DB.closeQuietly(stmt);
					}
					
					return true;
				}
			});
		}
		
		
	}


