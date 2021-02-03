import java.util.ArrayList;

public class Bundle {
    private int id = -1;
    private String name = null;
    private ArrayList<Product> products = null;

    public Bundle() {
    }

    public Bundle(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Bundle(String name) {
        this.name = name;
    }

    public Bundle(int id, String name, ArrayList<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Bundle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }

    public int getProductsNumber() {
        return this.products.size();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) return product;
        }
        return null;
    }
}
