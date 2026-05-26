package application;

import controllers.AuthController;

public class Main {

	public static void main(String[] args) {
		
		AuthController auth = new AuthController(); 
		
		auth.login();
		
	}
}