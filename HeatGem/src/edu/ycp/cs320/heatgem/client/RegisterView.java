package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
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

import edu.ycp.cs320.heatgem.shared.User;

public class RegisterView extends Composite {
	private TextBox emailTextBox;
	private PasswordTextBox confirmRegisterPasswordTextBox;
	private PasswordTextBox passwordRegisterTextBox;
	private TextBox usernameRegisterTextBox;

	public RegisterView(){
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		
		InlineLabel usernameRegisterLabel = new InlineLabel("Username");
		layoutPanel.add(usernameRegisterLabel);
		layoutPanel.setWidgetLeftWidth(usernameRegisterLabel, 12.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(usernameRegisterLabel, 57.0, Unit.PX, 18.0, Unit.PX);
		
		InlineLabel registerHeadingLabel = new InlineLabel("Create A New Account");
		layoutPanel.add(registerHeadingLabel);
		layoutPanel.setWidgetLeftWidth(registerHeadingLabel, 146.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerHeadingLabel, 23.0, Unit.PX, 18.0, Unit.PX);
		
		InlineLabel registerPasswordLabel = new InlineLabel("Password");
		layoutPanel.add(registerPasswordLabel);
		layoutPanel.setWidgetLeftWidth(registerPasswordLabel, 12.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerPasswordLabel, 91.0, Unit.PX, 18.0, Unit.PX);
		
		InlineLabel ConfirmPassword = new InlineLabel("Confirm Password");
		layoutPanel.add(ConfirmPassword);
		layoutPanel.setWidgetLeftWidth(ConfirmPassword, 12.0, Unit.PX, 115.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(ConfirmPassword, 138.0, Unit.PX, 18.0, Unit.PX);
		
		InlineLabel registerEmailLabel = new InlineLabel("Email");
		layoutPanel.add(registerEmailLabel);
		layoutPanel.setWidgetLeftWidth(registerEmailLabel, 12.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerEmailLabel, 183.0, Unit.PX, 18.0, Unit.PX);
		
		Label WarningMessageLabel = new Label("Password and Confirmation Password must match.");
		layoutPanel.add(WarningMessageLabel);
		layoutPanel.setWidgetLeftWidth(WarningMessageLabel, 80.0, Unit.PX, 305.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(WarningMessageLabel, 229.0, Unit.PX, 18.0, Unit.PX);
		
		Button RegisterButton = new Button("New button");
		RegisterButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				//implement event handler
				handleRegister();
			}
		});
		RegisterButton.setText("Register!");
		layoutPanel.add(RegisterButton);
		layoutPanel.setWidgetLeftWidth(RegisterButton, 174.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(RegisterButton, 253.0, Unit.PX, 30.0, Unit.PX);
		
		usernameRegisterTextBox = new TextBox();
		layoutPanel.add(usernameRegisterTextBox);
		layoutPanel.setWidgetLeftWidth(usernameRegisterTextBox, 123.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(usernameRegisterTextBox, 57.0, Unit.PX, 30.0, Unit.PX);
		
		passwordRegisterTextBox = new PasswordTextBox();
		layoutPanel.add(passwordRegisterTextBox);
		layoutPanel.setWidgetLeftWidth(passwordRegisterTextBox, 122.0, Unit.PX, 174.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(passwordRegisterTextBox, 91.0, Unit.PX, 32.0, Unit.PX);
		
		confirmRegisterPasswordTextBox = new PasswordTextBox();
		layoutPanel.add(confirmRegisterPasswordTextBox);
		layoutPanel.setWidgetLeftWidth(confirmRegisterPasswordTextBox, 123.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(confirmRegisterPasswordTextBox, 129.0, Unit.PX, 32.0, Unit.PX);
		
		emailTextBox = new TextBox();
		layoutPanel.add(emailTextBox);
		layoutPanel.setWidgetLeftWidth(emailTextBox, 123.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(emailTextBox, 167.0, Unit.PX, 34.0, Unit.PX);
		
	}
	
	public void handleRegister(){
		String username = this.usernameRegisterTextBox.getText();
		String password = this.passwordRegisterTextBox.getText();
		String confirmPassword = this.confirmRegisterPasswordTextBox.getText();
		String email = this.emailTextBox.getText();
		
		
		RPC.userService.addUser(username, password, confirmPassword, email,  new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
			
			
			
		});
		
		
	}
}
