package info.zdziech.webstore.Service;

import info.zdziech.webstore.Repository.ProductRepository;
import info.zdziech.webstore.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
        }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
        }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
        }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}

