package edu.ycp.cs320.heatgem.shared;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import edu.ycp.cs320.heatgem.shared.UserProfile;


public class UserProfileTest {
		
	private UserProfile myUserProfile;	
	private int userID;
	private String name;	
	private String email;
	private int level;
	private int highscore;
	private int losses;
	private int wins;
	private int experience;
	
	
	@Before
	public void setUp()  {
		
		myUserProfile = new UserProfile();
		
		userID = 10;
		name = "Bob";		
		email = "Bob@Bob.com";
		level = 5;
		highscore = 12345;
		losses = 12;
		wins = 20;
		experience = 2002;
		
		myUserProfile.setUserId(userID);
		myUserProfile.setName(name);
		myUserProfile.setEmail(email);
		myUserProfile.setLevel(level);
		myUserProfile.setHighScore(highscore);
		myUserProfile.setLosses(losses);
		myUserProfile.setWins(wins);
		myUserProfile.setExperience(experience);
		
		myUserProfile.getUserId();		
		myUserProfile.getName();
		myUserProfile.getEmail();
		myUserProfile.getLevel();
		myUserProfile.getHighScore();
		myUserProfile.getLosses();
		myUserProfile.getWins();
		myUserProfile.getExperience();
		
		
		
		
	}
	
	@Test
	public void testSetUserId() {
		assertEquals(this.userID, 10);
	}

	@Test
	public void testGetUserId() {		
		assertEquals(myUserProfile.getUserId(), 10);
	}

	@Test
	public void testSetName() {
		assertEquals(this.name, "Bob");
	}

	@Test
	public void testGetName() {
		assertEquals(myUserProfile.getName(), "Bob");
	}
	
	@Test
	public void testSetEmail() {
		assertEquals(this.email, "Bob@Bob.com");
	}
	
	@Test
	public void testTetEmail() {
		assertEquals("Bob@Bob.com", myUserProfile.getEmail());
	}

	@Test
	public void setLevel() {
		assertEquals(this.level, 5);
	}

	@Test
	public void testGetLevel() {
		assertEquals(5, myUserProfile.getLevel());
	}

	@Test
	public void testSetHighScore() {	
		assertEquals(this.highscore, 12345);
	}
 
	@Test
	public void testGetHighScore() {
		assertEquals(12345, myUserProfile.getHighScore());
	}


	@Test
	public void testSetLosses() {
		assertEquals(this.losses, 12);
	}
	
	@Test
	public void testGetLosses() {
		assertEquals(12, myUserProfile.getLosses());
	}
	
	@Test
	public void testSetWins() {		
		assertEquals(this.wins, 20);
	}
	
	@Test
	public void testGetWins() {
		assertEquals(20, myUserProfile.getWins());
	}
	
	@Test
	public void testSetExperience() {
		assertEquals(this.experience, 2002);
	}
	
	@Test
	public void testGetExperience() {
		assertEquals(2002, myUserProfile.getExperience());
	}


	
	
}
