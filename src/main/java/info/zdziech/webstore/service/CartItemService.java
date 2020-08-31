package info.zdziech.webstore.service;

import info.zdziech.webstore.model.CartItem;

import java.util.Optional;

public interface CartItemService {
    void create(CartItem cartItem);
    Optional<CartItem> read(Long id);
    void update( CartItem cartItem);
    void delete(Long id);
}
