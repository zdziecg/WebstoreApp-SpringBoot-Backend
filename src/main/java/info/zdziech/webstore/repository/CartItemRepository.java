package info.zdziech.webstore.repository;

import info.zdziech.webstore.model.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository <CartItem, Long> {


}
