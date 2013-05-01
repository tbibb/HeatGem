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
import com.google.gwt.event.dom.client.KeyEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;


public class RegisterView extends Composite {
	private TextBox registerUsernameTextBox;
	private PasswordTextBox confirmationPasswordRegisterTextBox;
	private TextBox registerEmailTextBox;
	private PasswordTextBox passwordRegisterTextBox;
	private Label errorLabel;

	public RegisterView(){
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("450px", "350px");
		
		InlineLabel usernameRegisterLabel = new InlineLabel("  Username ");
		layoutPanel.add(usernameRegisterLabel);
		layoutPanel.setWidgetLeftWidth(usernameRegisterLabel, 12.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(usernameRegisterLabel, 57.0, Unit.PX, 18.0, Unit.PX);
		
		InlineLabel registerHeadingLabel = new InlineLabel("Create A New Account");
		layoutPanel.add(registerHeadingLabel);
		layoutPanel.setWidgetLeftWidth(registerHeadingLabel, 146.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerHeadingLabel, 23.0, Unit.PX, 18.0, Unit.PX);
		
		registerUsernameTextBox = new TextBox();
		layoutPanel.add(registerUsernameTextBox);
		layoutPanel.setWidgetLeftWidth(registerUsernameTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerUsernameTextBox, 49.0, Unit.PX, 26.0, Unit.PX);
		
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
		
		registerEmailTextBox = new TextBox();
		layoutPanel.add(registerEmailTextBox);
		layoutPanel.setWidgetLeftWidth(registerEmailTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(registerEmailTextBox, 183.0, Unit.PX, 26.0, Unit.PX);
		
		Label WarningMessageLabel = new Label("Password and Confirmation Password must match.");
		layoutPanel.add(WarningMessageLabel);
		layoutPanel.setWidgetLeftWidth(WarningMessageLabel, 80.0, Unit.PX, 305.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(WarningMessageLabel, 229.0, Unit.PX, 18.0, Unit.PX);
		
		Button RegisterButton = new Button("New button");
		RegisterButton.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				
				//pressing enter key to register
			}       
		        
		});
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
		
		passwordRegisterTextBox = new PasswordTextBox();
		layoutPanel.add(passwordRegisterTextBox);
		layoutPanel.setWidgetLeftWidth(passwordRegisterTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(passwordRegisterTextBox, 91.0, Unit.PX, 26.0, Unit.PX);
		
		confirmationPasswordRegisterTextBox = new PasswordTextBox();
		layoutPanel.add(confirmationPasswordRegisterTextBox);
		layoutPanel.setWidgetLeftWidth(confirmationPasswordRegisterTextBox, 136.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(confirmationPasswordRegisterTextBox, 138.0, Unit.PX, 26.0, Unit.PX);
		
		errorLabel = new Label("");
		layoutPanel.add(errorLabel);
		layoutPanel.setWidgetLeftWidth(errorLabel, 80.0, Unit.PX, 289.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(errorLabel, 289.0, Unit.PX, 18.0, Unit.PX);
		
		Label goBackLabel = new Label("Go Back To Login");
		goBackLabel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				//implement event handler
				handleGoBack();
			}
		});
		
		layoutPanel.add(goBackLabel);
		layoutPanel.setWidgetLeftWidth(goBackLabel, 12.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(goBackLabel, 11.0, Unit.PX, 18.0, Unit.PX);
		
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
		
		
		//TO-DO: prevent registering blank user and other blank text boxes
		
		
		
		RPC.userService.addUser(username, password, confirmPassword, email,  new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// show error message
				errorLabel.setText("Could not communicate with server?");
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				if(password != confirmPassword){
					
					errorLabel.setText("Incorrect username/password/email. Try again.");
				} else{
					//register successful, change to login view
					
					LoginView view = new LoginView();
					HeatGem.setView(view);
				}
			}
			
			
			
			
		});
		
		
	}
}
