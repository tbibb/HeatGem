package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import edu.ycp.cs320.heatgem.shared.User;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;

public class LoginView extends Composite {
	private TextBox usernameTextBox;
	private Label errorLabel;
	private Image h_gem;
	private PasswordTextBox passwordLoginTextBox;
	private Button loginButton;
	public LoginView() {

		LayoutPanel layoutPanel = new LayoutPanel();
		layoutPanel.setStyleName("gwt-Label-hyperlink");
		initWidget(layoutPanel);
		layoutPanel.setSize("442px", "619px");

		InlineLabel usernameLabel = new InlineLabel("Username:");
		usernameLabel.setStyleName("gwt-InlineLabel-redness");
		layoutPanel.add(usernameLabel);
		layoutPanel.setWidgetLeftWidth(usernameLabel, 55.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(usernameLabel, 309.0, Unit.PX, 18.0, Unit.PX);

		usernameTextBox = new TextBox();
		usernameTextBox.setStyleName("gwt-TextBox-login");
		layoutPanel.add(usernameTextBox);
		usernameTextBox.setSize("207", "44");
		layoutPanel.setWidgetLeftWidth(usernameTextBox, 151.0, Unit.PX, 207.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(usernameTextBox, 309.0, Unit.PX, 55.0, Unit.PX);

		InlineLabel passwordLabel = new InlineLabel("Password:");
		passwordLabel.setStyleName("gwt-InlineLabel-redness");
		layoutPanel.add(passwordLabel);
		layoutPanel.setWidgetLeftWidth(passwordLabel, 55.0, Unit.PX, 207.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(passwordLabel, 358.0, Unit.PX, 55.0, Unit.PX);
	
		passwordLoginTextBox = new PasswordTextBox();
		passwordLoginTextBox.setStyleName("gwt-TextBox-login");
		passwordLoginTextBox.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
					handleLogin();
				}
			}
		});
		layoutPanel.add(passwordLoginTextBox);
		layoutPanel.setWidgetLeftWidth(passwordLoginTextBox, 151.0, Unit.PX, 207.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(passwordLoginTextBox, 358.0, Unit.PX, 44.0, Unit.PX);
		
//		if (rememberCheckBox.getValue() == true) {
//			GWT.log("Checkbox is checked");
//			System.out.println("Hi");
//
//		}
		
		errorLabel = new Label("");
		errorLabel.setStyleName("gwt-Label-alert");
		layoutPanel.add(errorLabel);
		layoutPanel.setWidgetLeftWidth(errorLabel, 120.0, Unit.PX, 307.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(errorLabel, 259.0, Unit.PX, 30.0, Unit.PX);

		h_gem = HeatGem.getImage("fireRuby1.gif");
		layoutPanel.add(h_gem);
		layoutPanel.setWidgetLeftWidth(h_gem, 66.0, Unit.PX, 260.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(h_gem, 15.0, Unit.PX, 245.0, Unit.PX);
		
		loginButton = new Button("Login!");
		layoutPanel.add(loginButton);
		layoutPanel.setWidgetLeftWidth(loginButton, 192.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(loginButton, 426.0, Unit.PX, 30.0, Unit.PX);
		
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//send username and password to server and validate login

				handleLogin();

			}		
		});
		
		
		Label lblNewUserClick = new Label("New user? Click here to register!");
		layoutPanel.add(lblNewUserClick);
		layoutPanel.setWidgetLeftWidth(lblNewUserClick, 120.0, Unit.PX, 249.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblNewUserClick, 483.0, Unit.PX, 44.0, Unit.PX);

		lblNewUserClick.addMouseMoveHandler(new MouseMoveHandler() {
			public void onMouseMove(MouseMoveEvent event) {
				//change color of text for when mouse hovers over link
			}
		});
		lblNewUserClick.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				handleRegister();
			}

		});

	}

	protected void handleRegister() {
		RegisterView view = new RegisterView();
		HeatGem.setView(view);
	}
	
	protected void handleLogin() {
		String username = this.usernameTextBox.getText();
		String password = this.passwordLoginTextBox.getText();

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
					//  switches to home page
					errorLabel.setText("Success!");
					HomePage view = new HomePage();
					view.setUser(result);
					HeatGem.setView(view);
					view.activate(); // do any required dynamic initialization
				}
			}
		});
	}
	
	
}
