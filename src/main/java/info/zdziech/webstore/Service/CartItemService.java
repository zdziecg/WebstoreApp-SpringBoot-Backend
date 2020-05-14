package info.zdziech.webstore.Service;

import info.zdziech.webstore.Model.CartItem;

import java.util.Optional;

public interface CartItemService {
    public Optional findById (Long id);
    public Iterable findAll();
    public CartItem save (CartItem cartItem);
    public void deleteById (Long id);
}
