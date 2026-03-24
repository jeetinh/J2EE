package phattrienungdungvoi2ee.bai4_qlsp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phattrienungdungvoi2ee.bai4_qlsp.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}