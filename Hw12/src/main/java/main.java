import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class main {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:D:\\SQLiteStudio\\bookstore.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            BookStore bookStore = new BookStore(1,"John","Au", 234);
            ReflectionRepository<BookStore> ref= new ReflectionRepository<BookStore>(BookStore.class ,conn);
            System.out.println(ref.add(bookStore));
            System.out.println(ref.getAll());
            System.out.println(ref.get(4));
            ref.delete(7);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
