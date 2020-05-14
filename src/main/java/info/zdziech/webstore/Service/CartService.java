package info.zdziech.webstore.Service;

import info.zdziech.webstore.Model.Cart;

import java.util.Optional;

public interface CartService {
//    Cart create(Cart cart);
    public Optional<Cart> findById (Long id);
    public Iterable <Cart> findAll();
    public Cart save (Cart cart);
    public void deleteById (Long id);

}


