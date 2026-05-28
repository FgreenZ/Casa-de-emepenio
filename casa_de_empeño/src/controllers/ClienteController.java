package controllers;

import models.Cliente;
import models.ClienteModel;

import java.util.ArrayList;

public class ClienteController {

    ClienteModel model;

    public ClienteController() {

        model =
            new ClienteModel();
    }

    public ArrayList<Cliente> getClientes() {

        return model.getClientes();
    }
    public boolean agregarCliente(
    	    String nombre,
    	    String telefono,
    	    String correo,
    	    String fecha
    	) {

    	    return model.agregarCliente(
    	        nombre,
    	        telefono,
    	        correo,
    	        fecha
    	    );
    	}
    public boolean eliminarCliente(int idCliente) {

        return model.eliminarCliente(idCliente);
    }

    public boolean actualizarCliente(
    	    int idCliente,
    	    String nombre,
    	    String telefono,
    	    String correo,
    	    String fecha
    	) {

    	    return model.actualizarCliente(
    	        idCliente,
    	        nombre,
    	        telefono,
    	        correo,
    	        fecha
    	    );
    }
}