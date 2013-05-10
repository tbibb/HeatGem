package edu.ycp.cs320.heatgem.client;


import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;

import com.google.gwt.event.dom.client.MouseMoveHandler;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.ImageElement;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;


import edu.ycp.cs320.heatgem.shared.Battle;
import edu.ycp.cs320.heatgem.shared.Game;
import edu.ycp.cs320.heatgem.shared.Logic;
import edu.ycp.cs320.heatgem.shared.Player;
import edu.ycp.cs320.heatgem.shared.Score;
import edu.ycp.cs320.heatgem.shared.UserProfile;

public class GameUI extends Composite {

	public int MouseX;
	public int MouseY;
	private int gamestate;
	private Canvas buffer;
	private Context2d bufCtx;
	private Canvas canvas;
	private Context2d ctx;
	private Timer timer;
	private Image background, GameWin, GameLoss, HomePage;
	private Image Layer1, Layer2, Layer3, Layer4, Layer5, Layer6, Layer7, Layer8, Layer9, Layer10, Layer11, Layer12, Layer13, Layer14,Layer15;
	private Image PlayerFace;
	private Image EnemyFace;
	private Image Attack;
	private Image AttackSelected;
	private Image Heal;
	private Image HealSelected;
	private Image Play, PlaySelected;
	private Image Defeat;
	private Image Victory;
	private Image MediumHealth, LowHealth;
	private Player player1;
	private Player player2;
	private Battle BattleState;
	// The game object contains all of the game state data.
	private Game game;
	private int MilliTime;
	private int SecondTime;
	public int TotalTime; 
	private int PScore;
	private Score score;
	private int HealthMove, HealthMove2, PrevHealth = 100, PrevHealth2 = 100;

	//NICK: profile retrieval things
	private String username;
	private UserProfile profile;

