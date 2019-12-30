package info.zdziech.webstore.Services;

import info.zdziech.webstore.Repository.ProductRepository;
import info.zdziech.webstore.ShopProducts.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public class OrderServiceImpl implements OrderService{
        @Autowired
        private ProductRepository productRepository;
        public void processOrder(String productId, int count) {
            Product productById = productRepository.getProductById(productId);
            if(productById.getUnitsInStock() < count){
                throw new IllegalArgumentException("Zbyt mało towaru. Obecna liczba sztuk w magazynie: "+ productById.getUnitsInStock());
            }
            productById.setUnitsInStock(productById.getUnitsInStock() - count);
        }
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }
    }

