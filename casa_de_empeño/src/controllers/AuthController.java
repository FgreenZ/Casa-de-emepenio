package controllers;

import views.AuthView;
import views.HomeView;

public class AuthController {

	private AuthView vista;
	private HomeView prueba;

	
	public AuthController() {
		
		vista = new AuthView();
		prueba = new HomeView();
	}
	
	public void login()
	{
		vista.VentanaLogin();
	}
	
	public void dash()
	{
		prueba.dashboardPagos();
	}
	
	public void articulos()
	{
		prueba.dashboardArticulos();
	}
	
}
