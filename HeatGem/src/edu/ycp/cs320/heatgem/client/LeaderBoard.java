package edu.ycp.cs320.heatgem.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.dom.client.Style.Unit;

import edu.ycp.cs320.heatgem.server.FakeDatabase;
import edu.ycp.cs320.heatgem.shared.User;

public class LeaderBoard extends Composite {
	private List<User> topScoreList;

	public LeaderBoard(){
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		
		FlexTable t = new FlexTable();
		layoutPanel.add(t);
		layoutPanel.setWidgetLeftWidth(t, 32.0, Unit.PX, 144.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(t, 14.0, Unit.PX, 94.0, Unit.PX);
		
	    t.setText(0, 0, "Username");
	    t.setText(1, 0, "Alice");
	    t.setText(2, 0, "Bob");
	    t.setText(0, 1, "Score");
	    t.setText(0, 2, "Time");
	    t.setBorderWidth(1);
		
	}
	
//	public void updateBoard() {
//		topScoreList = new ArrayList<User>();
//		
//		// Create example users
//		User user = new User();
//		user.setUsername("alice");
//		user.setPassword("abc");
//		
//		User user2 = new User();
//		user2.setUsername("bob");
//		user2.setPassword("xyz");
//		
//		topScoreList.add(user);
//		topScoreList.add(user2);
//	}
}
