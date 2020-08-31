package info.zdziech.webstore.service;

import info.zdziech.webstore.model.CartItem;
import info.zdziech.webstore.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CartItemServiceImpl implements CartItemService {

@Autowired
private CartItemRepository cartItemRepository;

    @Override
    public void create(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public Optional<CartItem> read(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public void update(CartItem cartItem) {
         cartItemRepository.save(cartItem);

    }

    @Override
    public void delete(Long id) {
        cartItemRepository.deleteById(id);

    }
}
