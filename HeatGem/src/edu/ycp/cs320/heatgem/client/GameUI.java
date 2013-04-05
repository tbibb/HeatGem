package edu.ycp.cs320.heatgem.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;


import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;

import com.google.gwt.user.client.ui.Widget;

import edu.ycp.cs320.heatgem.shared.Game;


public class GameUI {
public static double MouseX, MouseY;
	
	// The game object contains all of the game state data.
	private Game game;
	
	public void startGame() {

		//Update game 60x per second
		Timer timer = new Timer(1000 / 60, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTimerEvent();
			}
		});
		timer.start();
	}
		// Add a listener for mouse motion.
		// Each time the mouse is moved, clicked, released, etc. the handleMouseMove method
		// will be called.
		public void onMouseDown(Widget sender, int x, int y)
	    {
	      
	    }
	    public void onMouseEnter(Widget sender)
	    {
	       
	    }
	    public void onMouseLeave(Widget sender)
	    {
	        
	    }
	    public void onMouseMove(Widget sender, int x, int y)
	    {
	       
	    }
	    public void onMouseUp(Widget sender, int x, int y)
	    {
	       
	    }
	    
	protected void handleTimerEvent() {
		// You should not need to change this method.
		game.timerTick();
		
	}
	

	
}
