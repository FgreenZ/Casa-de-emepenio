package controllers;

import views.HomeView;

public class HomeController {
	
	private HomeView hv;
	
	public HomeController() {
		hv = new HomeView();
	}
	
	public void home()
	{
		hv.home();
	}

}
