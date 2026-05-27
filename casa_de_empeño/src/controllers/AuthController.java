package controllers;

import views.AuthView;
import views.HomeView;

import models.AuthModel;
public class AuthController {
	private AuthModel model;
	private AuthView vista;
	private HomeView prueba;

	
	public AuthController() {

	    vista = new AuthView();
	    model = new AuthModel();

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
	public boolean crearCuenta(
		    String usuario,
		    String correo,
		    String contrasena,
		    String rol
		){

		    return model.crearCuenta(
		        usuario,
		        correo,
		        contrasena,
		        rol
		    );
		}
	
}
