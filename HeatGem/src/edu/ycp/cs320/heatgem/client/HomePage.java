package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DecoratedTabBar;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;

import edu.ycp.cs320.heatgem.shared.User;
import com.google.gwt.user.client.ui.Image;



public class HomePage extends Composite {
	private LayoutPanel layoutPanel;
	private DecoratedTabBar decoratedTabBar;
	private LayoutPanel tabViewLayoutPanel;
	private int tabCurrentSelection= -1;
	private IsWidget currentTabView;
	private User user;
	private Image smallgem;
	
	public HomePage(){

		layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("800px", "571px");

		decoratedTabBar = new DecoratedTabBar();
		decoratedTabBar.addSelectionHandler(new SelectionHandler<Integer>() {
			public void onSelection(SelectionEvent<Integer> event) {

				handleTabSelection(event);
			}
		});
		decoratedTabBar.addTab("Home");
		decoratedTabBar.addTab("Profile");
		decoratedTabBar.addTab("Game");
		decoratedTabBar.addTab("Leader Boards");
		layoutPanel.add(decoratedTabBar);
		layoutPanel.setWidgetRightWidth(decoratedTabBar, 0.0, Unit.PX, 300.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(decoratedTabBar, 51.0, Unit.PX, 31.0, Unit.PX);

		InlineLabel WelcomeLabel = new InlineLabel("Welcome to Heat Gem, the interactive game site!");
		WelcomeLabel.setStyleName("gwt-InlineLabel-redness");
		layoutPanel.add(WelcomeLabel);
		layoutPanel.setWidgetLeftWidth(WelcomeLabel, 13.0, Unit.PX, 305.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(WelcomeLabel, 58.0, Unit.PX, 18.0, Unit.PX);

		tabViewLayoutPanel = new LayoutPanel();
		layoutPanel.add(tabViewLayoutPanel);
		tabViewLayoutPanel.setSize("800", "480");
		layoutPanel.setWidgetLeftRight(tabViewLayoutPanel, 0.0, Unit.PX, -91.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(tabViewLayoutPanel, 88.0, Unit.PX, 0.0, Unit.PX);

		Label lblLogOut = new Label("Log out!!");
		lblLogOut.setStyleName("gwt-Label-hyperlink");
		lblLogOut.addMouseMoveHandler(new MouseMoveHandler() {
			public void onMouseMove(MouseMoveEvent event) {
			}
		});
		lblLogOut.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				handleLogOut();
			}
		});
		layoutPanel.add(lblLogOut);
		layoutPanel.setWidgetLeftWidth(lblLogOut, 596.0, Unit.PX, 99.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblLogOut, 17.0, Unit.PX, 28.0, Unit.PX);
		
		smallgem = new Image();
		layoutPanel.add(smallgem);
		layoutPanel.setWidgetLeftWidth(smallgem, 0.0, Unit.PX, 70.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(smallgem, 0.0, Unit.PX, 50.0, Unit.PX);
		smallgem.setUrl(GWT.getModuleBaseForStaticFiles() + "fireRuby_small.gif");


	}

	public void activate() {
		decoratedTabBar.selectTab(0);
	}

	public void handleLogOut(){
		LoginView view = new LoginView();
		HeatGem.setView(view);
	}

	protected void handleTabSelection(SelectionEvent<Integer> event) {
		GWT.log("Tab selection: " + event.getSelectedItem());
		if (event.getSelectedItem().intValue() != tabCurrentSelection) {
			tabCurrentSelection = event.getSelectedItem();

			if (currentTabView != null) {
				tabViewLayoutPanel.remove(currentTabView);
			}


			//nextTabView = new HomeView();

			//IsWidget nextTabView = null;

			IsWidget nextTabView = new HomeView();



			//Game Tab
			if (tabCurrentSelection == 2){
				nextTabView = new GameUI();

			}

			//Home Tab
			else if (tabCurrentSelection == 0) {
				nextTabView = new HomeView();
			}

			//Profile Tab
			else if (tabCurrentSelection == 1) {
				nextTabView = new ProfileView();
			}

			//Leader boards Tab
			else if (tabCurrentSelection == 3) {
				nextTabView = new LeaderBoard();
			}



			currentTabView = nextTabView;
			if (nextTabView != null) {
				GWT.log("Adding a " + nextTabView.getClass().getName() + " to the tab view layout panel");
				tabViewLayoutPanel.add(nextTabView);

				if (nextTabView instanceof GameUI) {
					GameUI gameUI = (GameUI) nextTabView;
					gameUI.setusername(user.getUsername());
					gameUI.setProfile(user.getUsername());
					gameUI.startGame();
				} else if (nextTabView instanceof ProfileView) {
					ProfileView profileView = (ProfileView) nextTabView;
					profileView.setUsername(user.getUsername());
					profileView.setLevel(user.getLevel());
					profileView.setExp(user.getExperience());
					profileView.setLosses(user.getLosses());
					profileView.setWins(user.getWins());
					profileView.activate();
				} else if (nextTabView instanceof HomeView) {
					((HomeView)nextTabView).startScroll();
				}
			}
		}
	}

	public void setUser(User result) {
		this.user = result;
	}
}
