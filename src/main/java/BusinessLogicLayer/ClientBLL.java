package BusinessLogicLayer;

import BusinessLogicLayer.Validators.*;
import DataAccessObject.ClientDAO;
import Model.Client;
import java.util.List;

public class ClientBLL {
    private final ClientDAO clientDAO;
    private final List<Validator<Client>> validators;
    public ClientBLL()
    {
        clientDAO=new ClientDAO();
        validators=List.of(new NameValidator(), new EmailValidators(), new AddressValidator());
    }
    public Client insert(Client client)
    {
        validators.forEach(v->v.validate(client));
        return clientDAO.insert(client);
    }
    public Client update(Client client)
    {
        validators.forEach(v->v.validate(client));
        return clientDAO.update(client);
    }
    public List<Client> findAll()
    {
        return clientDAO.findAll();
    }
    public Client findById(int id)
    {
        return clientDAO.findById(id);
    }
    public void delete(Client client)
    {
        clientDAO.delete(client);
    }
}
