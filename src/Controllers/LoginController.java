package Controllers;

import Database.AdminDAO;
import Database.ClientDAO;
import MainClasses.Admin;
import MainClasses.Client;

public class LoginController {
    private ClientDAO clientDAO = new ClientDAO();
    private AdminDAO adminDAO = new AdminDAO();

    public Object login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return "MissingFields";
        }

        Client client = clientDAO.getClientByUsernameAndPassword(email, password);
        if (client != null) {
            return client;
        }

        Admin admin = adminDAO.getAdminByUsernameAndPassword(email, password);
        if (admin != null) {
            return admin;
        }

        return "InvalidCredentials";
    }
}
