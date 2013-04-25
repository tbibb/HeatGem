package edu.ycp.cs320.heatgem.shared;


import java.util.ArrayList;
import java.util.Random;
import java.applet.*;
import java.net.*;




/**
 * The Game class represents the game state:
 * the state of the player, enemy, position of menu items
 * also it is responsible for the state of health and win conditions.
 * Note that this class is NOT responsible for drawing game play objects:
 * that is handled by the GameUI class.
 */
public class Game {
	public static final int Width = 800;
	public static final int Height = 480;
	

    //Game states
    public int gameState;
    public static int introMenu = 0, gameRunning = 1, gameWin = 2, gameLoss = 3;
    private int count;
    
    
    private int MLocX, MLocY;
    
    public Player player1;
    public Player player2;
    public Battle battleState;
    
    //Variables to store rectangle positions/size
    private static double width;
	private static double height;
	private static double pX;
	private static double pY;
	
	
	
	//Health Bars
	public void RunGame() {
		player1 = new Player("Player");
		player2 = new Player("Enemy");	
		player1.setHealth(100);
		player2.setHealth(100);
	}
    
	
    //What time? Game Time...
    float currentTime = 0f;
    
    
	public void timerTick() {
		// Update stuff that needs to be updated
		count++;
		//Game Timer that increments by seconds
		if(count % 60 == 0){
			currentTime +=1;
			count = 0;
		}
		
	}

}