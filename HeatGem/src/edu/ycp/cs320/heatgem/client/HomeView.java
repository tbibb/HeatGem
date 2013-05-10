package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Image;


public class HomeView extends Composite {

	/**
	 * @param args
	 */
	
	
	private Timer timer;	
	private Image candle;
	private Image candle2;
	private Image black_box;
	private Image black_box2;
	private Image text;
	
	
	public HomeView() {
		
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("800px", "480px");
		

		
		candle = new Image(); 		
		layoutPanel.add(candle);
		layoutPanel.setWidgetLeftWidth(candle, 600.0, Unit.PX, 200.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(candle, 330.0, Unit.PX, 150.0, Unit.PX);
		
		candle2 = new Image(); 		
		layoutPanel.add(candle2);
		layoutPanel.setWidgetLeftWidth(candle2, 000.0, Unit.PX, 200.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(candle2, 330.0, Unit.PX, 150.0, Unit.PX);
		
		black_box = new Image();		
		layoutPanel.add(black_box);
		layoutPanel.setWidgetLeftWidth(black_box, 600.0, Unit.PX, 200.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(black_box, 0.0, Unit.PX, 330.0, Unit.PX);
		
		black_box2 = new Image();
		layoutPanel.add(black_box2);
		layoutPanel.setWidgetLeftWidth(black_box2, 0.0, Unit.PX, 200.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(black_box2, 0.0, Unit.PX, 330.0, Unit.PX);
		
		text = new Image();
		layoutPanel.add(text);
		layoutPanel.setWidgetLeftWidth(text, 200.0, Unit.PX, 400.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(text, 0.0, Unit.PX, 480.0, Unit.PX);
				
				this.timer = new Timer() {
					@Override
					public void run() {
						Draw();
					}
				}; 
	}
	
				public void startScroll() {
					GWT.log("setScroll called");
									

					candle.setUrl(GWT.getModuleBaseForStaticFiles() + "Candle.gif");
					candle2.setUrl(GWT.getModuleBaseForStaticFiles() + "Candle.gif");
					black_box.setUrl(GWT.getModuleBaseForStaticFiles() + "black_box.png");
					black_box2.setUrl(GWT.getModuleBaseForStaticFiles() + "black_box.png");
					text.setUrl(GWT.getModuleBaseForStaticFiles() + "heat_gem_vid.gif");
					

					timer.scheduleRepeating(1000/10);
				}
				

				
				
				

				protected void Draw() {

				
				}
}