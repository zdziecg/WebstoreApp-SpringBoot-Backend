package info.zdziech.webstore.repository;

import info.zdziech.webstore.model.OrderProduct;
import info.zdziech.webstore.model.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
