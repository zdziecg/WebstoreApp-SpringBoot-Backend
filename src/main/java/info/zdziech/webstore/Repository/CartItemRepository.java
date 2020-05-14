package info.zdziech.webstore.Repository;

import info.zdziech.webstore.Model.Cart;
import info.zdziech.webstore.Model.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartItemRepository extends CrudRepository <CartItem, Long> {


}
