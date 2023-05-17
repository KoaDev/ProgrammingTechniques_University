// dataAccessLayer/GenericDAO.java
package dataAccessLayer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> the type of the objects stored in the database
 *           Generic DAO class used to perform CRUD operations on the database
 */
public abstract class GenericDAO<T> {
    private final Class<T> type;

    /**
     * Constructor that sets the type of the objects stored in the database
     */
    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.type = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * @param obj the object to be inserted into the database
     * @return the inserted object
     */
    // Create
    public T insert(T obj) {
        try (Connection connection = DBConnection.getConnection()) {
            StringBuilder query = new StringBuilder("INSERT INTO " + type.getSimpleName().toLowerCase() + "s (");
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    query.append(field.getName()).append(", ");
                }
            }
            query.setLength(query.length() - 2);
            query.append(") VALUES (");
            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    query.append("?, ");
                }
            }
            query.setLength(query.length() - 2);
            query.append(")");

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

            int parameterIndex = 1;
            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    field.setAccessible(true);
                    preparedStatement.setObject(parameterIndex, field.get(obj));
                    parameterIndex++;
                }
            }

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                Method setIdMethod = type.getMethod("setId", int.class);
                setIdMethod.invoke(obj, id);
            }
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * @param obj the object to be updated in the database
     *            Updates the object with the same id as the given object
     */
    // Edit
    public void update(T obj) {
        try (Connection connection = DBConnection.getConnection()) {
            StringBuilder query = new StringBuilder("UPDATE " + type.getSimpleName().toLowerCase() + "s SET ");
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    query.append(field.getName()).append(" = ?, ");
                }
            }
            query.setLength(query.length() - 2);
            query.append(" WHERE id = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

            int parameterIndex = 1;
            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    field.setAccessible(true);
                    preparedStatement.setObject(parameterIndex, field.get(obj));
                    parameterIndex++;
                }
            }
            // Note the adjustment here: use fields.length instead of parameterIndex
            preparedStatement.setObject(fields.length, type.getMethod("getId").invoke(obj));

            preparedStatement.executeUpdate();
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id the id of the object to be deleted from the database
     *           Deletes the object with the given id from the database
     */
    // Delete
    public void deleteById(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "DELETE FROM " + type.getSimpleName().toLowerCase() + "s WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id the id of the object to be found in the database
     * @return the object with the given id
     */
    // Find
    public T findById(int id) {
        T obj = null;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM " + type.getSimpleName().toLowerCase() + "s WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                obj = type.getDeclaredConstructor().newInstance();

                Field[] fields = type.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Method setMethod = type.getMethod("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), field.getType());
                    Object value = resultSet.getObject(field.getName());
                    if (value != null) {
                        if (field.getType() == int.class) {
                            value = resultSet.getInt(field.getName());
                        } else if (field.getType() == double.class) {
                            value = resultSet.getDouble(field.getName());
                        } // add more types if needed
                    }
                    setMethod.invoke(obj, value);
                }
            }
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * @return a list of all objects of the type of the DAO
     * Finds all objects of the type of the DAO
     */
    public List<T> findAll() {
        List<T> results = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM " + type.getSimpleName().toLowerCase() + "s";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T obj = type.getDeclaredConstructor().newInstance();

                Field[] fields = type.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Method setMethod = type.getMethod("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), field.getType());
                    Object value = resultSet.getObject(field.getName());
                    if (value != null) {
                        if (field.getType() == int.class) {
                            value = resultSet.getInt(field.getName());
                        } else if (field.getType() == double.class) {
                            value = resultSet.getDouble(field.getName());
                        } // add more types if needed
                    }
                    setMethod.invoke(obj, value);
                }
                results.add(obj);
            }
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return results;
    }


}