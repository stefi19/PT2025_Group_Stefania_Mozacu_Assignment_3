package BusinessLogicLayer.Validators;
import Model.Client;
import java.util.regex.Pattern;

public class EmailValidators implements Validator<Client>{
    private static final String EMAIL_PATTERN="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
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
