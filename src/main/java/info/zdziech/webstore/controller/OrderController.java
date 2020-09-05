package info.zdziech.webstore.controller;

import info.zdziech.webstore.ResourceNotFoundException;
import info.zdziech.webstore.model.Order;
import info.zdziech.webstore.model.OrderProduct;
import info.zdziech.webstore.model.OrderProductDto;
import info.zdziech.webstore.model.User;
import info.zdziech.webstore.service.OrderProductService;
import info.zdziech.webstore.service.OrderService;
import info.zdziech.webstore.service.ProductService;
import info.zdziech.webstore.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("orders")
public class OrderController {

    private ProductService productService;
    private OrderService orderService;
    private OrderProductService orderProductService;
    private UserService userService;

    public OrderController(ProductService productService, OrderService orderService, OrderProductService orderProductService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }

    @GetMapping()
    public Optional<Order> getById (@RequestParam Long index){
        return orderService.findById(index);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list(Principal principal) {

        return this.orderService.findByUserName(principal.getName());
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm form, Principal principal) {
        List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        Order order = new Order();
        order.setStatus("CREATED");
        order.setClientName(principal.getName());
        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            try {
                orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(dto
                        .getProduct()
                        .getProductId()), dto.getQuantity())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        order.setOrderProducts(orderProducts);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProduct(op
                        .getProduct()
                        .getProductId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    public static class OrderForm {

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }
    }
}
