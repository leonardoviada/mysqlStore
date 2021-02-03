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

    static int getId(Bundle bundle) throws SQLException {
        ResultSet rs = getConnection().createStatement().executeQuery(String.format(
                "SELECT * FROM bundles WHERE name='%s';", bundle.getName())
        );

        rs.next();
        return rs.getInt("id");
    }

    static boolean update(Bundle bundle) throws SQLException {
        return getConnection().createStatement().execute(String.format(
                "UPDATE products SET name=%s WHERE name='%s';", bundle.getName(), bundle.getName())
        );
    }

    static boolean delete(Bundle bundle) throws SQLException {
        return getConnection().createStatement().execute(String.format("DELETE FROM bundles WHERE name='%s';", bundle.getName()));
    }

    static boolean clean() throws SQLException {
        return (getConnection().createStatement().execute("TRUNCATE TABLE bundles;") &&
                getConnection().createStatement().execute("ALTER TABLE bundles AUTO_INCREMENT = 1;"));
    }
}
