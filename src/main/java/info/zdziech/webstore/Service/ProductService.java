package info.zdziech.webstore.Service;

import info.zdziech.webstore.Model.Product;

import java.util.Optional;

public interface ProductService {

    public Optional<Product> findById (Long id);
    public Iterable <Product> findAll();
    public Product save (Product product);
    public void deleteById (Long id);

}
