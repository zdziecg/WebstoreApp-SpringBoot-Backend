package info.zdziech.webstore.Service;

import info.zdziech.webstore.Model.Cart;

public interface CartService {
    Cart create(Cart cart);
    Cart read(Long cartId);
    void update(Long cartId, Cart cart);
    void delete(Long cartId);
}


