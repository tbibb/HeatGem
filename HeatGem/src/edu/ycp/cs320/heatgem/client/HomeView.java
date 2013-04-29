package edu.ycp.cs320.heatgem.client;

//import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;

import com.google.gwt.dom.client.Style.Unit;

//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.user.cellview.client.CellList;
//import com.google.gwt.cell.client.AbstractCell;
//import com.google.gwt.cell.client.Cell.Context;
//import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
//import com.google.gwt.user.client.ui.Image;

import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBoxBase;

public class HomeView extends Composite {

	/**
	 * @param args
	 */
	public HomeView() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("629px", "516px");
		
		TextArea txtrHeatGemIs = new TextArea();
		txtrHeatGemIs.setDirectionEstimator(true);
		txtrHeatGemIs.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		txtrHeatGemIs.setText("Heat Gem is a great game site, but is currently under construction.\r\n\r\n\r\nMore coming soon....");
		layoutPanel.add(txtrHeatGemIs);
		layoutPanel.setWidgetLeftWidth(txtrHeatGemIs, 15.0, Unit.PX, 458.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(txtrHeatGemIs, 28.0, Unit.PX, 346.0, Unit.PX);

	}
}
