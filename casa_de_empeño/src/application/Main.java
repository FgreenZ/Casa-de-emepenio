package application;

import controllers.AuthController;

public class Main {

	public static void main(String[] args) {
		
		AuthController auth = new AuthController(); 
		auth.articulos();
	}
}
/*
package application;

import controllers.AuthController;
import controllers.MainController;
import views.AuthView;
import views.HomeView;
import views.MainFrame;

public class Main {

	public static void main(String[] args) {
		
		MainFrame frame = new MainFrame();
		AuthView login = new AuthView();
		HomeView home = new HomeView();

		//frame.agregarPanel(login, "LOGIN");
		frame.agregarPanel(home, "HOME");

		//MainController control = new MainController(frame, login, home);
		frame.setVisible(true);
	}
}
*/