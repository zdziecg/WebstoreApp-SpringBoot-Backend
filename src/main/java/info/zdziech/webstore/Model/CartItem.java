package info.zdziech.webstore.Model;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "ITEMS")
public class CartItem {


    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private long quantity;
    private BigDecimal totalPrice;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_Id")
    private Cart cart;

    public CartItem(Optional<Product> product) {
    }

    public CartItem(Cart cart, Optional<Product> byId, long quantity) {

    }


    public CartItem() {
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartItem(Product product) {
        super();
        this.product = product;
        this.quantity = 1;
        this.totalPrice = product.getPrice();
    }
        public Product getProduct() {
        return product;
    }
        public void setProduct(Product product) {
        this.product = product;
    }
        public long getQuantity() {
        return quantity;
    }
        public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
        public BigDecimal getTotalPrice() {
        totalPrice = this.product.getPrice().multiply(new
                BigDecimal(this.quantity));
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return getQuantity() == cartItem.getQuantity() &&
                getProduct().equals(cartItem.getProduct()) &&
                getTotalPrice().equals(cartItem.getTotalPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getQuantity(), getTotalPrice());
    }
}
