import java.sql.SQLException;

public class Client {
    public static void main(String[] args) {
        Product myProduct = new Product("Yellow Gloves", "Lorem ipsum dolor", 994);
        try {
            ProductDB.insert(myProduct);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Bundle myBundle = new Bundle("Jackets");
        try {
            BundleDB.insert(myBundle);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
