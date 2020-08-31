package info.zdziech.webstore.service;

import info.zdziech.webstore.model.CartItem;
import info.zdziech.webstore.model.Product;
import info.zdziech.webstore.model.User;
import info.zdziech.webstore.repository.CartItemRepository;
import info.zdziech.webstore.repository.CartRepository;
import info.zdziech.webstore.model.Cart;
import info.zdziech.webstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    ProductService productService;

    private UserRepository userRepository;

    private CartItemRepository cartItemRepository;

    private UserService userService;


    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductService productService, UserRepository userRepository, CartItemRepository cartItemRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
    }

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }
    public Cart read(String cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }
    public void update(  Cart cart) {
        cartRepository.save(cart);
    }
    public void delete(String cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void updat(Cart cart, String cartId) {
        cartRepository.save(cart);
    }

}

