package edu.ycp.cs320.heatgem.server;

import java.sql.SQLException;


public class CreateTables {
	
	public static void main(String[] args) throws SQLException {
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		db.addUser("alice", "abc", "abc", "alice@ycp.edu");
		
		System.out.println("Successfully created tables");
		//le comment
	}

}