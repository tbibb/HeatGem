package edu.ycp.cs320.heatgem.game;

public class Player {
	
	int health;
    String playerName;
    
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
