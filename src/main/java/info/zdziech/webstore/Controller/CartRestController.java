package info.zdziech.webstore.Controller;

import javax.servlet.http.HttpServletRequest;

import info.zdziech.webstore.Repository.CartItemRepository;
import info.zdziech.webstore.Service.*;
import info.zdziech.webstore.Model.Cart;
import info.zdziech.webstore.Model.CartItem;
import info.zdziech.webstore.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="rest/cart")
public class CartRestController {

    private CartService cartService;

    private ProductService productService;

    private CartItemService cartItemService;

    private  CartItem cartItem;


    public CartRestController(CartService cartService, ProductService productService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    @GetMapping("all")
    public Iterable <Cart> getAll ()
    {
        return cartService.findAll();
    }

    @PostMapping("new")
    public ResponseEntity<Cart> create(@RequestBody OrderForm form) {
        List<CartItem> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        Cart cart = new Cart();
//        cart.setStatus(OrderStatus.PAID.name());
        cart = this.cartService.save(cart);

        List<CartItem> cartItems = new ArrayList<>();
        for (CartItem dto : formDtos) {
            cartItems.add(cartItemService.save(new CartItem(cart, productService.findById(dto
                    .getProduct()
                    .getProductId()), cartItem.getQuantity())));
        }

        cart.setCartItems(cartItems);

        this.cartService.save(cart);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(cart.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(cart, headers, HttpStatus.CREATED);
    }


        private void validateProductsExistence(List<CartItem> cartItems) {
        List<CartItem> list = cartItems
                .stream()
                .filter(op -> Objects.isNull(productService.findById(op
                        .getProduct()
                        .getProductId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new Exception ("Product not found");
        }
    }
    public static class OrderForm {

        private List<CartItem> productOrders;

        List<CartItem> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<CartItem> productOrders) {
            this.productOrders = productOrders;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Cart create(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

//    @RequestMapping(value="/{cartId}", method = RequestMethod.GET)
//    public @ResponseBody Cart find(@PathVariable(value = "cartId") Long cartId){
//        return cartService.findById(cartId);
//    }
//    @GetMapping()
//    public Optional<Cart> getById (@RequestParam Long index){
//
//        return cart.findById(index);
//    }
//
    @RequestMapping(value="/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    public void update(Long cartId, @RequestBody Cart cart){
        cartService.save(cart);
    }
//
//    @RequestMapping(value="/{cartId}", method = RequestMethod.DELETE)
//    @ResponseStatus(value=HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable(value="cartId") Long cartId){
//        cartService.delete(cartId);
//    }

//    @RequestMapping(value="/add/{productId}", method = RequestMethod.PUT)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void addItem(@PathVariable(value="productId") Long productId, HttpServletRequest request){
//        Long sessionId = Long.valueOf(request.getSession().getId());
//        Optional<Cart> cart = cartService.findById(sessionId);
//        System.out.println(cart);
//
//        if(cart == null){
//            cartService.create(new Cart(Long.valueOf(sessionId)));
//        }
//
//        Optional<Product> product = productService.findById(productId);
//        if(!product.isPresent()) {
//            throw new IllegalArgumentException(new ProductNotFoundException(productId));
//        }
//
//        assert cart != null;
//        cart.setCartItems((List<CartItem>) new CartItem(product));
//        cartService.save(Long.valueOf(cart), cart);
//
//    }
//
//    @RequestMapping(value="/remove/{productId}", method = RequestMethod.PUT)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void removeItem(@PathVariable("productId") Long productId, HttpServletRequest request) throws ProductNotFoundException {
//
//        Long sessionId = Long.valueOf(request.getSession().getId());
//        Cart cart = cartService.read(sessionId);
//        if(cart == null){
//            cart = cartService.create(new Cart(sessionId));
//        }
//
//        Optional<Product> product = productService.findById(productId);
//        if(!product.isPresent()){
//            throw new ProductNotFoundException(productId);
//        }
//
//        cart.removeCartItem(new CartItemRepository(product));
//        cartService.update(sessionId, cart);
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Illegal request, please verify your payload")
//    public void handleClientErrors(Exception ex) { }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal Server Error")
//    public void handleServletErrors(Exception ex){}


}

