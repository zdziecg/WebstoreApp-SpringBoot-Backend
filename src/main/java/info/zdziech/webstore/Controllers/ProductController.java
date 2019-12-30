package info.zdziech.webstore.Controllers;

import info.zdziech.webstore.Repository.ProductRepository;
import info.zdziech.webstore.Services.ProductService;
import info.zdziech.webstore.ShopProducts.Basket;
import info.zdziech.webstore.ShopProducts.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/products")
    public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/all")
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }
    @RequestMapping
    public String list(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }
    @RequestMapping("/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
        model.addAttribute("products", productService.getProductsByCategory(productCategory));
        return "products";
    }
    @RequestMapping("/filter/{ByCriteria}")
        public String getProductsByFilter(@MatrixVariable(pathVar="ByCriteria") Map<String,List<String>> filterParams, Model model) {
            model.addAttribute("products", productService.getProductsByFilter(filterParams));
            return "products";
        }
    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }
    @GetMapping("/add")
    public String getAddNewProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }
    @PostMapping("/add")
    public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, HttpServletRequest request) {
        productService.addProduct(newProduct);
        return "redirect:/products";
    }
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setDisallowedFields("unitsInOrder", "discontinued");
    }
    }








