package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

import edu.ycp.cs320.heatgem.shared.User;

public class LoginView extends Composite {
	private TextBox usernameTextBox;
	private TextBox passwordTextBox;
	private Label errorLabel;
	public LoginView() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		
		InlineLabel usernameLabel = new InlineLabel("Username:");
		layoutPanel.add(usernameLabel);
		layoutPanel.setWidgetLeftWidth(usernameLabel, 24.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(usernameLabel, 54.0, Unit.PX, 18.0, Unit.PX);
		
		usernameTextBox = new TextBox();
		layoutPanel.add(usernameTextBox);
		layoutPanel.setWidgetLeftWidth(usernameTextBox, 120.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(usernameTextBox, 43.0, Unit.PX, 34.0, Unit.PX);
		//String username =  usernameTextBox.toString();
		
		InlineLabel passwordLabel = new InlineLabel("Password");
		layoutPanel.add(passwordLabel);
		layoutPanel.setWidgetLeftWidth(passwordLabel, 24.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(passwordLabel, 108.0, Unit.PX, 18.0, Unit.PX);
		
		passwordTextBox = new TextBox();
		layoutPanel.add(passwordTextBox);
		layoutPanel.setWidgetLeftWidth(passwordTextBox, 120.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(passwordTextBox, 97.0, Unit.PX, 34.0, Unit.PX);
		
		SimpleCheckBox simpleCheckBox = new SimpleCheckBox();
		layoutPanel.add(simpleCheckBox);
		layoutPanel.setWidgetLeftWidth(simpleCheckBox, 55.0, Unit.PX, 20.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(simpleCheckBox, 226.0, Unit.PX, 19.0, Unit.PX);
		
		InlineLabel rememberMeNextLabel = new InlineLabel("Remember me next time.");
		layoutPanel.add(rememberMeNextLabel);
		layoutPanel.setWidgetLeftWidth(rememberMeNextLabel, 120.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(rememberMeNextLabel, 226.0, Unit.PX, 18.0, Unit.PX);
		
		Button loginNewButton = new Button("New button");
		loginNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//send username and password to server and validate login
				
				handleLogin();
				
			}
		});
		loginNewButton.setText("Login!");
		layoutPanel.add(loginNewButton);
		layoutPanel.setWidgetLeftWidth(loginNewButton, 133.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(loginNewButton, 174.0, Unit.PX, 30.0, Unit.PX);
		
		Label headerLabel = new Label("Login");
		headerLabel.setStyleName("h1");
		layoutPanel.add(headerLabel);
		layoutPanel.setWidgetLeftWidth(headerLabel, 168.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(headerLabel, 19.0, Unit.PX, 18.0, Unit.PX);
		
		errorLabel = new Label("");
		layoutPanel.add(errorLabel);
		layoutPanel.setWidgetLeftWidth(errorLabel, 120.0, Unit.PX, 307.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(errorLabel, 259.0, Unit.PX, 18.0, Unit.PX);
	}
	
	protected void handleLogin() {
		String username = this.usernameTextBox.getText();
		String password = this.passwordTextBox.getText();
		
		RPC.userService.logIn(username, password, new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				// show error message
				errorLabel.setText("Could not communicate with server?");
			}

			@Override
			public void onSuccess(User result) {
				if (result == null) {
					// show error message
					errorLabel.setText("Unknown username/password");
				} else {
					// TODO: switch to home page
					errorLabel.setText("Success! Should switch to home page");
					HeatGem.setView(new HomePage());
				}
			}
		});
	}
}
