package info.zdziech.webstore.service;

import info.zdziech.webstore.model.Order;
import info.zdziech.webstore.model.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
public interface OrderService {

    @NotNull Iterable<Order> getAllOrders();

    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);

    public Optional<Order> findById (Long id);

    public Iterable<Order> findByUserName(String userName);


}
