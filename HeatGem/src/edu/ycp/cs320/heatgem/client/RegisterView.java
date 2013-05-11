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
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;


public class RegisterView extends Composite {
	private TextBox registerUsernameTextBox;
	private PasswordTextBox confirmationPasswordRegisterTextBox;
	private TextBox registerEmailTextBox;
	private PasswordTextBox passwordRegisterTextBox;
	private Label errorLabel;
	private Boolean unique;

	public RegisterView(){
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("450px", "350px");
		
		InlineLabel registerHeadingLabel = new InlineLabel("Create A New Account");
		registerHeadingLabel.setStyleName("gwt-InlineLabel-redness");
		layoutPanel.add(registerHeadingLabel);
		layoutPanel.setWidgetLeftWidth(registerHeadingLabel, 146.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerHeadingLabel, 23.0, Unit.PX, 18.0, Unit.PX);
		
		InlineLabel usernameRegisterLabel = new InlineLabel("  Username ");
		usernameRegisterLabel.setStyleName("gwt-InlineLabel-redness");
		layoutPanel.add(usernameRegisterLabel);
		layoutPanel.setWidgetLeftWidth(usernameRegisterLabel, 12.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(usernameRegisterLabel, 57.0, Unit.PX, 18.0, Unit.PX);
		
		registerUsernameTextBox = new TextBox();
		registerUsernameTextBox.setStyleName("gwt-TextBox-login");
		layoutPanel.add(registerUsernameTextBox);
		layoutPanel.setWidgetLeftWidth(registerUsernameTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerUsernameTextBox, 49.0, Unit.PX, 26.0, Unit.PX);
		
		InlineLabel registerPasswordLabel = new InlineLabel("Password");
		registerPasswordLabel.setStyleName("gwt-InlineLabel-redness");
		layoutPanel.add(registerPasswordLabel);
		layoutPanel.setWidgetLeftWidth(registerPasswordLabel, 12.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerPasswordLabel, 91.0, Unit.PX, 18.0, Unit.PX);
		
		passwordRegisterTextBox = new PasswordTextBox();
		passwordRegisterTextBox.setStyleName("gwt-TextBox-login");
		layoutPanel.add(passwordRegisterTextBox);
		layoutPanel.setWidgetLeftWidth(passwordRegisterTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(passwordRegisterTextBox, 91.0, Unit.PX, 26.0, Unit.PX);
		
		InlineLabel ConfirmPassword = new InlineLabel("Confirm Password");
		ConfirmPassword.setStyleName("gwt-InlineLabel-redness");
		layoutPanel.add(ConfirmPassword);
		layoutPanel.setWidgetLeftWidth(ConfirmPassword, 12.0, Unit.PX, 115.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(ConfirmPassword, 138.0, Unit.PX, 18.0, Unit.PX);
		
		confirmationPasswordRegisterTextBox = new PasswordTextBox();
		confirmationPasswordRegisterTextBox.setStyleName("gwt-TextBox-login");
		layoutPanel.add(confirmationPasswordRegisterTextBox);
		layoutPanel.setWidgetLeftWidth(confirmationPasswordRegisterTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(confirmationPasswordRegisterTextBox, 138.0, Unit.PX, 26.0, Unit.PX);
		
		InlineLabel registerEmailLabel = new InlineLabel("Email");
		registerEmailLabel.setStyleName("gwt-InlineLabel-redness");
		layoutPanel.add(registerEmailLabel);
		layoutPanel.setWidgetLeftWidth(registerEmailLabel, 12.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerEmailLabel, 183.0, Unit.PX, 18.0, Unit.PX);
		
		registerEmailTextBox = new TextBox();
		registerEmailTextBox.setStyleName("gwt-TextBox-login");
		registerEmailTextBox.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
					handleRegister();
				}
			}
		});
		layoutPanel.add(registerEmailTextBox);
		layoutPanel.setWidgetLeftWidth(registerEmailTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerEmailTextBox, 183.0, Unit.PX, 26.0, Unit.PX);
		
		Label WarningMessageLabel = new Label("Password and Confirmation Password must match.");
		WarningMessageLabel.setStyleName("gwt-InlineLabel-redness");
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
		
		errorLabel = new Label("");
		errorLabel.setStyleName("gwt-Label-alert");
		layoutPanel.add(errorLabel);
		layoutPanel.setWidgetLeftWidth(errorLabel, 80.0, Unit.PX, 289.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(errorLabel, 289.0, Unit.PX, 30.0, Unit.PX);
		
		Label goBackLabel = new Label("Go Back To Login");
		goBackLabel.setStyleName("gwt-Label-hyperlink");
		goBackLabel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				//implement event handler
				handleGoBack();
			}
		});
		
		layoutPanel.add(goBackLabel);
		layoutPanel.setWidgetLeftWidth(goBackLabel, 12.0, Unit.PX, 128.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(goBackLabel, 11.0, Unit.PX, 40.0, Unit.PX);
		
	}
	
	public void handleGoBack(){
		LoginView view = new LoginView();
		HeatGem.setView(view);
	}
	
	
	public void handleRegister(){
		String username = this.registerUsernameTextBox.getText();
		final String password = this.passwordRegisterTextBox.getText();
		final String confirmPassword = this.confirmationPasswordRegisterTextBox.getText();
		String email = this.registerEmailTextBox.getText();
		unique = true;

		//set this to true when username is found to be unique
		
//		RPC.userService.uniqueUser(username, new AsyncCallback<Boolean>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onSuccess(Boolean result) {
//				// TODO Auto-generated method stub
//				unique = result;
//			}
//		});
		
		//TO-DO: prevent registering blank user and other blank text boxes
		if(username.equals("") || password.equals("") || confirmPassword.equals("") || email.equals("")){
			errorLabel.setText("Must fill in the above fields. Try again.");
		} else if (unique == false) {
			errorLabel.setText("Error: Username already exists");
		} else if(password.equals(confirmPassword)){
			// add user
			RPC.userService.addUser(username, password, confirmPassword, email,  new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					// show error message
					errorLabel.setText("Could not communicate with server?");
					
				}

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					
					
					// if username entered during registration is already taken by another user in database
					//else if(username == ){
						//errorLabel.setText("Username already taken, pick a new one.");
					//} 
					
					//if the email entered during registration is already in use in database, show error message
					//else if(email == ) {
						//errorLabel.setText("Email already in use. Try again.");
					//}
					
				
						//Create new row in user table of database
						
						
						//register successful, change to login view
						LoginView view = new LoginView();
						HeatGem.setView(view);
				
				}
				
			});
			
		} else {
			//password and confirmation password entered during registration do not match
			errorLabel.setText("Incorrect password-confirmation password combination. Try again.");
			
		}
		
		
		
	}
}
