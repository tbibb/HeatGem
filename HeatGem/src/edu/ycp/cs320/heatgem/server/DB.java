package edu.ycp.cs320.heatgem.server;

public class DB {
	private static IDatabase theInstance = new FakeDatabase();
	
	public static IDatabase instance() {
		return theInstance;
	}
}
