package edu.ycp.cs320.heatgem.shared;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User myUser;
	
	private int id;
	private String username;
	private String password;
	private int highScore;
	private String email;
	private int experience;
	private int level;
	private int wins;
	private int losses;
	
	@Before
	public void setUp() throws Exception{
		
		myUser = new User();
		
		id = 3;
		username = "Alice";
		password = "wonderland";
		highScore = 99;
		email = "Alice@wonderland.com";
		experience = 2222;
		level = 99;
		wins = 30;
		losses = 2;
		
		myUser.setId(id);
		myUser.setUsername(username);
		myUser.setPassword(password);
		myUser.setHighScore(highScore);
		myUser.setEmail(email);
		myUser.setExperience(experience);
		myUser.setLevel(level);
		myUser.setWins(wins);
		myUser.setLosses(losses);
		
		myUser.getId();
		myUser.getUsername();
		myUser.getPassword();
		myUser.getHighScore();
		myUser.getEmail();
		myUser.getExperience();
		myUser.getLevel();
		myUser.getWins();
		myUser.getLosses();
		
	}
	
	@Test
	public void testSetId() {
		assertEquals(this.id, 3);
	}
	
	@Test
	public void testGetId() {
		assertEquals(myUser.getId(), 3);
	}
	
	@Test
	public void testSetUsername() {
		assertEquals(this.username, "Alice");
	}
	
	@Test
	public void testGetUsername() {
		assertEquals(myUser.getUsername(), "Alice");
	}
	
	@Test
	public void testSetPassword() {
		assertEquals(this.password, "wonderland");		
	}
	
	@Test
	public void testGetPassword() {
		assertEquals(myUser.getPassword(), "wonderland");
	}
	
	@Test
	public void testSetHighScore() {
		assertEquals(this.highScore, 99);
	}
	
	@Test
	public void testGetHighScore() {
		assertEquals(myUser.getHighScore(), 99);
	}
	
	@Test
	public void testSetEmail(){
		assertEquals(this.email, "Alice@wonderland.com");
	}
	
	@Test
	public void testGetEmail(){
		assertEquals(myUser.getEmail(), "Alice@wonderland.com");
	}
	
	@Test
	public void testSetExperience(){
		assertEquals(this.experience, 2222);
	}
	
	@Test
	public void testGetExperience(){
		assertEquals(myUser.getExperience(), 2222);
	}
	
	@Test
	public void testSetLevel(){
		assertEquals(this.level, 99);
	}
	
	@Test
	public void testGetLevel(){
		assertEquals(myUser.getLevel(), 99);
	}
	
	@Test
	public void testSetWins(){
		assertEquals(this.wins, 30);
	}
	
	@Test
	public void testGetWins(){
		assertEquals(myUser.getWins(), 30);
	}
	
	@Test
	public void testSetLosses(){
		assertEquals(this.losses, 2);
	
	}
	
	@Test
	public void testGetLosses(){
		assertEquals(myUser.getLosses(), 2);
	}
	

	
}
