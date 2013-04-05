package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DecoratedTabBar;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;


public class HomePage extends Composite {
	public HomePage(){
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("709px", "571px");
		
		DecoratedTabBar decoratedTabBar = new DecoratedTabBar();
		decoratedTabBar.addSelectionHandler(new SelectionHandler<Integer>() {
			public void onSelection(SelectionEvent<Integer> event) {
				
				//implement event handler
			}
		});
		decoratedTabBar.addTab("Home");
		decoratedTabBar.addTab("Profile");
		decoratedTabBar.addTab("Game");
		decoratedTabBar.addTab("Leader Boards");
		layoutPanel.add(decoratedTabBar);
		layoutPanel.setWidgetRightWidth(decoratedTabBar, 0.0, Unit.PX, 300.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(decoratedTabBar, 28.0, Unit.PX, 31.0, Unit.PX);
		
		InlineLabel HeatGemLabel = new InlineLabel("HEAT GEM");
		layoutPanel.add(HeatGemLabel);
		layoutPanel.setWidgetLeftWidth(HeatGemLabel, 13.0, Unit.PX, 145.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(HeatGemLabel, 0.0, Unit.PX, 31.0, Unit.PX);
		
	}
}
