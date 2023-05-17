// businessLayer/ProductService.java
package businessLayer;

import dataAccessLayer.ProductDAO;
import model.Product;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public Product findProductById(int id) {
        return productDAO.findProductById(id);
    }
}
