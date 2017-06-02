package com.cardboard.reviews.service;

public class ServiceFactory {

	private static UserService userService;
	
	public static void setUserService(UserService userService) {
		ServiceFactory.userService = userService;
	}
	
	public static UserService getUserService() {
		return userService;
	}
}
