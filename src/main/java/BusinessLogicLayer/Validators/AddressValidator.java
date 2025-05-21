package BusinessLogicLayer.Validators;

import Model.Client;

public class AddressValidator implements Validator<Client>
{
    @Override
    public void validate(Client client)
    {
        if (client.getAddress()==null||client.getAddress().trim().length()<5)
        {
            throw new IllegalArgumentException("Address must be at least 5 characters long.");
        }
    }
}
