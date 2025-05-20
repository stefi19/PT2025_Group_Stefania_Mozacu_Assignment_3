package DataAccessObject;

import Connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO()
    {
        this.type=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    private String createSelectQuery(String field)
    {
        StringBuilder sb=new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE "+field+" =?");
        return sb.toString();
    }
    public List<T> findAll()
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query=createSelectQuery(type.getSimpleName());
        try
        {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            return createObjects(resultSet);
        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName()+"DAO:findAll "+e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return new ArrayList<>();
    }

    public T findById(int id)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query=createSelectQuery("id");
        try
        {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet=statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName()+"DAO:findById "+e.getMessage());
        } finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    private List<T> createObjects(ResultSet resultSet)
    {
        List<T> list=new ArrayList<T>();
        Constructor[] ctors=type.getDeclaredConstructors();
        Constructor ctor=null;
        int i;
        for (i=0; i<ctors.length; i++)
        {
            ctor=ctors[i];
            if (ctor.getGenericParameterTypes().length==0)
                break;
        }
        try
        {
            while (resultSet.next())
            {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields())
                {
                    String fieldName=field.getName();
                    Object value=resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor=new PropertyDescriptor(fieldName, type);
                    Method method=propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T insert(T t) {
        Connection connection=null;
        PreparedStatement statement=null;
        try
        {
            connection=ConnectionFactory.getConnection();
            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO ").append(type.getSimpleName()).append(" (");
            StringBuilder values = new StringBuilder();
            List<Object> parameters = new ArrayList<>();
            for (Field field : type.getDeclaredFields())
            {
                field.setAccessible(true);
                if (!field.getName().equalsIgnoreCase("id"))
                {
                    query.append(field.getName()).append(",");
                    values.append("?,");
                    parameters.add(field.get(t));
                }
            }
            // Remove last commas
            query.setLength(query.length() - 1);
            values.setLength(values.length() - 1);
            query.append(") VALUES (").append(values).append(")");
            statement = connection.prepareStatement(query.toString());
            int i;
            for(i=0; i<parameters.size(); i++)
                statement.setObject(i+1,parameters.get(i));
            statement.executeUpdate();
            return t;
        } catch (IllegalAccessException | SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    public T update(T t) {
        Connection connection=null;
        PreparedStatement statement=null;
        try
        {
            connection=ConnectionFactory.getConnection();
            StringBuilder query=new StringBuilder();
            query.append("UPDATE ").append(type.getSimpleName()).append(" SET ");
            List<Object> parameters=new ArrayList<>();
            Object idValue=null;
            for (Field field : type.getDeclaredFields())
            {
                field.setAccessible(true);
                if (!field.getName().equalsIgnoreCase("id"))
                {
                    query.append(field.getName()).append(" = ?, ");
                    parameters.add(field.get(t));
                } else
                {
                    idValue=field.get(t); // save ID for WHERE clause
                }
            }
            // Remove last comma and space
            query.setLength(query.length() - 2);
            query.append(" WHERE id = ?");
            statement = connection.prepareStatement(query.toString());
            int i;
            for (i=0; i<parameters.size(); i++)
            {
                statement.setObject(i+1, parameters.get(i));
            }
            statement.setObject(parameters.size()+1, idValue);
            statement.executeUpdate();
            return t;
        } catch (IllegalAccessException | SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}

