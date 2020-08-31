package info.zdziech.webstore.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.util.Objects;


@Entity
public final class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name", nullable = false)
    @Length(min = 3, message = "*Name must have at least 5 characters")
    private String name;

    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;

    @Column(name = "unitsinstock")
    @Min(value = 0, message = "*unitsInStock has to be non negative number")
    private Integer unitsInStock;

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private double price;

//    private String manufacturer;

//    @Transient
//    private MultipartFile productImage;

    public Product() {
    }

    public Product(@Length(min = 3, message = "*Name must have at least 5 characters") String name, String description, String category, @Min(value = 0, message = "*unitsInStock has to be non negative number") Integer unitsInStock, @DecimalMin(value = "0.00", message = "*Price has to be non negative number") double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.unitsInStock = unitsInStock;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                Objects.equals(getProductId(), product.getProductId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getDescription(), product.getDescription()) &&
                Objects.equals(getCategory(), product.getCategory()) &&
                Objects.equals(getUnitsInStock(), product.getUnitsInStock());
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


