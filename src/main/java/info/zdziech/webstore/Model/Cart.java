package info.zdziech.webstore.Model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class Cart {

    private Long cartId;
    private Map<Long,CartItem> cartItems;
    private BigDecimal grandTotal;
    public Cart() {

    }
    public Cart(Long cartId) {
        this();
        this.cartId = cartId;
    }
    public Long getCartId() {
        return cartId;
    }
    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
    public Map<Long, CartItem> getCartItems() {
        return cartItems;
    }
    public void setCartItems(Map<Long, CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }
    public void addCartItem(CartItem item) {
        Long productId = item.getProduct().getProductId();
        if(cartItems.containsKey(productId)) {
            CartItem existingCartItem = cartItems.get(productId);
            existingCartItem.setQuantity(existingCartItem.getQuantity()+ item.getQuantity());
            cartItems.put(productId, existingCartItem);
        } else {
            cartItems.put(productId, item);
        }
        updateGrandTotal();
    }
    public void removeCartItem(CartItem item) {
        Long productId = item.getProduct().getProductId();
        cartItems.remove(productId);
        updateGrandTotal();
    }
    public void updateGrandTotal() {
        grandTotal= new BigDecimal(0);
        for(CartItem item : cartItems.values()){
            grandTotal = grandTotal.add(item.getTotalPrice());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(getCartId(), cart.getCartId()) &&
                Objects.equals(getCartItems(), cart.getCartItems()) &&
                Objects.equals(getGrandTotal(), cart.getGrandTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartId(), getCartItems(), getGrandTotal());
    }
}
