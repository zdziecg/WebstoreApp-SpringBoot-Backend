package info.zdziech.webstore.Repository;

import info.zdziech.webstore.Model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Repository
public interface ProductRepository extends CrudRepository <Product, Long> {

}
