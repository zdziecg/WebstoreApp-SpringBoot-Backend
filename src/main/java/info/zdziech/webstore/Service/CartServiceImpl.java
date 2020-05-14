package info.zdziech.webstore.Service;

import info.zdziech.webstore.Repository.CartRepository;
import info.zdziech.webstore.Model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    //    @Autowired
//    private CartRepository cartRepository;
//    public Cart create(Cart cart) {
//        return cartRepository.create(cart);
//    }
//    public Cart read(Long cartId) {
//        return cartRepository.read(cartId);
//    }
//    public void update(Long cartId, Cart cart) {
//        cartRepository.update(cartId, cart);
//    }
//    public void delete(Long cartId) {
//        cartRepository.delete(cartId);
//    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);

    }
}

