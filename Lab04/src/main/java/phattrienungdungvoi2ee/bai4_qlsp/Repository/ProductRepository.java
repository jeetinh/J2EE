package phattrienungdungvoi2ee.bai4_qlsp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phattrienungdungvoi2ee.bai4_qlsp.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}