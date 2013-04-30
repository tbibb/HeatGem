package edu.ycp.cs320.heatgem.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.heatgem.shared.User;
import edu.ycp.cs320.heatgem.shared.UserProfile;
import edu.ycp.cs320.heatgem.server.DB;
import edu.ycp.cs320.heatgem.server.ITransaction;
import edu.ycp.cs320.heatgem.server.FakeDatabase;

public class DerbyDatabase implements IDatabase {
	private static final String DATASTORE = "/home/heatgemdb";
	
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new RuntimeException("Could not load Derby JDBC driver");
		}
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
	
	void createTables() throws SQLException {
		databaseRun(new ITransaction<Boolean>() {
			@Override
			public Boolean run(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"create table users (" +
							"  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
							"  username VARCHAR(50) NOT NULL, " +
							"  password VARCHAR(50) NOT NULL, " +
							"  highscore INT, " + 
							"  email VARCHAR(50) NOT NULL, " + 
							"  experience INT, " + 
							"  level INT NOT NULL, " + 
							"  losses INT, " + 
							"  wins INT, " +
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

	@Override
	public User logIn(final String username, final String password) {
//	public User logIn(final String username, final String password) throws SQLException {
		// idk what i'm doing :D
//		return databaseRun(new ITransaction<User> () {
//			@Override
//			public User run(Connection conn) throws SQLException {
//				PreparedStatement stmt = null;
//				ResultSet resultSet = null;
//				
//				try {
//					stmt = conn.prepareStatement("select * from users");
//					resultSet = stmt.executeQuery();
//					
//					User result = new User();
//					
//					while (resultSet.next()) {
//						User current = new User();
//						
//						if (username.equals(current)) {
//							if (password.equals(current)) {
//								//allow login, return true/valid?
//								break;
//							}
//						}
//						
//						result = current;
//					}
//					
//					return result;
//				} finally {
//					DB.closeQuietly(stmt);
//					DB.closeQuietly(resultSet);
//				}
//			}
//		});
		return null;
	}

	@Override
	public void addUser(String username, String password,
			String confirmPassword, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserProfile getUserProfile(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUserProfile(String username, UserProfile updatedProfile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserProfile findUserProfileByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAmountUsers() {
		// TODO Auto-generated method stub
		return 0;
	}
	
//	@Override
//	public OrderReceipt placeOrder(final Order order) throws SQLException {
//		return databaseRun(new ITransaction<OrderReceipt>() {
//			@Override
//			public OrderReceipt run(Connection conn) throws SQLException {
//				PreparedStatement stmt = null;
//				ResultSet generatedKeys = null;
//				
//				OrderReceipt receipt = new OrderReceipt();
//				receipt.setUserInfo(order.getUser());
//				receipt.setPrice(order.getPrice());
//				
//				try {
//					
//					stmt = conn.prepareStatement(
//							"insert into order_receipts (userinfo, price) values (?, ?)",
//							PreparedStatement.RETURN_GENERATED_KEYS
//					);
//					
//					stmt.setString(1, receipt.getUserInfo());
//					stmt.setBigDecimal(2, receipt.getPrice());
//					
//					stmt.executeUpdate();
//					
//					generatedKeys = stmt.getGeneratedKeys();
//					if (!generatedKeys.next()){
//						throw new SQLException("Could not get generated key for order receipt");
//					}
//					receipt.setId(generatedKeys.getInt(1));
//					
//					return receipt;
//					
//				} finally {
//					DB.closeQuietly(stmt);
//				}
//			}
//		});
//	}
	
//	@Override
//	public List<OrderReceipt> getOrderReceipts() throws SQLException {
//		return databaseRun(new ITransaction<List<OrderReceipt>>() {
//			@Override
//			public List<OrderReceipt> run(Connection conn) throws SQLException {
//				PreparedStatement stmt = null;
//				ResultSet resultSet = null;
//				
//				try {
//					stmt = conn.prepareStatement("select * from order_receipts");
//					resultSet = stmt.executeQuery();
//					
//					List<OrderReceipt> result = new ArrayList<OrderReceipt>();
//					
//					while (resultSet.next()) {
//						OrderReceipt receipt = new OrderReceipt();
//						
//						receipt.setId(resultSet.getInt(1));
//						receipt.setUserInfo(resultSet.getString(2));
//						receipt.setPrice(resultSet.getBigDecimal(3));
//						
//						result.add(receipt);
//					}
//					
//					
//					return result;
//				} finally {
//					DB.closeQuietly(stmt);
//					DB.closeQuietly(resultSet);
//				}
//			}
//		});
//	}
}
