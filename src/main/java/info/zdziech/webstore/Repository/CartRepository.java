package info.zdziech.webstore.Repository;

import info.zdziech.webstore.Model.Cart;

public interface CartRepository {
    Cart create(Cart cart);
    Cart read(Long cartId);
    void update(Long cartId, Cart cart);
    void delete(Long cartId);
}
