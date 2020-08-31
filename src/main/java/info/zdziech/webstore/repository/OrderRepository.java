package info.zdziech.webstore.repository;

import info.zdziech.webstore.model.Order;
import info.zdziech.webstore.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Iterable<Order> findByClientName(String category);

}
