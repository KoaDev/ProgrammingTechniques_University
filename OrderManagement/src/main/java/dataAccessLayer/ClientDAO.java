package dataAccessLayer;

import model.Client;

import java.util.List;

/**
 * This class is used to access the client table from the database
 */
public class ClientDAO extends GenericDAO<Client> {

    /**
     * @return a list of all clients from the database
     */
    public List<Client> getAllClients() {
        return super.findAll();
    }

    /**
     * @param id the id of the client to be found
     * @return the client with the given id
     */
    public Client findClientById(int id) {
        return super.findById(id);
    }

    /**
     * @param client the client to be added to the database
     *              inserts the given client into the database
     */
    public void addClient(Client client) {
        super.insert(client);
    }

    /**
     * @param client the client to be updated
     *               updates the given client in the database
     */
    public void updateClient(Client client) {
        super.update(client);
    }

    /**
     * @param id the id of the client to be deleted
     *           deletes the client with the given id from the database
     */
    public void deleteClient(int id) {
        super.deleteById(id);
    }
}
