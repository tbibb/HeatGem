package edu.ycp.cs320.heatgem.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.ycp.cs320.heatgem.shared.User;
import edu.ycp.cs320.heatgem.shared.UserProfile;
import edu.ycp.cs320.heatgem.server.DB;
import edu.ycp.cs320.heatgem.server.ITransaction;


public class DerbyDatabase implements IDatabase {
	private static final String DATASTORE = "H:/heatgemdb";
	
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
				/*
							"create table order_receipts (" +
							"  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
							"  userinfo VARCHAR(200) NOT NULL, " +
							"  price DECIMAL(10,2) " +
							")"
				 */
				PreparedStatement stmt = null;
				
				try {
					// TODO: create a unique index on the username column
					// to avoid having two users with the same username
					stmt = conn.prepareStatement(
							"create table users (" +
							"  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
							"  username VARCHAR(50) NOT NULL, " +
							"  password VARCHAR(50) NOT NULL, " +
							"  highscore INT, " + 
							"  email VARCHAR(50) NOT NULL, " + 
							"  exp INT, " + 
							"  level INT NOT NULL, " + 
							"  losses INT, " + 
							"  wins INT " +
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
	public User logIn(final String username, final String password) throws SQLException {
		return databaseRun(new ITransaction<User> () {
			@Override
			public User run(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				User result = new User();
				
				try {
					stmt = conn.prepareStatement("select * from users where username = ? and password = ?");
					stmt.setString(1, username);
					stmt.setString(2, password);
					resultSet = stmt.executeQuery();
					
					if (!resultSet.next()) {
						// no such user/password
						return null;
					}
					
					result.setId(resultSet.getInt(1));
					result.setUsername(resultSet.getString(2));
					result.setPassword(resultSet.getString(3));
					// etc...
					
					return result;
					
					//result.setUsername(resultSet.getString(username));
					//result.setPassword(resultSet.getString(password));
					
					/*
					if (username.equals(result.getUsername())) {
						if (password.equals(result.getPassword())) {
							//successful retrieval of user information
						} else {
							//password incorrect
						}
					} else {
						//user name incorrect
					}
					*/
				} finally {
					DB.closeQuietly(stmt);
					DB.closeQuietly(resultSet);
				}
				//return user's information
				//return result;
			}
		});
	}

	@Override
	public void addUser(final String username, final String password,
			final String confirmPassword, final String email) {
		try {
			databaseRun(new ITransaction<Boolean>() {
				@Override
				public Boolean run(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					
					try {
						stmt = conn.prepareStatement(
								"insert into users(username, password, highscore, email, exp, level, losses, wins) " +
								"values (?, ?, 0, ?, 0, 1, 0, 0)"
						);
						stmt.setString(1, username);
						stmt.setString(2, password);
						stmt.setString(3, email);
						
						stmt.executeUpdate();
						
						return true;
					} finally {
						DB.closeQuietly(stmt);
					}
				}
			});
		} catch (SQLException e) {
			throw new RuntimeException("SQLException adding user", e);
		}
	}

	@Override
	public UserProfile getUserProfile(final String username) {
		// TODO Auto-generated method stub
		try {
			databaseRun(new ITransaction<UserProfile>() {
				@Override
				public UserProfile run(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					User result = new User();
					
					try {
						stmt = conn.prepareStatement("select * from users where username = ?");
						stmt.setString(1, username);
						resultSet = stmt.executeQuery();
						
						//return user Profile
					} finally {
						DB.closeQuietly(stmt);
						DB.closeQuietly(resultSet);
					}
					//return user's information
					//return result;
					return null;
				}
			});
		} catch (SQLException e) {
			throw new RuntimeException("SQLException getting UserProfile", e);
		}
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

	@Override
	public boolean deleteUser(final String username) {
		// delete user - deletes row in SQL database
		try {
			databaseRun(new ITransaction<Boolean>() {
				@Override
				public Boolean run(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					
					try {
						stmt = conn.prepareStatement(
								"DELETE FROM users " +
								"WHERE username=?"
						);
						stmt.setString(1, username);
						
						stmt.executeUpdate();
						
						return true;
					} finally {
						DB.closeQuietly(stmt);
					}
				}
			});
		} catch (SQLException e) {
			throw new RuntimeException("SQLException deleting user", e);
		}
		
		return false;
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
