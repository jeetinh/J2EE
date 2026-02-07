package phattrienungdungvoi2ee.bai4_qlsp.Service;

import phattrienungdungvoi2ee.bai4_qlsp.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private long nextId = 1;

    public List<Product> getAll() {
        return products;
    }

    public void add(Product product) {
        product.setId(nextId++);
        products.add(product);
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(Product product) {
        Product old = findById(product.getId());
        if (old != null) {
            old.setName(product.getName());
            old.setPrice(product.getPrice());
        }
    }

    public void delete(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
