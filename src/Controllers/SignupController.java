package Controllers;

import Database.ClientDAO;
import MainClasses.Client;
import MainClasses.PhoneStore;

public class SignupController {
    private ClientDAO clientDAO = new ClientDAO();

    public String signup(String name, String email, String username, String password) {
        if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            return "MissingFields";
        }

        Client existingClient = clientDAO.getClientByUsername(username);
        if (existingClient != null) {
            return "UsernameTaken";
        }

        Client newClient = new Client(username, password, name, email, PhoneStore.getInstance());
        clientDAO.addClient(newClient);

        return "Success";
    }
}
