package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source: http://theopentutorials.com/tutorials/java/jdbc/jdbc-mysql-create-database-example/
 */
public class ConnectionFactory {

    private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://localhost:3306/orders";
    private static final String USER="root";
    private static final String PASS="Stef0578";

    private static ConnectionFactory singleInstance=new ConnectionFactory();
    /**
     * Private constructor that loads the java database connection driver class.
     */
    private ConnectionFactory()
    {
        try
        {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Creates a new database connection.
     */
    private Connection createConnection()
    {
        Connection connection=null;
        try
        {
            connection=DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * Gets a database connection from the instance.
     */
    public static Connection getConnection()
    {
        return singleInstance.createConnection();
    }
    /**
     * Closes a database connection
     * @param connection to close
     */
    public static void close(Connection connection)
    {
        if (connection!=null) {
            try
            {
                connection.close();
            } catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }
    /**
     * Closes a statement
     * @param statement to close
     */
    public static void close(Statement statement)
    {
        if (statement!=null)
        {
            try
            {
                statement.close();
            } catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }
    /**
     * Closes a ResultSet
     * @param resultSet to close
     */
    public static void close(ResultSet resultSet)
    {
        if (resultSet!=null)
        {
            try
            {
                resultSet.close();
            } catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
