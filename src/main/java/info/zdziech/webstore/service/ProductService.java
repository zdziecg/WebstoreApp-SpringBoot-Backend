package info.zdziech.webstore.service;

import info.zdziech.webstore.model.Product;

import javax.validation.constraints.Min;
import java.util.Optional;

public interface ProductService {

    public Optional<Product> findById (Long id);
    public Iterable <Product> findAll();
    public Product save (Product product);
    public void deleteById (Long id);
    public Iterable<Product> findByCategory(String category);
    Product getProduct(@Min(value = 1L, message = "Invalid product ID.") long id);

}
