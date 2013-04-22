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
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;

import edu.ycp.cs320.heatgem.shared.User;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class LoginView extends Composite {
	private TextBox usernameTextBox;
	private TextBox passwordTextBox;
	private SimpleCheckBox simpleCheckBox;	
	private InlineLabel errorLabel;
	public LoginView() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("433px", "395px");
		
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
		passwordTextBox.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
					handleLogin();
				}
			}
		});
		layoutPanel.add(passwordTextBox);
		layoutPanel.setWidgetLeftWidth(passwordTextBox, 120.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(passwordTextBox, 97.0, Unit.PX, 34.0, Unit.PX);
		
		simpleCheckBox = new SimpleCheckBox();
		layoutPanel.add(simpleCheckBox);
		
		
		layoutPanel.setWidgetLeftWidth(simpleCheckBox, 74.0, Unit.PX, 20.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(simpleCheckBox, 290.0, Unit.PX, 19.0, Unit.PX);
		
		if (simpleCheckBox.getValue() == true) {
			GWT.log("Checkbox is checked");
			System.out.println("Hi");
		
		}
		
		InlineLabel rememberMeNextLabel = new InlineLabel("Remember me next time.");
		layoutPanel.add(rememberMeNextLabel);
		layoutPanel.setWidgetLeftWidth(rememberMeNextLabel, 139.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(rememberMeNextLabel, 290.0, Unit.PX, 18.0, Unit.PX);
		
		Button loginNewButton = new Button("New button");
		loginNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//send username and password to server and validate login
				
				handleLogin();
				
			}		
		});
		
		
		
		loginNewButton.setText("Login!");
		layoutPanel.add(loginNewButton);
		layoutPanel.setWidgetLeftWidth(loginNewButton, 155.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(loginNewButton, 166.0, Unit.PX, 30.0, Unit.PX);
		
		Label headerLabel = new Label("Login");
		headerLabel.setStyleName("h1");
		layoutPanel.add(headerLabel);
		layoutPanel.setWidgetLeftWidth(headerLabel, 168.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(headerLabel, 19.0, Unit.PX, 18.0, Unit.PX);
		
		Button btnCreateAccount = new Button("Create Account");
		layoutPanel.add(btnCreateAccount);
		layoutPanel.setWidgetLeftWidth(btnCreateAccount, 133.0, Unit.PX, 126.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnCreateAccount, 232.0, Unit.PX, 30.0, Unit.PX);
		btnCreateAccount.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				
				//HomePage view = new HomePage();
				AddUserView view = new AddUserView();
				HeatGem.setView(view);
				view.activate();
				
			}		
		});
		
		Label lblOr = new Label("or");
		lblOr.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel.add(lblOr);
		layoutPanel.setWidgetLeftWidth(lblOr, 155.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblOr, 202.0, Unit.PX, 24.0, Unit.PX);
		
		errorLabel = new InlineLabel("");
		layoutPanel.add(errorLabel);
		layoutPanel.setWidgetLeftRight(errorLabel, 10.0, Unit.PX, 11.0, Unit.PX);
		layoutPanel.setWidgetBottomHeight(errorLabel, 43.0, Unit.PX, 18.0, Unit.PX);
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
					HomePage view = new HomePage();
					HeatGem.setView(view);
					view.activate(); // do any required dynamic initialization
				}
			}
		});
	}
}
// hi