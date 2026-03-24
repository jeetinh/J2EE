package phattrienungdungvoi2ee.bai4_qlsp.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import phattrienungdungvoi2ee.bai4_qlsp.Model.Product;
import phattrienungdungvoi2ee.bai4_qlsp.Service.CategoryService;
import phattrienungdungvoi2ee.bai4_qlsp.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    @Autowired
    private CategoryService categoryService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size, // Câu 2: Mỗi trang hiển thị 5 sản phẩm
            @RequestParam(defaultValue = "id,asc") String[] sort, // Câu 3: Mặc định sắp xếp theo id tăng dần
            Model model) {

        // Xử lý sắp xếp (Câu 3)
        String sortField = sort[0];
        String sortDirection = sort[1];
        Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort order = Sort.by(direction, sortField);

        // Xử lý phân trang (Câu 2)
        Pageable pageable = PageRequest.of(page, size, order);

        // Lấy dữ liệu đã lọc và phân trang (Câu 1 & 4)
        Page<Product> productPage = productService.searchAndFilter(keyword, categoryId, pageable);

        // Đẩy dữ liệu xuống View
        model.addAttribute("productPage", productPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("categories", categoryService.getAll()); // Dropdown chọn category

        return "product/products";
    }
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories",
                categoryService.getAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "product/create";
        }
        productService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/edit";
    }

    @PostMapping("/edit")
    public String edit(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "product/edit";
        }
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

}
