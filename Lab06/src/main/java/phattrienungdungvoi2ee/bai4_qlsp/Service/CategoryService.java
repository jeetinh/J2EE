package phattrienungdungvoi2ee.bai4_qlsp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phattrienungdungvoi2ee.bai4_qlsp.Model.Category;
import phattrienungdungvoi2ee.bai4_qlsp.Repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    // Thêm danh mục
    public void add(Category category) {
        categoryRepository.save(category);
    }

    // Tìm danh mục theo ID
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Cập nhật danh mục
    public void update(Category category) {
        categoryRepository.save(category);
    }

    // Xóa danh mục
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}