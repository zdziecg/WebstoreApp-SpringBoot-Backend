package info.zdziech.webstore.Repository;

import info.zdziech.webstore.Model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository <Cart, Long> {

    Optional<Cart> findById(Cart id);
}
