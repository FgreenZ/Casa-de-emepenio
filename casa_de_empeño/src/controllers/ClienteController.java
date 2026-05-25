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
}