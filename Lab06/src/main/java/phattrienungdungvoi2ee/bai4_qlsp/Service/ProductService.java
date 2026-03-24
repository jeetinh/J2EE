package phattrienungdungvoi2ee.bai4_qlsp.Service;

import phattrienungdungvoi2ee.bai4_qlsp.Model.Product;
import org.springframework.stereotype.Service;
import phattrienungdungvoi2ee.bai4_qlsp.Repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository=productRepository;
    }
    private final List<Product> products = new ArrayList<>();
    private long nextId = 1;

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public void add(Product product) {
        this.productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository
                .findById(id)
                .orElse(null);
    }

    public void update(Product product) {
        productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
    public Page<Product> searchAndFilter(String keyword, Long categoryId, Pageable pageable) {
        return productRepository.searchAndFilter(keyword, categoryId, pageable);
    }
}
