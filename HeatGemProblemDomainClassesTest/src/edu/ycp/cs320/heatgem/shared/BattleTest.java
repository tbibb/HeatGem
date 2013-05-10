package edu.ycp.cs320.heatgem.shared;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
	public void testAttack() {
		player2.setHealth(100);
		battle.Attack();
		assertTrue(player2.getHealth() < 100);		
	}
	
	@Test
	public void testheal() {
		player1.setHealth(200);
		battle.heal();
		assertEquals( player1.getHealth(), 100);
		
		player1.setHealth(1);
		battle.heal();
		assertTrue(player1.getHealth() > 1);
	}
	
	@Test
	public void testMonsterAttack() {
		player1.setHealth(100);
		battle.MonsterAttack();
		assertTrue(player1.getHealth() < 100);
	}
	
	@Test
	public void testMonsterHeal() {
		player2.setHealth(200);
		battle.MonsterHeal();
		assertEquals( player2.getHealth(), 100);
		
		player2.setHealth(1);
		battle.MonsterHeal();
		assertTrue(player2.getHealth() >= 1);
	}
	
	
	@Test
	public void testBattleState(){
		player1.setHealth(100);
		player2.setHealth(100);
		assertTrue(battle.battleState() == 0);
		assertFalse(battle.battleState() == 1);
		assertFalse(battle.battleState() == 2);
		
		player1.setHealth(0);
		assertTrue(battle.battleState() == 1);
		
		player1.setHealth(100);
		player2.setHealth(0);
		assertTrue(battle.battleState() == 2);
	}
	
	@Test
	public void testGetHealth1(){
		assertTrue(battle.getHealth1() <= 100);
	
	}
	
	@Test
	public void testGetHealth2(){
		assertTrue(battle.getHealth2() <= 100);
	
	}

}
