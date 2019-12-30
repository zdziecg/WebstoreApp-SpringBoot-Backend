package info.zdziech.webstore.Repository;

import info.zdziech.webstore.ShopProducts.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
    public class ProductRepoositoryImpl implements ProductRepository{
        private List<Product> listOfProducts = new ArrayList<Product>();
        public ProductRepoositoryImpl() {

            Product mleczko = new Product("P1234","Mleczko do ciała EKE", 7.99);
            mleczko.setDescription("Intensywne nawilżenie zamknięte w formule mleczka do ciała,");
            mleczko.setCategory("Mleczko");
            mleczko.setManufacturer("EKE");
            mleczko.setUnitsInStock(1000);
            Product szampon_eke = new Product("P1235","Szmpon do włosów", 20.99);
            szampon_eke.setDescription("Środek do mycia włosów, zawierający ciekłe mydło, substancje pieniące oraz substancje pomocnicze");
            szampon_eke.setCategory("Szampon");
            szampon_eke.setManufacturer("EKE");
            szampon_eke.setUnitsInStock(777);
            Product krem = new Product("P1236","Krem do twarzy",24.99);
            krem.setDescription(" Krem na noc odżywia, regeneruje i nawilża skórę podczas nocnego odpoczynku");
            krem.setCategory("Krem");
            krem.setManufacturer("Inny");
            krem.setUnitsInStock(1000);
            listOfProducts.add(mleczko);
            listOfProducts.add(szampon_eke);
            listOfProducts.add(krem);
        }
        public List<Product> getAllProducts() {
            return listOfProducts;
        }

        public Product getProductById(String productId) {
            Product productById = null;
            for(Product product : listOfProducts) {
                if(product!=null && product.getProductId()!=null && product.getProductId().equals(productId)){
                    productById = product;
                    break;
                }
            }
            if(productById == null){
                throw new IllegalArgumentException("Brak produktu o wskazanym id:"+ productId);
            }
            return productById;
        }
        public List<Product> getProductsByCategory(String category) {
            List<Product> productsByCategory = new ArrayList<Product>();
            for(Product product: listOfProducts) {
                if(category.equalsIgnoreCase(product.getCategory())){
                    productsByCategory.add(product);
                }
            }
            return productsByCategory;
        }

        public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
            Set<Product> productsByBrand = new HashSet<Product>();
            Set<Product> productsByCategory = new HashSet<Product>();
            Set<String> criterias = filterParams.keySet();
            if(criterias.contains("brand")) {
                for(String brandName: filterParams.get("brand")) {
                    for(Product product: listOfProducts) {
                        if(brandName.equalsIgnoreCase(product.getManufacturer())){
                            productsByBrand.add(product);
                        }
                    }
                }
            }
            if(criterias.contains("category")) {
                for(String category: filterParams.get("category")) {
                    productsByCategory.addAll(this.getProductsByCategory(category));
                }
            }
            productsByCategory.retainAll(productsByBrand);
            return productsByCategory;
        }
    public void addProduct(Product product) {
        listOfProducts.add(product);
    }
    }