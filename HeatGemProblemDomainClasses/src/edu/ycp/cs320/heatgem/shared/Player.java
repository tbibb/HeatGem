package edu.ycp.cs320.heatgem.shared;

public class Player {
	
	private int health;
    private String playerName;
    
    public Player(String username)
    {
        playerName = username;
        health = 100;
    }

    public int getHealth()
    {
        return health;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }
}