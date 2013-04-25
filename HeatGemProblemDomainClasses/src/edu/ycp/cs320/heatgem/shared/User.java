package edu.ycp.cs320.heatgem.shared;

import java.io.Serializable;

//comment

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String password;
	private int highScore;
	private String email;
	private int experience;
	private int level;
	private int wins;
	private int losses;
	

	public User() {
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	public int getHighScore() {
		return highScore;
	}
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setExperience(int experience){
		this.experience = experience;
	}
	
	public int getExperience(){
		return experience;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setWins(int wins){
		this.wins = wins;
	}
	
	public int getWins(){
		return wins;
	}
	
	public void setLosses(int losses){
		this.losses = losses;
	}
	
	public int getLosses(){
		return losses;
	}
	
	
}
