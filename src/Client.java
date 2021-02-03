import java.sql.SQLException;

public class Client {
    public static void main(String[] args) {
        try {
            ProductDB.clean();
            BundleDB.clean();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Product mySweatShirt = new Product("Sweatshirt", "Lorem ipsum dolor", 8904);
        mySweatShirt.save();

        Bundle sweatshirts = new Bundle("Sweatshirts");
        sweatshirts.addProduct(mySweatShirt);
        sweatshirts.save();
    }
}
