package info.zdziech.webstore.controller;

import info.zdziech.webstore.model.Product;
import info.zdziech.webstore.service.*;
import info.zdziech.webstore.model.Cart;
import info.zdziech.webstore.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="rest/cart")
public class CartRestController {

    private CartService cartService;

    private ProductService productService;

    private CartItemService cartItemService;

    private  CartItem cartItem;


    @Autowired
    public CartRestController(CartService cartService, ProductService productService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    @RequestMapping(value = "/{cartId}/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable Long productId,@PathVariable String cartId, HttpServletRequest request) throws Exception {
        Cart cart = cartService.read(cartId);
        if(cart== null) {
            cart = cartService.create(new Cart());
        }
        Product product = productService.getProduct(productId);
        if(product == null) {
            throw new IllegalArgumentException(new ProductNotFoundException(productId));
        }
        cart.addCartItem(new CartItem(1, product, cart));
        cartService.update(cart);
    }

    @GetMapping("/all")
    public Iterable <Cart> getAll ()
    {
        return cartService.findAll();
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public @ResponseBody
//    void create(@RequestBody Cart cart) throws Exception {
//        cartService.create(new Cart());
//        List<CartItem> cartItems = cart.getCartItems();
//        Product product = productService.getProduct(cartItem.getProduct().getProductId());
//        for (int i = 0; i < cartItems.size(); i++) {
//            CartItem cartItem = cartItems.get(i);
//            if (product.getProductId().equals(cartItem.getProduct().getProductId())) {
//                cartItemService.update(cartItem);
//                return;
//            }
//        }
//        CartItem cartItem = new CartItem();
//        cartItem.setQuantity(1);
//        cartItem.setProduct(product);
//        cartItem.setCart(cart);
//        cartItemService.update(cartItem);
//
//         cartService.update(cart);
//    }

    @RequestMapping(value="/{cartId}", method = RequestMethod.GET)
    public @ResponseBody
    Cart read(@PathVariable(value = "cartId") String cartId){
        return cartService.read(cartId);
    }
    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "cartId") Long cartId, @RequestBody Cart cart) {
        cartItemService.update(cartItem);

        cartService.update(cart);
    }


    @RequestMapping(value="/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value="cartId") String cartId){
        cartService.delete(cartId);
    }
//    @PostMapping("/new")
//    public ResponseEntity<Cart> create(@RequestBody CartForm form) throws Exception {
//        List<CartItem> formDtos = form.getCartItems();
////        validateProductsExistence(formDtos);
//        Cart cart = new Cart();
////        cart.setStatus(OrderStatus.PAID.name());
//        cart = this.cartService.create(cart);
//
//        List<CartItem> cartItems = new ArrayList<>();
//        for (CartItem dto : formDtos) {
//            Product p = productService.getProduct(dto.getProduct().getProductId());
////            cartItemService.create(new CartItem( dto.getQuantity(), p,cart));
//            cartItems.add(new CartItem(Math.toIntExact(dto.getQuantity()),p,  cart));
//            cartItemService.update(cartItem);
//
//        }
//
//        cart.setCartItems(cartItems);
//
//
//        this.cartService.update(cart);
//
//        String uri = ServletUriComponentsBuilder
//                .fromCurrentServletMapping()
//                .path("/cart/{id}")
//                .buildAndExpand(cart.getId())
//                .toString();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Location", uri);
//
//        return new ResponseEntity<>(cart, headers, HttpStatus.CREATED);
//    }
//
//    private void validateProductsExistence(List<CartItemDto> cartItems) {
//        List<CartItemDto> list = cartItems
//                .stream()
//                .filter(op -> Objects.isNull(productService.findById(op
//                        .getProduct()
//                        .getProductId())))
//                .collect(Collectors.toList());
//
//        if (!CollectionUtils.isEmpty(list)) {
//            new ResourceNotFoundException("Product not found");
//        }
//    }
    @RequestMapping("/add/{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addCartItem(@PathVariable(value = "productId") Long productId, HttpSession session) throws Exception {

        Cart cart = (Cart) session.getAttribute("cartId");
        if (cart == null) {
            cart = cartService.create(new Cart());
            cart.setStatus("CREATED");
            List<CartItem> cartItems = cart.getCartItems();
            Product product = productService.getProduct(productId);
            for (int i = 0; i < cartItems.size(); i++) {
                CartItem cartItem = cartItems.get(i);
                if (product.getProductId().equals(cartItem.getProduct().getProductId())) {
                    cartItem.setQuantity(Math.toIntExact(cartItem.getQuantity() + 1));
                    cartItemService.update(cartItem);
                    return;
                }
            }
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItemService.update(cartItem);
        }

//    public static class CartForm {
//
//        private List<CartItem> cartItems;
//
//        List<CartItem> getCartItems() {
//            return cartItems;
//        }
//
//        public void setCartItems(List<CartItem> cartItems) {
//            this.cartItems = cartItems;
//        }
//    }

    }}

