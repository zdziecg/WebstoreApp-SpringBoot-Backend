package info.zdziech.webstore.Service;

public interface OrderService {
    void processOrder(Long productId, int count);
}
