package edu.ycp.cs320.heatgem.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;

import com.google.gwt.user.client.ui.Widget;

import edu.ycp.cs320.heatgem.shared.Game;


public class GameUI extends Composite {


	public static double MouseX, MouseY;
	private Canvas buffer;
	private Context2d bufCtx;
	private Canvas canvas;
	private Context2d ctx;
	private Timer timer;
	private Image background;
	
	// The game object contains all of the game state data.
	private Game game;
	
	public GameUI() {
		
		//FocusPanel
		FocusPanel panel = new FocusPanel();
		panel.setSize(Game.Height + "px", Game.Height + "px");
		
		//"buffer" canvas
		this.buffer = Canvas.createIfSupported();
		buffer.setSize(Game.Width + "px", Game.Height + "px");
		buffer.setCoordinateSpaceWidth(Game.Width);
		buffer.setCoordinateSpaceHeight(Game.Height);
		this.bufCtx = buffer.getContext2d();
		
		// The visible canvas
		this.canvas = Canvas.createIfSupported();
		canvas.setSize(Game.Width + "px", Game.Height + "px");
		canvas.setCoordinateSpaceWidth(Game.Width);
		canvas.setCoordinateSpaceHeight(Game.Height);
		this.ctx = canvas.getContext2d();
		panel.add(canvas);

		this.timer = new Timer() {
			@Override
			public void run() {
				Draw();
			}
		};
				
		initWidget(panel);		
	}
	
	public void startGame() {
		// get background and sprite images that will be used for painting
		background = HeatGem.getImage("RoughBattle.jpg");
		
		// Add a listener for mouse motion.
		// Each time the mouse is moved, clicked, released, etc. the handleMouseMove method
		// will be called.
		timer.scheduleRepeating(1000/10);
	}
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
	
//	public void setBackground(Image RoughBattle) {
//		this.background = RoughBattle;
//	}
	
	protected void Draw() {
		// Draw onto buffer
		
		// Draw background

		
		// Draw player ship
		bufCtx.drawImage((ImageElement) background.getElement().cast(),
				0,
				0);

		
		// Copy buffer onto main canvas
		ctx.drawImage((CanvasElement) buffer.getElement().cast(), 0, 0);
	}

	
}
