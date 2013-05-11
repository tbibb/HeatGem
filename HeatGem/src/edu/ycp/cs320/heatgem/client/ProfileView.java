package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.NumberLabel;

import edu.ycp.cs320.heatgem.shared.UserProfile;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class ProfileView extends Composite {

	private Image UserFace;
	private NumberLabel<Integer> numberLevelLabel;
	private NumberLabel<Integer> experienceLabel;

	private String username;
	private int level;
	private int exp;
	private int losses;
	private int wins;

	private UserProfile model;
	private NumberLabel<Integer> lossesLabel;
	private NumberLabel<Integer> winsLabel;
	private Label nameProfileLabel;
	
	private Image namepic;
	private Image levelpic;
	private Image exppic;
	private Image winspic;
	private Image lossespic;

	/**
	 * @param args
	 */
	public ProfileView() {
		activate();
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("568px", "472px");

		Button btnNewButton_1 = new Button("Delete Profile");
		btnNewButton_1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				deleteUser(username);
				LoginView view = new LoginView();
				HeatGem.setView(view);
				
			}
		});
		layoutPanel.add(btnNewButton_1);
		layoutPanel.setWidgetLeftWidth(btnNewButton_1, 639.0, Unit.PX, 122.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnNewButton_1, 371.0, Unit.PX, 30.0, Unit.PX);

//		Label lblProfileView = new Label("Profile View");
//		layoutPanel.add(lblProfileView);
//		layoutPanel.setWidgetLeftWidth(lblProfileView, 244.0, Unit.PX, 99.0, Unit.PX);
//		layoutPanel.setWidgetTopHeight(lblProfileView, 23.0, Unit.PX, 18.0, Unit.PX);

		//Image image = new Image("Defeat.png");
		UserFace = HeatGem.getImage("Defeat.png");

		UserFace.setAltText("you");
		layoutPanel.add(UserFace);
		layoutPanel.setWidgetLeftWidth(UserFace, 600.0, Unit.PX, 179.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(UserFace, 81.0, Unit.PX, 197.0, Unit.PX);

//		Label lblLevel = new Label("Level");
//		layoutPanel.add(lblLevel);
//		layoutPanel.setWidgetLeftWidth(lblLevel, 20.0, Unit.PX, 56.0, Unit.PX);
//		layoutPanel.setWidgetTopHeight(lblLevel, 128.0, Unit.PX, 18.0, Unit.PX);
//
//		Label lblExperience = new Label("Experience");
//		layoutPanel.add(lblExperience);
//		layoutPanel.setWidgetLeftWidth(lblExperience, 17.0, Unit.PX, 70.0, Unit.PX);
//		layoutPanel.setWidgetTopHeight(lblExperience, 181.0, Unit.PX, 18.0, Unit.PX);
//
//		Label lblWins = new Label("Wins");
//		layoutPanel.add(lblWins);
//		layoutPanel.setWidgetLeftWidth(lblWins, 20.0, Unit.PX, 56.0, Unit.PX);
//		layoutPanel.setWidgetTopHeight(lblWins, 236.0, Unit.PX, 18.0, Unit.PX);
//
//		Label lblLosses = new Label("Losses");
//		layoutPanel.add(lblLosses);
//		layoutPanel.setWidgetLeftWidth(lblLosses, 20.0, Unit.PX, 56.0, Unit.PX);
//		layoutPanel.setWidgetTopHeight(lblLosses, 284.0, Unit.PX, 18.0, Unit.PX);

		numberLevelLabel = new NumberLabel<Integer>();
		numberLevelLabel.setStyleName("h1");
		layoutPanel.add(numberLevelLabel);
		//layoutPanel.setPixelSize(25, 25);
		layoutPanel.setWidgetLeftWidth(numberLevelLabel, 201.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(numberLevelLabel, 131.0, Unit.PX, 18.0, Unit.PX);
		

		experienceLabel = new NumberLabel<Integer>();
		layoutPanel.add(experienceLabel);
		layoutPanel.setWidgetLeftWidth(experienceLabel, 201.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(experienceLabel, 212.0, Unit.PX, 18.0, Unit.PX);

		nameProfileLabel = new Label("");
		layoutPanel.add(nameProfileLabel);
		layoutPanel.setWidgetLeftWidth(nameProfileLabel, 201.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(nameProfileLabel, 59.0, Unit.PX, 18.0, Unit.PX);

		winsLabel = new NumberLabel<Integer>();
		layoutPanel.add(winsLabel);
		layoutPanel.setWidgetLeftWidth(winsLabel, 201.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(winsLabel, 283.0, Unit.PX, 18.0, Unit.PX);

		lossesLabel = new NumberLabel<Integer>();
		layoutPanel.add(lossesLabel);
		layoutPanel.setWidgetLeftWidth(lossesLabel, 201.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lossesLabel, 359.0, Unit.PX, 18.0, Unit.PX);
		
		namepic = new Image();
		layoutPanel.add(namepic);
		layoutPanel.setWidgetLeftWidth(namepic, 24.0, Unit.PX, 160.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(namepic, 35.0, Unit.PX, 60.0, Unit.PX);
		namepic.setUrl(GWT.getModuleBaseForStaticFiles() + "name.png");

		levelpic = new Image();
		layoutPanel.add(levelpic);
		layoutPanel.setWidgetLeftWidth(levelpic, 24.0, Unit.PX, 160.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(levelpic, 110.0, Unit.PX, 60.0, Unit.PX);
		levelpic.setUrl(GWT.getModuleBaseForStaticFiles() + "level.png");

		exppic = new Image();
		layoutPanel.add(exppic);
		layoutPanel.setWidgetLeftWidth(exppic, 24.0, Unit.PX, 160.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(exppic, 185.0, Unit.PX, 60.0, Unit.PX);
		exppic.setUrl(GWT.getModuleBaseForStaticFiles() + "exp.png");
		
		winspic = new Image();
		layoutPanel.add(winspic);
		layoutPanel.setWidgetLeftWidth(winspic, 24.0, Unit.PX, 160.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(winspic, 260.0, Unit.PX, 60.0, Unit.PX);
		winspic.setUrl(GWT.getModuleBaseForStaticFiles() + "wins.png");
		
		lossespic = new Image();
		layoutPanel.add(lossespic);
		layoutPanel.setWidgetLeftWidth(lossespic, 24.0, Unit.PX, 160.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lossespic, 335.0, Unit.PX, 60.0, Unit.PX);
		lossespic.setUrl(GWT.getModuleBaseForStaticFiles() + "losses.png");
		
		
		
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public void deleteUser(final String username) {
		RPC.userService.deleteUserAccount(username,  new AsyncCallback <Boolean>() {
			
			@Override
			public void onFailure(Throwable caught) {
				// show error message
				System.out.println("Could not communicate with server?");
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				if (result == true) {
					System.out.println("Profile was not deleted!");
				} else {
					System.out.println("Profile Deleted!");
				}
			}
		});
	}

	public void activate() {

		RPC.userService.getUserProfile(username, new AsyncCallback <UserProfile>() {

			@Override
			public void onFailure(Throwable caught) {
				// show error message
				System.out.println("Could not communicate with server?");
			}

			@Override
			public void onSuccess(UserProfile result) {
					model = result;
					update();
			}
		});

	}

	protected void update() {
		// Use values in model object to update UI components
		nameProfileLabel.setText(username);
		numberLevelLabel.setValue(level);
		experienceLabel.setValue(exp);
		lossesLabel.setValue(losses);
		winsLabel.setValue(wins);

	}
}
