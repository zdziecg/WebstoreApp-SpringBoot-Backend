package info.zdziech.webstore.Service;

import info.zdziech.webstore.Repository.CartRepository;
import info.zdziech.webstore.Model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;
    public Cart create(Cart cart) {
        return cartRepository.create(cart);
    }
    public Cart read(Long cartId) {
        return cartRepository.read(cartId);
    }
    public void update(Long cartId, Cart cart) {
        cartRepository.update(cartId, cart);
    }
    public void delete(Long cartId) {
        cartRepository.delete(cartId);
    }
}

