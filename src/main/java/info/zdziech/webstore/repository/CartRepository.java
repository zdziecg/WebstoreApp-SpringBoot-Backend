package info.zdziech.webstore.repository;

import info.zdziech.webstore.model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository <Cart, String> {

    Optional<Cart> findById(Cart id);
}
