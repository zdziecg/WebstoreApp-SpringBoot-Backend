package info.zdziech.webstore.Controller;


        import info.zdziech.webstore.Model.Product;
        import info.zdziech.webstore.Service.ProductServiceImpl;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("menage/products")
public class ProductRestController {


    private ProductServiceImpl products;

    @Autowired
    public ProductRestController(ProductServiceImpl products) {
        this.products = products;
    }

    @GetMapping("all")
    public Iterable <Product> getAll ()
    {
        return products.findAll();
    }

    @GetMapping()
    public Optional<Product> getById (@RequestParam Long index){

        return products.findById(index);
    }

    @PostMapping()
    public Product addProduct (@RequestBody Product product){


        return products.save(product);
    }
    @PutMapping()
    public Product updateProduct (@RequestBody Product product){

        return products.save(product);
    }


    @DeleteMapping()
    public void  deleteProduct (@RequestParam Long index) {
        products.deleteById(index);
    }



}
