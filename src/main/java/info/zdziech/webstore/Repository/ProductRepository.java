package info.zdziech.webstore.Repository;

import info.zdziech.webstore.ShopProducts.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductRepository {
    List<Product> getAllProducts();
    Product getProductById(String productId);
    List<Product> getProductsByCategory(String category);
    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
    void addProduct(Product product);

}
