package info.zdziech.webstore.Controller;

import javax.servlet.http.HttpServletRequest;

import info.zdziech.webstore.Service.CartService;
import info.zdziech.webstore.Service.ProductService;
import info.zdziech.webstore.Model.Cart;
import info.zdziech.webstore.Model.CartItem;
import info.zdziech.webstore.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="rest/cart")
public class CartRestController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody Cart create(@RequestBody Cart cart){
        return cartService.create(cart);
    }

    @RequestMapping(value="/{cartId}", method = RequestMethod.GET)
    public @ResponseBody Cart read(@PathVariable(value = "cartId") Long cartId){
        return cartService.read(cartId);
    }

    @RequestMapping(value="/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    public void update(Long cartId, @RequestBody Cart cart){
        cartService.update(cartId, cart);
    }

    @RequestMapping(value="/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value="cartId") Long cartId){
        cartService.delete(cartId);
    }

    @RequestMapping(value="/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable(value="productId") Long productId, HttpServletRequest request){
        String sessionId = String.valueOf(Long.valueOf(request.getSession().getId()));
        Cart cart = cartService.read(Long.valueOf(sessionId));
        System.out.println(cart);

        if(cart == null){
            cartService.create(new Cart(Long.valueOf(sessionId)));
        }

        Product product = productService.getProductById(productId);
        if(product == null) {
            throw new IllegalArgumentException(new ProductNotFoundException(productId));
        }

        cart.addCartItem(new CartItem(product));
        cartService.update(Long.valueOf(sessionId), cart);

    }

    @RequestMapping(value="/remove/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable("productId") Long productId, HttpServletRequest request) throws ProductNotFoundException {

        Long sessionId = Long.valueOf(request.getSession().getId());
        Cart cart = cartService.read(sessionId);
        if(cart == null){
            cart = cartService.create(new Cart(sessionId));
        }

        Product product = productService.getProductById(productId);
        if(product == null){
            throw new ProductNotFoundException(productId);
        }

        cart.removeCartItem(new CartItem(product));
        cartService.update(sessionId, cart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Illegal request, please verify your payload")
    public void handleClientErrors(Exception ex) { }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal Server Error")
    public void handleServletErrors(Exception ex){}


}

