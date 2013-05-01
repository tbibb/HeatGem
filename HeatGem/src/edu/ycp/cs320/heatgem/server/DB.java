package edu.ycp.cs320.heatgem.server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
//	private static IDatabase theInstance = new FakeDatabase();
	private static IDatabase theInstance = new DerbyDatabase();
	
	public static IDatabase instance() {
		return theInstance;
	}
	
	public static void closeQuietly(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// FIXME: should log
			}
		}
	}

	public static void closeQuietly(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// FIXME: should log
			}
		}
	}
	
}