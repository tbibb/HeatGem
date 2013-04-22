package edu.ycp.cs320.heatgem.shared;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BattleTest {
	private Battle battle;
	private Player player1;
	private Player player2;
	

	@Before
	public void setUp() {
		
		player1 = new Player("alice");
		player2 = new Player("bob");
		battle = new Battle(player1, player2);
	}
	@Test
	public void testTurn() {
		
		assertTrue(true);
		
	}
	
	@Test
	public void testBattleState(){
		
	}
	
	@Test
	public void testSetHealth(){
		
	}
	
	@Test
	public void testGetHealth(){
		
	}

}
