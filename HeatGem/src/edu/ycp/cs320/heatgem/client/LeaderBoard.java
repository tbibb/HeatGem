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

	UserProfile[] list = new UserProfile[10];
	int size;
	private String username;
	private FlexTable t = new FlexTable();
	
	public LeaderBoard(){
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		
		layoutPanel.add(t);
		layoutPanel.setWidgetLeftWidth(t, 32.0, Unit.PX, 325.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(t, 14.0, Unit.PX, 70.0, Unit.PX);
		
		
	    t.setText(0, 0, "Username");
	    t.setText(0, 1, "High Score");
	    t.setText(0, 2, "Time");
	    t.setText(0, 3, "Experience");
	    t.setText(0, 4, "Level");
	    t.setText(0, 5, "Wins");
	    t.setText(0, 6, "Losses");
	 
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
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	// DEBUG for those not named Nick, ignore this function for now
	private void updateHighScoreList() {
		RPC.userService.getAmountUsers( new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("ERROR getAmountUsers");
			}

			@Override
			public void onSuccess(Integer result) {
				// TODO Auto-generated method stub
				
				t.setText(1, 0, username);
				System.out.println("Size = " + size);
				System.out.println("Result = " + result);
				
			}
			
		});
	}
	
//	RPC.userService.findUserProfileByUserId(i, new AsyncCallback<UserProfile>() {
//	@Override
//	public void onFailure(Throwable caught) {
//		// TODO Auto-generated method stub
//		System.out.println("ERROR findUserProfileByUserId, i = " + i);
//	}
//
//	@Override
//	public void onSuccess(UserProfile result) {
//		// TODO Auto-generated method stub
//		list[i] = result;
//		
//		System.out.println("i = " + i);
//
//		System.out.println(list[i].getHighScore());
//		System.out.println(list[i].getName());
//		System.out.println(list[i].getLevel());
//		System.out.println(list[i].getUserId());
//	}
//	
//});

}

//// DEBUG for those not named Nick, ignore this function for now
//private void updateHighScoreList() {
//RPC.userService.getUserProfile("alice", new AsyncCallback<UserProfile>() {
//
//	@Override
//	public void onFailure(Throwable caught) {
//		// TODO Auto-generated method stub
//		System.out.println("ERROR alice");
//	}
//
//	@Override
//	public void onSuccess(UserProfile result) {
//		// TODO Auto-generated method stub
//		System.out.println(result.getHighScore());
//		System.out.println(result.getName());
//		System.out.println(result.getLevel());
//		System.out.println(result.getUserId());
//	}
//	
//});
//}
	
	
	
	
