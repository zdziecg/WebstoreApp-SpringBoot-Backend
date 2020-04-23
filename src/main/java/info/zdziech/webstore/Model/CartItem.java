package info.zdziech.webstore.Model;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {

        private Product product;
        private int quantity;
        private BigDecimal totalPrice;
   public CartItem() {
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
        this.updateTotalPrice();
    }
        public int getQuantity() {
        return quantity;
    }
        public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateTotalPrice();
    }
        public BigDecimal getTotalPrice() {
        return totalPrice;
    }
        public void updateTotalPrice() {
        totalPrice = this.product.getPrice().multiply(new
                BigDecimal(this.quantity));
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