	public GameUI() {

		// FocusPanel
		final FocusPanel panel = new FocusPanel();
		// LayoutPanel layoutPanel = new LayoutPanel();
		panel.setSize("800px", "480px");

		// "buffer" canvas
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

		initWidget(panel);

		this.timer = new Timer() {
			@Override
			public void run() {
				Draw();
				if (gamestate == 1) {
					if (BattleState.battleState() == 0) {
						MilliTime++; // Framerate at 10 frames per second
						TotalTime++;
						if (MilliTime % 100 == 0) { //Increment timer by
									// seconds ONLY if game is
									// in session
							SecondTime++;
							MilliTime = 0;
						}
					} else { // Get Score
						PScore = (int) score.getScore(TotalTime,  player1);
						//timer.cancel();
						/*
						//QUESTION
						//NICK: update database with new score, testing values now, don't work :(
						//I think it's because the timer is still going, maybe not 
						profile.setLosses(1);
						updateScore();
						GWT.log("updated score perhaps");
						timer.cancel();
						 */
					}
				}
				else { 
				}	// Do nothing
			}
		};
		


		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {

				MouseX = event.getX();
				MouseY = event.getY();
			}

		});

		canvas.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				if (gamestate == 0) { // Menu
					if ((MouseX >= 300 && MouseX <= 500)
							&& (MouseY >= 200 && MouseY <= 250)) {
						gamestate = 1;
					}
				} else {
					if (BattleState.battleState() == 0) { // If No one has one,
															// allow the game to
															// continue
						if ((MouseX > 380 && MouseX < 455)
								&& (MouseY > 360 && MouseY < 390)) {

							Logic.doBattle(player1, player2);
							//Set localvariable to store and print the player's health change
							HealthMove = PrevHealth - player1.getHealth();
							HealthMove2 = PrevHealth2 - player2.getHealth();
							
							HealthMove *= -1; 
							HealthMove2 *= -1;
							
							PrevHealth = player1.getHealth();
              PrevHealth2 = player2.getHealth();
							
						} else if ((MouseX > 380 && MouseX < 455)
								&& (MouseY > 410 && MouseY < 440)) {

							Logic.doHeal(player1, player2);

				            //Set localvariable to store and print the player's health change
				            HealthMove = PrevHealth - player1.getHealth();
				            HealthMove2 = PrevHealth2 - player2.getHealth();
				            
				            HealthMove *= -1; 
				            HealthMove2 *= -1;
				            
				            PrevHealth = player1.getHealth();
			            	PrevHealth2 = player2.getHealth();

						}
					}
				}
			}
		});

	}

	public void onMouseMove(Widget sender, int x, int y) {
		x = MouseX;
		y = MouseY;
	}

	public void startGame() {
		// get background and sprite images that will be used for painting
		background = HeatGem.getImage("RoughBattle.jpg");
		PlayerFace = HeatGem.getImage("FullHealth.png");
		EnemyFace = HeatGem.getImage("FullHealth.png");
		Attack = HeatGem.getImage("Attack.png");
		Heal = HeatGem.getImage("Heal.png");
		Defeat = HeatGem.getImage("Defeat.png");
		Victory = HeatGem.getImage("Victory.png");
		AttackSelected = HeatGem.getImage("AttackSelected.png");
		HealSelected = HeatGem.getImage("HealSelected.png");
		GameWin = HeatGem.getImage("BattleWin.jpg");
		GameLoss = HeatGem.getImage("BattleLoss.jpg");
		MediumHealth = HeatGem.getImage("YellowHealth.png");
		LowHealth = HeatGem.getImage("LowHealth.png");
		HomePage = HeatGem.getImage("Homepage.png");
		//HomePage = HeatGem.getImage("HomePageDif.gif");
		Play = HeatGem.getImage("Play.png");
		PlaySelected = HeatGem.getImage("PlaySelected.png");

		game = new Game();
		player1 = new Player("Player");
		player2 = new Player("Monster");
		BattleState = new Battle(player1, player2);
		score = new Score();
		gamestate = 0;
		// Add a listener for mouse motion.
		// Each time the mouse is moved, clicked, released, etc. the
		// handleMouseMove method
		// will be called.
		timer.scheduleRepeating(1000 / 100); // DeciSeconds
	}

	// protected void handleTimerEvent() {
	// // You should not need to change this method.
	// game.timerTick();
	//
	// }
	


	protected void updateScore() {
		//NICK
		RPC.userService.updateUserProfile(username, profile, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("GameUI-updateScore Error");
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				System.out.println("Score sent to database!");
			}
			
			
		});
		
	}

	public void setusername(String username) {
		//NICK
		this.username = username;
	}

	public void setProfile(String username) {
		//NICK
		RPC.userService.getUserProfile(username, new AsyncCallback <UserProfile>() {

			@Override
			public void onFailure(Throwable caught) {
				// show error message
				System.out.println("GameUI-getUserProfile Error");
			}

			@Override
			public void onSuccess(UserProfile result) {
					profile = result;
			}
		});

	}

	protected void Draw() {

		// Draw home menu
		if (gamestate == 0) {
		    for (int i = 1; i <= 15; i++){
			      bufCtx.drawImage((ImageElement) HomePage.getElement().cast(), 0, 0);
			}
			if ((MouseX >= 300 && MouseX <= 500)
					&& (MouseY >= 200 && MouseY <= 250)) {
				bufCtx.drawImage((ImageElement) PlaySelected.getElement()
						.cast(), 300, 200); // Draw play selected
			} else {
				bufCtx.drawImage((ImageElement) Play.getElement().cast(), 300,
						200); // Draw play
			}

		} else {
			if (BattleState.battleState() == 0) {
				// Draw background
				bufCtx.drawImage((ImageElement) background.getElement().cast(),
						0, 0);
				// if (p1Health > 50){
				int player1Health = player1.getHealth();
				if (player1Health > 50) {
					bufCtx.setFillStyle("green");
					// Draw Sprite for character
					bufCtx.drawImage((ImageElement) PlayerFace.getElement()
							.cast(), 50, 200);
				} else if (player1Health <= 50 && player1Health > 25) {
					bufCtx.setFillStyle("yellow");
					// Draw Sprite for character
					bufCtx.drawImage((ImageElement) MediumHealth.getElement()
							.cast(), 50, 200);
				} else {
					bufCtx.setFillStyle("red");
					// Draw Sprite for character
					bufCtx.drawImage((ImageElement) LowHealth.getElement()
							.cast(), 50, 200);
				}
				// Draw PlayerHealth Bar that scales based on health size
				bufCtx.fillRect(30, 430, (double) player1Health * 3, 25);

				int player2Health = player2.getHealth();
				if (player2Health > 50) {
					bufCtx.setFillStyle("green");
					// Draw Sprite for Enemy
					bufCtx.drawImage((ImageElement) EnemyFace.getElement()
							.cast(), 580, 100);
				} else if (player2Health <= 50 && player2Health > 25) {
					bufCtx.setFillStyle("yellow");
					// Draw Sprite for Enemy
					bufCtx.drawImage((ImageElement) MediumHealth.getElement()
							.cast(), 580, 100);
				} else {
					bufCtx.setFillStyle("red");
					// Draw Sprite for Enemy
					bufCtx.drawImage((ImageElement) LowHealth.getElement()
							.cast(), 580, 100);
				}

				// Draw EnemyHealth Bar that scales based on health size
				bufCtx.fillRect(450, 35, (double) player2Health * 3, 25);

				if ((MouseX > 380 && MouseX < 455)
						&& (MouseY > 360 && MouseY < 390)) {
					// Draw AttackSelected Button
					bufCtx.drawImage((ImageElement) AttackSelected.getElement()
							.cast(), 380, 360);
				} else {
					// Draw Attack Button
					bufCtx.drawImage((ImageElement) Attack.getElement().cast(),
							380, 360);
				}
				if ((MouseX > 380 && MouseX < 455)
						&& (MouseY > 410 && MouseY < 440)) {
					// Draw HealSelected Button
					bufCtx.drawImage((ImageElement) HealSelected.getElement()
							.cast(), 380, 410);
				} else {
					// Draw Heal Button
					bufCtx.drawImage((ImageElement) Heal.getElement().cast(),
							380, 410);
				}
				// Set font to red
				bufCtx.setFillStyle("red");
				// Sets font to sans-serif
				bufCtx.setFont("bold 16px sans-serif");
				// Prints Player 1 Health
				bufCtx.fillText((player1Health + " / 100"), 30, 430);
				// Prints Player 2 Health
				bufCtx.fillText(player2Health + " / 100", 450, 35);
				bufCtx.setFillStyle("red");
				bufCtx.setFont("bold 18px sans-serif");
				// Prints current game time
				bufCtx.fillText(
						Integer.toString(SecondTime) + ":"
								+ Integer.toString(MilliTime), 700, 378);
				bufCtx.fillText("Health changes after last move- Player1: " + HealthMove + " Player2:" + HealthMove2, 320, 330);

			} else if (BattleState.battleState() == 1) {
				// Draw loss image
				bufCtx.drawImage((ImageElement) GameLoss.getElement().cast(),
						0, 0);
				bufCtx.setFillStyle("green");
				bufCtx.setFont("bold 36px sans-serif");
				bufCtx.fillText(username + ", Your Score: " + Integer.toString(PScore), 400,
						200);

				// Draw Sprite for character
				bufCtx.drawImage((ImageElement) Defeat.getElement().cast(), 50,
						200);

			} else {
				// Draw win image
				bufCtx.drawImage((ImageElement) GameWin.getElement().cast(), 0,
						0);
				bufCtx.setFillStyle("green");
				bufCtx.setFont("bold 36px sans-serif");
				bufCtx.fillText(username + ", Your Score: " + Integer.toString(PScore), 350,
						250);

				// Draw Sprite for character
				bufCtx.drawImage((ImageElement) Victory.getElement().cast(),
						50, 200);
			}
		}
		// Copy buffer onto main canvas
		ctx.drawImage((CanvasElement) buffer.getElement().cast(), 0, 0);

	}

}