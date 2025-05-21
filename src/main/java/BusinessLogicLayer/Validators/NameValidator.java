package BusinessLogicLayer.Validators;

import Model.Client;

public class NameValidator implements Validator<Client> {
    @Override
    public void validate(Client client)
    {
        if (client.getName()==null||client.getName().trim().isEmpty())
        {
            throw new IllegalArgumentException("Client name cannot be empty!");
        }
    }
}
