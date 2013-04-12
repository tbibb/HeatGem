package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.dom.client.Style.Unit;

public class LeaderBoard extends Composite {

	public LeaderBoard(){
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		
		FlexTable t = new FlexTable();
		layoutPanel.add(t);
		layoutPanel.setWidgetLeftWidth(t, 32.0, Unit.PX, 325.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(t, 14.0, Unit.PX, 70.0, Unit.PX);
		
		// Put some text at the table's extremes.  This forces the table to be
	    // 3 by 3.
	    t.setText(0, 0, "Username");
	    t.setText(0, 1, "Score");
	    t.setText(0, 2, "Time");
	    t.setBorderWidth(1);
		
	}
}
