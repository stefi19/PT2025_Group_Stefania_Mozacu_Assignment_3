package DataAccessObject;

import Connection.ConnectionFactory;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    protected static final Logger LOGGER=Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO(){this.type=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];}
    private String createSelectQuery(String field)
    {
        StringBuilder sb=new StringBuilder();
        sb.append("SELECT * FROM ").append(type.getSimpleName()).append(" WHERE ").append(field).append(" =?");
        return sb.toString();
    }
    public List<T> findAll()
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query="SELECT * FROM "+type.getSimpleName();
        try
        {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            return createObjects(resultSet);
        } catch(SQLException e)
        {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:findAll "+e.getMessage());
        } finally
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
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch(SQLException e)
        {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:findById "+e.getMessage());
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
        List<T> list=new ArrayList<>();
        Constructor[] ctors=type.getDeclaredConstructors();
        Constructor ctor=null;
        for(Constructor c:ctors)
        {
            if(c.getGenericParameterTypes().length==0){ctor=c;break;}
        }
        try
        {
            while(resultSet.next())
            {
                ctor.setAccessible(true);
                T instance=(T)ctor.newInstance();
                for(Field field:type.getDeclaredFields())
                {
                    String fieldName=field.getName();
                    Object value=resultSet.getObject(fieldName);
                    PropertyDescriptor pd=new PropertyDescriptor(fieldName,type);
                    Method method=pd.getWriteMethod();
                    method.invoke(instance,value);
                }
                list.add(instance);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    public T insert(T t)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        try
        {
            connection=ConnectionFactory.getConnection();
            StringBuilder query=new StringBuilder();
            query.append("INSERT INTO ").append(type.getSimpleName()).append(" (");
            StringBuilder values=new StringBuilder();
            List<Object> params=new ArrayList<>();
            for(Field field:type.getDeclaredFields())
            {
                field.setAccessible(true);
                if(!field.getName().equalsIgnoreCase("id"))
                {
                    query.append(field.getName()).append(",");
                    values.append("?,");
                    params.add(field.get(t));
                }
            }
            query.setLength(query.length()-1);
            values.setLength(values.length()-1);
            query.append(") VALUES (").append(values).append(")");
            statement=connection.prepareStatement(query.toString());
            for(int i=0;i<params.size();i++) statement.setObject(i+1,params.get(i));
            statement.executeUpdate();
            return t;
        } catch(Exception e)
        {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:insert "+e.getMessage());
        } finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    public T update(T t)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        try
        {
            connection=ConnectionFactory.getConnection();
            StringBuilder query=new StringBuilder();
            query.append("UPDATE ").append(type.getSimpleName()).append(" SET ");
            List<Object> params=new ArrayList<>();
            Object idValue=null;
            for(Field field:type.getDeclaredFields())
            {
                field.setAccessible(true);
                if(!field.getName().equalsIgnoreCase("id"))
                {
                    query.append(field.getName()).append("=?,");
                    params.add(field.get(t));
                } else idValue=field.get(t);
            }
            query.setLength(query.length()-1);
            query.append(" WHERE id=?");
            statement=connection.prepareStatement(query.toString());
            for(int i=0;i<params.size();i++) statement.setObject(i+1,params.get(i));
            statement.setObject(params.size()+1,idValue);
            statement.executeUpdate();
            return t;
        } catch(Exception e)
        {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:update "+e.getMessage());
        } finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    public void delete(T t)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        try
        {
            connection=ConnectionFactory.getConnection();
            Field idField=type.getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue=idField.get(t);
            String query="DELETE FROM "+type.getSimpleName()+" WHERE id=?";
            statement=connection.prepareStatement(query);
            statement.setObject(1,idValue);
            statement.executeUpdate();
        } catch(Exception e)
        {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:delete "+e.getMessage());
        } finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
