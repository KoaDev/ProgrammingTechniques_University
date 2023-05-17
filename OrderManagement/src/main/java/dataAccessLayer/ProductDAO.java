package dataAccessLayer;

import model.Product;

import java.util.List;

/**
 * ProductDAO class
 * Extends GenericDAO<Product>
 */
public class ProductDAO extends GenericDAO<Product> {

    /**
     * @return List<Product>
     *     Read all Product records
     */
    public List<Product> getAllProducts() {
        return super.findAll();
    }

    /**
     * @param id int
     * @return Product
     */
    public Product findProductById(int id) {
        return super.findById(id);
    }

    /**
     * @param product Product
     *                Insert a new Product record
     */
    public void addProduct(Product product) {
        super.insert(product);
    }

    /**
     * @param product Product
     *                Update a Product record
     */

    public void updateProduct(Product product) {
        super.update(product);
    }

    /**
     * @param id int
     *           Delete a Product record
     */
    public void deleteProduct(int id) {
        super.deleteById(id);
    }
}
