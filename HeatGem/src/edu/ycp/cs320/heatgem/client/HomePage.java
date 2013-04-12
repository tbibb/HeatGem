package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DecoratedTabBar;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.user.client.ui.Hyperlink;


public class HomePage extends Composite {
	private LayoutPanel layoutPanel;
	public HomePage(){
		
		layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("709px", "571px");
		
		DecoratedTabBar decoratedTabBar = new DecoratedTabBar();
		decoratedTabBar.addSelectionHandler(new SelectionHandler<Integer>() {
			public void onSelection(SelectionEvent<Integer> event) {
				
				//implement event handler-->selecting a tab
				
				
				
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
		layoutPanel.setWidgetLeftWidth(WelcomeLabel, 47.0, Unit.PX, 305.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(WelcomeLabel, 99.0, Unit.PX, 18.0, Unit.PX);
		
		Hyperlink hprlnkLogOut = new Hyperlink("Log out!", false, "newHistoryToken");
		layoutPanel.add(hprlnkLogOut);
		layoutPanel.setWidgetLeftWidth(hprlnkLogOut, 605.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(hprlnkLogOut, 22.0, Unit.PX, 18.0, Unit.PX);
		
	}
}
