package edu.ycp.cs320.heatgem.shared;

import java.io.Serializable;

public class UserProfile implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// fields
	private int userId;
	private String name;
	private int level;
	private int highScore;
	private int experience, wins, losses;

	// etc.

	private int photoId;

	// methods

	public UserProfile() {

	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public int getHighScore() {
		return highScore;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
}
