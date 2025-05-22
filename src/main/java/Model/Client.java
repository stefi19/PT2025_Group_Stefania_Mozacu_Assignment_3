package Model;
/**
 * The model class for the client with id, name, email, and address.
 * Includes constructors for new clients and for clients loaded from database.
 * Getters and setters allow access and modification.
 * Overrides toString() to display name and email in the GUI.
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String address;
    /**
     * Default no-arg constructor required for reflection.
     */
    public Client(){}; // required for reflection
    /**
     * Constructor for inserting a new client (without id, because it is autoincremented when inserting).
     * @param name client name
     * @param email client email
     * @param address client address
     */
    public Client(String name, String email, String address)
    {
        this.name=name;
        this.email=email;
        this.address=address;
    }// used for inserting into the database
    /**
     * Constructor for reading client data from database (includes id).
     * @param id client unique identifier
     * @param name client name
     * @param email client email
     * @param address client address
     */
    public Client(int id,String name, String email, String address)
    {
        this.id=id;
        this.name=name;
        this.email=email;
        this.address=address;
    }// used for reading from the database
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
    /**
     * Returns a string representation of the client as "name (email)".
     * @return string for display
     */
    @Override
    public String toString() {
        return name+" ("+email+")";
    }

}
