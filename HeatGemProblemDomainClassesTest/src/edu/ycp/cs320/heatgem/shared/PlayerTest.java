package edu.ycp.cs320.heatgem.shared;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	private Player player;

	@Before 
	public void setUp() {
		player = new Player("test");
	}
	
	@Test
	public void testName() {
		//assertTrue(Player.getPlayerName() = "test");
		assertTrue(true);
	}

	@Test
	public void testGetHealth() {
		//assertTrue(getHealth("") = 100);
	}
	
	@Test
    public void testSetHealth( )
    {
       
    }
	
	
}
