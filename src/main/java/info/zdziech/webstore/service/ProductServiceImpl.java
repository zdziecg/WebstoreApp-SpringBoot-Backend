package info.zdziech.webstore.service;

import info.zdziech.webstore.ResourceNotFoundException;
import info.zdziech.webstore.repository.ProductRepository;
import info.zdziech.webstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
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

    @Override
    public Iterable<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }


    @Override
    public Product getProduct(long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}

