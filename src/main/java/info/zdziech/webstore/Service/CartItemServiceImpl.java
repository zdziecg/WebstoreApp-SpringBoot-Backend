package info.zdziech.webstore.Service;

import info.zdziech.webstore.Model.CartItem;
import info.zdziech.webstore.Repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CartItemServiceImpl implements CartItemService {

    private CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Optional findById(Long id) {
        return cartItemRepository.findById(id) ;
    }

    @Override
    public Iterable<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteById(Long id) {

        cartItemRepository.deleteById(id);
    }
}
