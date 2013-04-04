package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class RegisterView extends Composite {

	public RegisterView(){
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		
		InlineLabel usernameRegisterLabel = new InlineLabel("  Username ");
		layoutPanel.add(usernameRegisterLabel);
		layoutPanel.setWidgetLeftWidth(usernameRegisterLabel, 12.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(usernameRegisterLabel, 57.0, Unit.PX, 18.0, Unit.PX);
		
		InlineLabel registerHeadingLabel = new InlineLabel("Create Your New Account");
		layoutPanel.add(registerHeadingLabel);
		layoutPanel.setWidgetLeftWidth(registerHeadingLabel, 151.0, Unit.PX, 158.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerHeadingLabel, 22.0, Unit.PX, 18.0, Unit.PX);
		
		TextBox registerUsernameTextBox = new TextBox();
		layoutPanel.add(registerUsernameTextBox);
		layoutPanel.setWidgetLeftWidth(registerUsernameTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerUsernameTextBox, 49.0, Unit.PX, 26.0, Unit.PX);
		
		InlineLabel registerPasswordLabel = new InlineLabel("Password");
		layoutPanel.add(registerPasswordLabel);
		layoutPanel.setWidgetLeftWidth(registerPasswordLabel, 12.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerPasswordLabel, 91.0, Unit.PX, 18.0, Unit.PX);
		
		InlineLabel nlnlblConfirmPassword = new InlineLabel("Confirm Password");
		layoutPanel.add(nlnlblConfirmPassword);
		layoutPanel.setWidgetLeftWidth(nlnlblConfirmPassword, 12.0, Unit.PX, 115.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(nlnlblConfirmPassword, 138.0, Unit.PX, 18.0, Unit.PX);
		
		InlineLabel registerEmailLabel = new InlineLabel("Email");
		layoutPanel.add(registerEmailLabel);
		layoutPanel.setWidgetLeftWidth(registerEmailLabel, 12.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerEmailLabel, 183.0, Unit.PX, 18.0, Unit.PX);
		
		TextBox registerEmailTextBox = new TextBox();
		layoutPanel.add(registerEmailTextBox);
		layoutPanel.setWidgetLeftWidth(registerEmailTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerEmailTextBox, 183.0, Unit.PX, 26.0, Unit.PX);
		
		Label WarningMessageLabel = new Label("Password and Confirmation Password must match.");
		layoutPanel.add(WarningMessageLabel);
		layoutPanel.setWidgetLeftWidth(WarningMessageLabel, 80.0, Unit.PX, 305.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(WarningMessageLabel, 229.0, Unit.PX, 18.0, Unit.PX);
		
		Button RegisterButton = new Button("New button");
		RegisterButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				//implement event handler
			}
		});
		RegisterButton.setText("Register!");
		layoutPanel.add(RegisterButton);
		layoutPanel.setWidgetLeftWidth(RegisterButton, 174.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(RegisterButton, 253.0, Unit.PX, 30.0, Unit.PX);
		
		PasswordTextBox passwordRegisterTextBox = new PasswordTextBox();
		layoutPanel.add(passwordRegisterTextBox);
		layoutPanel.setWidgetLeftWidth(passwordRegisterTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(passwordRegisterTextBox, 91.0, Unit.PX, 26.0, Unit.PX);
		
		PasswordTextBox confirmationPasswordRegisterTextBox = new PasswordTextBox();
		layoutPanel.add(confirmationPasswordRegisterTextBox);
		layoutPanel.setWidgetLeftWidth(confirmationPasswordRegisterTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(confirmationPasswordRegisterTextBox, 138.0, Unit.PX, 26.0, Unit.PX);
		
	}
}
