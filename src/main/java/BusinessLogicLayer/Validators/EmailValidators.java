package BusinessLogicLayer.Validators;
import Model.Client;
import java.util.regex.Pattern;
/**
 * Validator class to verify the email address format of a Client object.
 */
public class EmailValidators implements Validator<Client>{
    /**
     * The pattern to validate an email address format.
     */
    private static final String EMAIL_PATTERN="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    /**
     * Validates the email field of the Client.
     * Throws IllegalArgumentException if the email does not match the pattern.
     * @param client the Client to validate
     */
    @Override
    public void validate(Client client)
    {
        Pattern pattern=Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(client.getEmail()).matches())
        {
            throw new IllegalArgumentException("Email is not valid: "+client.getEmail());
        }
    }
}
