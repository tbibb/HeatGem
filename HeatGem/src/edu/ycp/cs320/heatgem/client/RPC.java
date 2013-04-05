package edu.ycp.cs320.heatgem.client;

import com.google.gwt.core.shared.GWT;

public class RPC {
	public static final UserServiceAsync userService = GWT.create(UserService.class);
}
