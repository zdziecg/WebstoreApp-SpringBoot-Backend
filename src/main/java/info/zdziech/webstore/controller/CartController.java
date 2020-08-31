package info.zdziech.webstore.controller;


import info.zdziech.webstore.model.Cart;
import info.zdziech.webstore.model.CartItem;
import info.zdziech.webstore.model.Product;
import info.zdziech.webstore.service.CartService;
import info.zdziech.webstore.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Cart create(@RequestBody Cart cart) {
        return cartService.create(cart);
    }
    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
    public @ResponseBody
    Cart read(@PathVariable(value = "cartId") String cartId) {
        return cartService.read(cartId);
    }
    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "cartId") Long cartId, @RequestBody Cart cart) {
        cartService.update(cart);
    }
    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "cartId") String cartId) {
        cartService.delete(cartId);
    }
//    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void addItem(@PathVariable Long productId, HttpServletRequest request) {
//        Long sessionId = Long.valueOf(request.getSession(true).getId());
//
//        Optional<Cart> cart = cartService.read(sessionId);
//        if(!cart.isPresent()) {
//            cart = Optional.ofNullable(cartService.create(new Cart()));
//        }
//        Optional<Product> product = productService.findById(productId);
//        if(!product.isPresent()) {
//            throw new IllegalArgumentException(new ProductNotFoundException(productId));
//        }
//        cart.addItem(new CartItem(product));
//        cartService.update(cart);
//    }
//    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void removeItem(@PathVariable String productId, HttpServletRequest request) {
//        String sessionId = request.getSession(true).getId();
//        Cart cart = cartService.read(sessionId);
//        if(cart== null) {
//            cart = cartService.create(new Cart(sessionId));
//        }
//        Product product = productService.getProductById(productId);
//        if(product == null) {
//            throw new IllegalArgumentException(new ProductNotFoundException(productId));
//        }
//        cart.removeCartItem(new CartItem(product));
//        cartService.update(sessionId, cart);
//    }
@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
@ResponseStatus(value = HttpStatus.NO_CONTENT)
public void addItem(@PathVariable Long productId, HttpServletRequest request) throws Exception {
    String sessionId = request.getSession().getId();
    Cart cart = cartService.read(sessionId);
    if(cart== null)
    {
        cart = cartService.create(new Cart());
    }
    Product product = productService.getProduct(productId);
    if(product == null)
    {
        throw new IllegalArgumentException();
    }
    cart.addCartItem(new CartItem(1,product, cart ));
    cartService.update(cart);
}
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Niepoprawne żądanie, sprawdź przesyłane dane.")
    public void handleClientErrors(Exception ex) { }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Wewnętrzny błąd serwera.")
    public void handleServerErrors(Exception ex) { }
}

