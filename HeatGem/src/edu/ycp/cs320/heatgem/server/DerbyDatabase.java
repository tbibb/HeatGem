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
					result.setHighScore(resultSet.getInt(4));
					result.setEmail(resultSet.getString(5));
					result.setExperience(resultSet.getInt(6));
					result.setLevel(resultSet.getInt(7));
					result.setLosses(resultSet.getInt(8));
					result.setWins(resultSet.getInt(9));
					
					return result;
					
				} finally {
					DB.closeQuietly(stmt);
					DB.closeQuietly(resultSet);
				}
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
					PreparedStatement stmt2 = null;
					ResultSet resultSet = null;
					
					try {
						stmt = conn.prepareStatement("select * from users where username = ?");
						stmt.setString(1, username);
						resultSet = stmt.executeQuery();
						
						if (!resultSet.next()) {
							// no such user
							System.out.println("Unique user, add to DB!");
							stmt2 = conn.prepareStatement(
									"insert into users(username, password, highscore, email, exp, level, losses, wins) " +
									"values (?, ?, 0, ?, 0, 1, 0, 0)"
							);

							stmt2.setString(1, username);
							stmt2.setString(2, password);
							stmt2.setString(3, email);
							stmt2.executeUpdate();

							return true;
						} else {
							System.out.println("User already exists!");

							return false;
						}
						
//						If you need to check for the existence of a user in a particular database before creating them, then you can do this:
//
//							USE your_db_name
//
//							IF NOT EXISTS
//							    (SELECT name
//							     FROM sys.database_principals
//							     WHERE name = 'Bob')
//							BEGIN
//							    CREATE USER [Bob] 
//							END
//						
						//or use this to check if there is a user using that username already
//						IF NOT EXISTS (SELECT * FROM users WHERE username = @username)
//					    INSERT INTO users (username) VALUES (@username);
//					ELSE
//					    RAISERROR 'whatever';
						
						//After an hour, I found out Derby apparently doesn't support IF or RAISERROR :sadface:
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
					UserProfile result = new UserProfile();
					
					try {
						stmt = conn.prepareStatement("select * from users where username = ?");
						stmt.setString(1, username);
						resultSet = stmt.executeQuery();
						
						if (!resultSet.next()) {
							// no such user
							return null;
						}
						
						result.setUserId(resultSet.getInt(1));
						result.setName(resultSet.getString(2));
						result.setHighScore(resultSet.getInt(4));
						result.setEmail(resultSet.getString(5));
						result.setExperience(resultSet.getInt(6));
						result.setLevel(resultSet.getInt(7));
						result.setLosses(resultSet.getInt(8));
						result.setWins(resultSet.getInt(9));
						
						return result;
						
					} finally {
						DB.closeQuietly(stmt);
						DB.closeQuietly(resultSet);
					}
				}
			});
		} catch (SQLException e) {
			throw new RuntimeException("SQLException getting UserProfile", e);
		}
		return null;
	}

	@Override
	public boolean updateUserProfile(final String username, final UserProfile updatedProfile) {
		// TODO Auto-generated method stub
		
		try {
			databaseRun(new ITransaction<UserProfile>() {
				@Override
				public UserProfile run(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					UserProfile result = null;
					
					try {
//						UPDATE table_name
//						SET column1=value, column2=value2,...
//						WHERE some_column=some_value
//						
//						stmt = conn.prepareStatement("select * from users where username = ? and password = ?");
						
						stmt = conn.prepareStatement(
								"UPDATE users " +
								"SET column4=?, column6=?, column7=?, column8=?, column9=? " + 
								"WHERE username = ?"
						);
						
						stmt.setInt(1, updatedProfile.getHighScore());
						stmt.setInt(2, updatedProfile.getExperience());
						stmt.setInt(3, updatedProfile.getLevel());
						stmt.setInt(4, updatedProfile.getLosses());
						stmt.setInt(5, updatedProfile.getWins());
						stmt.setString(6, username);
						
						resultSet = stmt.executeQuery();
						
						if (!resultSet.next()) {
							// no such user
							return null;
						}
						
						result = updatedProfile;
						
					} finally {
						DB.closeQuietly(stmt);
						DB.closeQuietly(resultSet);
					}
					return result;
				}
			});
		} catch (SQLException e) {
			throw new RuntimeException("SQLException getting UserProfile", e);
		}
		
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
//	public boolean uniqueUser(final String username) {
//		// TODO Auto-generated method stub
//		// pull information from database, compare with given name
//		// return true if user does not exist
//		
//		try {
//			databaseRun(new ITransaction<Boolean>() {
//				@Override
//				public Boolean run(Connection conn) throws SQLException {
//					PreparedStatement stmt = null;
//					
//					try {
//						stmt = conn.prepareStatement("select username from users where username = ?");
//						stmt.setString(1, username);
//						
//						stmt.executeUpdate();
//						
//						return true;
//					} finally {
//						DB.closeQuietly(stmt);
//					}
//				}
//			});
//		} catch (SQLException e) {
//			throw new RuntimeException("SQLException unique user", e);
//		}
//		
//		return false;
//	}
	
}
