package info.zdziech.webstore.Model;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
public final class Product {




//    private String manufacturer;
//    private String category;
//    private long unitsInStock;
//    private long unitsInOrder;
//    @Transient
//    private MultipartFile productImage;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name", nullable = false)
    @Length(min = 3, message = "*Name must have at least 5 characters")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    @Min(value = 0, message = "*Quantity has to be non negative number")
    private Integer quantity;

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal price;



//    public Product() {
//    }
//
//    public Product( String name, BigDecimal price, String description) {
//        this.name = name;
//        this.price = price;
//        this.description = description;
//    }
    //    public Product(String name, BigDecimal price, String description, String manufacturer, String category, long unitsInStock, long unitsInOrder, MultipartFile productImage) {
//        this.name = name;
//        this.price = price;
//        this.description = description;
//        this.manufacturer = manufacturer;
//        this.category = category;
//        this.unitsInStock = unitsInStock;
//        this.unitsInOrder = unitsInOrder;
//        this.productImage = productImage;
//    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getManufacturer() {
//        return manufacturer;
//    }
//
//    public void setManufacturer(String manufacturer) {
//        this.manufacturer = manufacturer;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public long getUnitsInStock() {
//        return unitsInStock;
//    }
//
//    public void setUnitsInStock(long unitsInStock) {
//        this.unitsInStock = unitsInStock;
//    }
//
//    public long getUnitsInOrder() {
//        return unitsInOrder;
//    }
//
//    public void setUnitsInOrder(long unitsInOrder) {
//        this.unitsInOrder = unitsInOrder;
//    }
//
//    public MultipartFile getProductImage() {
//        return productImage;
//    }
//
//    public void setProductImage(MultipartFile productImage) {
//        this.productImage = productImage;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getName().equals(product.getName()) &&
                getPrice().equals(product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }


    @Override
    public String toString() {
        return "" +
                 name  +
                ", price " + price +" "
                ;
    }


}


