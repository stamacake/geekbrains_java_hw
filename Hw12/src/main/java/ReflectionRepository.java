import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ReflectionRepository<T> {

    private final Class<T> tmpClass;
    private final Connection connection;
    private final List<Field> cells;

    public ReflectionRepository(Class<T> tmpClass, Connection connection) {
        if (!checkClass(tmpClass)) {
            throw new RuntimeException("Class not found");
        }
        this.tmpClass = tmpClass;
        this.connection = connection;
        this.cells = getFields();
    }

    public T add(T t) throws SQLException {
        String sql = "INSERT INTO " + Table() + "(" +
                Columns() + ") VALUES (" +
                Values(t) + ");";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
        return this.getAll().get(this.getAll().size() - 1);
    }

    public T get(long id) throws SQLException {
        String sql = "SELECT * FROM " + Table() + " WHERE " + getId().getName() + " = " + id + ";";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (!resultSet.next()) return null;
            return ResToObj(resultSet);
        }
    }

    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM " + Table() + ";";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM " + Table() + " WHERE " + getId().getName() + " = " + id + ";";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private boolean checkClass(Class<T> tmpClass) {
        Field[] fields = tmpClass.getDeclaredFields();
        int ids = 0;
        for (Field field : fields) if (field.getAnnotation(DbId.class) != null) ids++;
        if (ids != 1 || tmpClass.getAnnotation(DbTable.class) == null) return false;
        return true;
    }

    private String Table() {
        return tmpClass.getAnnotation(DbTable.class).name();
    }

    private List<Field> getFields() {
        List<Field> gettedFields = new ArrayList<>();
        Field[] fields = tmpClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(DbColumn.class) != null) gettedFields.add(field);
        }
        return gettedFields;
    }

    private String Columns() {
        return cells.stream().map(Field::getName).collect(Collectors.joining(", "));
    }
    private Object Values(T t)  {
        return cells.stream().map(e -> {
            try {
                return this.stringConv(e, t);
            } catch (InvocationTargetException invocationTargetException) {
                invocationTargetException.printStackTrace();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            } catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
            return null;
        }).collect(Collectors.joining(", "));
    }

    private String stringConv(Field field, T t) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
            Method method = callGetter("get", field);
            if (method.getReturnType().equals(String.class)) return "\"" + method.invoke(t) + "\"";
            return (String) method.invoke(t).toString();
    }

    public List<T> getAll() throws SQLException {
        String sql = "SELECT * FROM " + Table() + " ORDER BY " + getId().getName() + ";";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<T> res = new ArrayList<>();
            while (resultSet.next()) res.add(ResToObj(resultSet));
            return res;
        }
    }

    private T ResToObj(ResultSet resultSet) {
        try {
            T result = tmpClass.getConstructor().newInstance();
            callGetter("set", getId(), getId().getType()).invoke(result, resultSet.getLong(1));
            int indx = 2;
            for (Field field : cells) {
                Method method = callGetter("set", field, field.getType());
                if (field.getType().equals(String.class)) method.invoke(result, resultSet.getString(indx));
                else if (field.getType().equals(Integer.class)) method.invoke(result, resultSet.getInt(indx));
                else throw new RuntimeException("Wrong value");
                indx++;
            }
            return result;
        } catch (InstantiationException | SQLException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

//    private void callGetter(Object obj, String fieldName) {
//        PropertyDescriptor pd;
//        try {
//            pd = new PropertyDescriptor(fieldName, obj.getClass());
//            System.out.println("" + pd.getReadMethod().invoke(obj));
//        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }

    private Method callGetter(String str, Field fieldName, Class... args) throws NoSuchMethodException {
        String gett = str + Character.toUpperCase(fieldName.getName().charAt(0)) + fieldName.getName().substring(1);
        return tmpClass.getMethod(gett, args);
    }

    private Field getId() {
        return Arrays.stream(tmpClass.getDeclaredFields()).filter(e -> e.getAnnotation(DbId.class) != null).findFirst().orElseThrow(() -> new RuntimeException("NO ID"));
    }


}