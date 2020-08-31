package info.zdziech.webstore.repository;

import info.zdziech.webstore.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface ProductRepository extends CrudRepository <Product, Long> {
    Iterable<Product> findByCategory(String category);



}
