package businessLayer;

import dataAccessLayer.ClientDAO;
import model.Client;
import java.util.List;

public class ClientService {

    private final ClientDAO clientDAO;

    public ClientService() {
        clientDAO = new ClientDAO();
    }

    public void addClient(Client client) {
        clientDAO.addClient(client);
    }

    public void updateClient(Client client) {
        clientDAO.updateClient(client);
    }

    public void deleteClient(int id) {
        clientDAO.deleteClient(id);
    }

    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    public Client findClientById(int id) {
        return clientDAO.findClientById(id);
    }

}
