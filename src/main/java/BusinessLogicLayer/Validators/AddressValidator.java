package BusinessLogicLayer.Validators;

import Model.Client;
/**
 * Validator class to verify that the address field of a Client object is not null and has a minimum length of 5 characters.
 */
public class AddressValidator implements Validator<Client>
{
    /**
     * Validates the address field of the given Client object.
     * Throws IllegalArgumentException if the address is null or shorter than 5 characters (after it erases(trims) leading/trailing spaces).
     * @param client the Client object to validate
     */
    @Override
    public void validate(Client client)
    {
        if (client.getAddress()==null||client.getAddress().trim().length()<5)
        {
            throw new IllegalArgumentException("Address must be at least 5 characters long.");
        }
    }
}
