package BusinessLogicLayer;

import BusinessLogicLayer.Validators.*;
import DataAccessObject.ClientDAO;
import Model.Client;
import java.util.List;
/**
 * Business logic layer for operations with the Client class.
 */
public class ClientBLL {
    private final ClientDAO clientDAO;
    private final List<Validator<Client>> validators;
    /**
     * The constructor to initialize DAO and validators.
     */
    public ClientBLL()
    {
        clientDAO=new ClientDAO();
        validators=List.of(new NameValidator(), new EmailValidators(), new AddressValidator());
    }

    /**
     * If validated, it inserts a client in the database
     * @param client to insert
     * @return inserted client
     */
    public Client insert(Client client)
    {
        validators.forEach(v->v.validate(client));
        return clientDAO.insert(client);
    }
    /**
     * If validated, it updates a client in the database
     * @param client to update
     * @return updated client
     */
    public Client update(Client client)
    {
        validators.forEach(v->v.validate(client));
        return clientDAO.update(client);
    }

    /**
     * Returns all clients.
     * @return a list of all clients
     */
    public List<Client> findAll()
    {
        return clientDAO.findAll();
    }
    /**
     * Finds client by its id in the database.
     * @param id client id
     * @return client with given id
     */
    public Client findById(int id)
    {
        return clientDAO.findById(id);
    }
    /**
     * Deletes the client.
     * @param client to delete
     */
    public void delete(Client client)
    {
        clientDAO.delete(client);
    }
}
