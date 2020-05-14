package info.zdziech.webstore.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    private BigDecimal grandTotal;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    public Cart() {

    }

    public Cart(Long cartId) {
        this();
        this.id = cartId;
    }

    public Long getCartId() {
        return id;
    }

    public void setCartId(Long cartId) {
        this.id = id;
    }


    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

//    public void addCartItem(CartItemRepository item) {
//        Long productId = item.getProduct().getProductId();
//        if (cartItems.contains(productId)) {
//            CartItemRepository existingCartItem = cartItems.get(productId);
//            existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
//            cartItems.add(new CartItemRepository(item, productId));
//        } else {
//            cartItems.add(item);
//        }
//        updateGrandTotal();
//    }
//
//    public void removeCartItem(CartItemRepository item) {
//        Long productId = item.getProduct().getProductId();
//        cartItems.remove(productId);
//        updateGrandTotal();
//    }
//
//    public void updateGrandTotal() {
//        grandTotal = new BigDecimal(0);
//        for (CartItemRepository item : cartItems.values()) {
//            grandTotal = grandTotal.add(item.getTotalPrice());
//        }
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
