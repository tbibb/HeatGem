package edu.ycp.cs320.heatgem.server;

import java.sql.SQLException;


public class CreateTables {
	
	public static void main(String[] args) throws SQLException {
		FakeDatabase db = new FakeDatabase();
		db.createTables();
		System.out.println("Successfully created tables");
		//le comment
	}

}