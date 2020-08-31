package info.zdziech.webstore.service;

import info.zdziech.webstore.model.Cart;
import info.zdziech.webstore.model.CartItem;
import info.zdziech.webstore.model.Product;
import info.zdziech.webstore.model.User;

import java.util.Collection;
import java.util.Optional;

public interface CartService {

    public Iterable <Cart> findAll();
    Cart create(Cart cart);
    Cart read(String cartId);
    void update( Cart cart);
    void delete(String cartId);
    void updat (Cart cart, String cartId );
}


