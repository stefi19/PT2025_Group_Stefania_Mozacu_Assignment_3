package BusinessLogicLayer.Validators;

import Model.Client;
/**
 * Validator for the Client name.
 * Checks that the Client name is not null or empty.
 */
public class NameValidator implements Validator<Client> {
    /**
     * Validates the name field of a Client.
     * @param client the Client to validate
     */
    @Override
    public void validate(Client client)
    {
        if (client.getName()==null||client.getName().trim().isEmpty())
        {
            throw new IllegalArgumentException("Client name cannot be empty!");
        }
    }
}
