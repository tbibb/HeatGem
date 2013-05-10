package edu.ycp.cs320.heatgem.shared;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LogicTest {
	
	private Player playerA;
	private Player playerB;
	private Battle battle;
	private Logic logic;
	
	@Before
	public void setUp(){
		
		playerA = new Player("alpha");
		playerB = new Player("beta");
		battle = new Battle(playerA, playerB);
		
		
	}
	
	
	@Test
	public void testDoBattle() {
		
		
		Logic.doBattle(playerA, playerB);
		assertTrue(playerA.getHealth() <= 100);
		assertTrue(playerB.getHealth() >= 0);
		
	}
	
	
	@Test
	public void testDoHeal(){
		
		Logic.doHeal(playerA, playerB);
		
	}
	

}
