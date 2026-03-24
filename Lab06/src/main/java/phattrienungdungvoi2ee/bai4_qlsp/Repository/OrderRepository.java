package phattrienungdungvoi2ee.bai4_qlsp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phattrienungdungvoi2ee.bai4_qlsp.Model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}