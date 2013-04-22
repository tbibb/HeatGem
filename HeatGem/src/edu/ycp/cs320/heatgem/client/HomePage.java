package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DecoratedTabBar;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.widget.client.TextButton;


public class HomePage extends Composite {
	private LayoutPanel layoutPanel;
	private DecoratedTabBar decoratedTabBar;
	private LayoutPanel tabViewLayoutPanel;
	private int tabCurrentSelection= -1;
	private IsWidget currentTabView;
	
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
		
		InlineLabel HeatGemLabel = new InlineLabel("HEAT GEM");
		layoutPanel.add(HeatGemLabel);
		layoutPanel.setWidgetLeftWidth(HeatGemLabel, 13.0, Unit.PX, 145.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(HeatGemLabel, 0.0, Unit.PX, 31.0, Unit.PX);
		
		InlineLabel WelcomeLabel = new InlineLabel("Welcome to Heat Gem, the interactive game site!");
		layoutPanel.add(WelcomeLabel);
		layoutPanel.setWidgetLeftWidth(WelcomeLabel, 13.0, Unit.PX, 305.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(WelcomeLabel, 51.0, Unit.PX, 18.0, Unit.PX);
		
		Hyperlink hprlnkLogOut = new Hyperlink("Log out!", false, "newHistoryToken");
		layoutPanel.add(hprlnkLogOut);
		layoutPanel.setWidgetLeftWidth(hprlnkLogOut, 605.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(hprlnkLogOut, 22.0, Unit.PX, 18.0, Unit.PX);
		
		tabViewLayoutPanel = new LayoutPanel();
		layoutPanel.add(tabViewLayoutPanel);
		tabViewLayoutPanel.setSize("800", "480");
		layoutPanel.setWidgetLeftRight(tabViewLayoutPanel, 0.0, Unit.PX, -91.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(tabViewLayoutPanel, 88.0, Unit.PX, 0.0, Unit.PX);
		

		
	}
	
	public void activate() {
		decoratedTabBar.selectTab(0);
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
			
			//Leaderboards Tab
			else if (tabCurrentSelection == 3) {
				nextTabView = new LeaderBoard();
			}
			
			
			
			currentTabView = nextTabView;
			if (nextTabView != null) {
				GWT.log("Adding a " + nextTabView.getClass().getName() + " to the tab view layout panel");
				tabViewLayoutPanel.add(nextTabView);
				
				
				// FIXME: think of a better way to do this
				if (nextTabView instanceof GameUI) {
					((GameUI)nextTabView).startGame();
				}
			}
		}
	}
}
