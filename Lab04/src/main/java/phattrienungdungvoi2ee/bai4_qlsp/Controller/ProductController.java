package phattrienungdungvoi2ee.bai4_qlsp.Controller;

import jakarta.validation.Valid;
import phattrienungdungvoi2ee.bai4_qlsp.Model.Product;
import phattrienungdungvoi2ee.bai4_qlsp.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAll());
        return "product/products";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
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
