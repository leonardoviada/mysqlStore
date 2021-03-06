import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDB {
    public static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection(
                        "jdbc:mysql://database-1.cztybodj4ulh.eu-central-1.rds.amazonaws.com/store",
                        "admin",
                        "Revolution44!"
                );
    }

    static boolean insert(Product product) throws SQLException {
        return getConnection()
                .createStatement()
                .execute(String.format("INSERT INTO products VALUES(NULL, '%s', %d, '%s', %d);",
                        product.getName(), product.getPrice(), product.getDescription(), 0)
                );
    }

    static boolean insert(Product product, int bundleId) throws SQLException {
        return getConnection()
                .createStatement()
                .execute(String.format("INSERT INTO products VALUES(NULL, '%s', %d, '%s', %d);",
                        product.getName(), product.getPrice(), product.getDescription(), bundleId)
                );
    }

    static Product select(int id) throws SQLException {
        ResultSet rs = getConnection().createStatement().executeQuery(
                String.format("SELECT * FROM products WHERE id=%d;", id)
        );

        rs.next();
        return new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("price")
        );
    }

    static int getId(Product product) throws SQLException {
        ResultSet rs = getConnection().createStatement().executeQuery(
                String.format("SELECT * FROM products WHERE id='%s';", product.getName())
        );

        rs.next();
        return rs.getInt("id");
    }

    static boolean update(Product product) throws SQLException {
        return getConnection().createStatement().execute(String.format(
                "UPDATE products SET name='%s', price=%d, description='%s' WHERE name='%s'",
                product.getName(), product.getPrice(), product.getDescription(), product.getName())
        );
    }


    static boolean update(Product product, int bundleId) throws SQLException {
        return getConnection().createStatement().execute(String.format(
                "UPDATE products SET name='%s', price=%d, description='%s', bundleId=%d WHERE name='%s'",
                product.getName(), product.getPrice(), product.getDescription(), bundleId, product.getName())
        );
    }

    static boolean delete(int id) throws SQLException {
        return getConnection().createStatement().execute(String.format("DELETE FROM products WHERE id=%d", id));
    }

    static boolean clean() throws SQLException {
        return (getConnection().createStatement().execute("TRUNCATE TABLE products;") &&
                getConnection().createStatement().execute("ALTER TABLE products AUTO_INCREMENT=1;"));
    }
}
