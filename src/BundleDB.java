import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BundleDB {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://database-1.cztybodj4ulh.eu-central-1.rds.amazonaws.com/store",
                "admin",
                "Revolution44!");
    }

    static boolean insert(Bundle bundle) throws SQLException {
        return getConnection().createStatement().execute(String.format(
                "INSERT INTO bundles VALUES(NULL, '%s');",
                bundle.getName())
        );
    }

    static Bundle select(int id) throws SQLException {
        ResultSet rs = getConnection().createStatement().executeQuery(String.format(
                "SELECT * FROM bundles WHERE id=%d;", id)
        );

        return new Bundle(rs.getInt("id"), rs.getString("name"));
    }

    static boolean update(int id, Bundle bundle) throws SQLException {
        return getConnection().createStatement().execute(String.format(
                "UPDATE products SET name=%s WHERE id=%d;", bundle.getName(), id)
        );
    }

    static boolean delete(int id) throws SQLException {
        return getConnection().createStatement().execute(String.format("DELETE FROM bundles WHERE id=%d;", id));
    }
}
