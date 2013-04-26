package edu.ycp.cs320.heatgem.client;

//import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Label;

import edu.ycp.cs320.heatgem.shared.UserProfile;

public class LeaderBoard extends Composite {
	//private List<UserProfile> highScoreList;

	public LeaderBoard(){
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		
		FlexTable t = new FlexTable();
		layoutPanel.add(t);
		layoutPanel.setWidgetLeftWidth(t, 32.0, Unit.PX, 325.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(t, 14.0, Unit.PX, 70.0, Unit.PX);
		
		
	    t.setText(0, 0, "Username");
	    t.setText(0, 1, "High Score");
	    t.setText(0, 2, "Time");
	    t.setText(0, 3, "Experience");
	    t.setText(0, 4, "Level");
	    t.setText(0, 5, "Wins");
	    t.setText(0, 6, "Losses ");
	    t.setText(1, 0, "Alice");
	    updateHighScoreList();
	    t.setBorderWidth(1);
	    
	    // DEBUG testing values to be deleted later
	    Label lbltestHigh = new Label("testHigh");
	    layoutPanel.add(lbltestHigh);
	    layoutPanel.setWidgetLeftWidth(lbltestHigh, 20.0, Unit.PX, 56.0, Unit.PX);
	    layoutPanel.setWidgetTopHeight(lbltestHigh, 88.0, Unit.PX, 18.0, Unit.PX);
	    
	    Label lbltestLow = new Label("testLow");
	    layoutPanel.add(lbltestLow);
	    layoutPanel.setWidgetLeftWidth(lbltestLow, 20.0, Unit.PX, 56.0, Unit.PX);
	    layoutPanel.setWidgetTopHeight(lbltestLow, 119.0, Unit.PX, 18.0, Unit.PX);
		
	}
	
	
	// DEBUG for those not named Nick, ignore this function for now
	private void updateHighScoreList() {
		RPC.userService.getUserProfile("alice", new AsyncCallback<UserProfile>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("ERROR alice");
			}

			@Override
			public void onSuccess(UserProfile result) {
				// TODO Auto-generated method stub
				System.out.println(result.getHighScore());
				System.out.println(result.getName());
			}
			
		});
	}
}