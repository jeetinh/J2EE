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
}
