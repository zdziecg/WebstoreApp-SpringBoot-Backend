package info.zdziech.webstore.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private String id;

    private BigDecimal grandTotal;

    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", updatable = false, insertable = false)
    private List<CartItem> cartItems =new ArrayList<>();

    public void addCartItem(CartItem item) {
        Long productId = item.getProduct().getProductId();
        if(cartItems.contains(productId)) {
            CartItem existingCartItem = cartItems.get(Math.toIntExact(productId));
            existingCartItem.setQuantity((int) (existingCartItem.getQuantity()+ item.getQuantity()));
            cartItems.add(Math.toIntExact(productId), existingCartItem);
        } else {
            cartItems.add(Math.toIntExact(productId), item);
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(getId(), cart.getId()) &&
                Objects.equals(getGrandTotal(), cart.getGrandTotal()) &&
                Objects.equals(getStatus(), cart.getStatus()) &&
                Objects.equals(getCartItems(), cart.getCartItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGrandTotal(), getStatus(), getCartItems());
    }
}
