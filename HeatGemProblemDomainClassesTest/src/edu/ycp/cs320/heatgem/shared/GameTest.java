package edu.ycp.cs320.heatgem.shared;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
	private Game game;
	private int count;
	
	
	@Before
	public void setUp() {
		game = new Game();
		count = 0;
	}
	
	@Test
	public void testRunGame() {
		game.RunGame();
		assertEquals(game.player1.getHealth(), 100);
		assertEquals(game.player1.getPlayerName(), "Player");
		
	}


	@Test
	public void testtimerTick() {
		game.timerTick(this.count);
		assertEquals(this.count, 0);
		
		this.count = 59;
		
		game.timerTick(this.count);
		
		
	
	}
}