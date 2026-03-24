package phattrienungdungvoi2ee.bai4_qlsp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phattrienungdungvoi2ee.bai4_qlsp.Model.CartItem;
import phattrienungdungvoi2ee.bai4_qlsp.Model.Product;
import phattrienungdungvoi2ee.bai4_qlsp.Service.CartService;
import phattrienungdungvoi2ee.bai4_qlsp.Service.ProductService;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    // Xử lý thêm vào giỏ hàng
    @PostMapping("/add")
    public String add(@RequestParam("id") Long id,
                      @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        Product product = productService.findById(id);
        if (product != null) {
            CartItem item = new CartItem();
            item.setProductId(product.getId());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(quantity);
            cartService.add(item);
        }
        return "redirect:/cart"; // Thêm xong chuyển hướng sang trang Giỏ hàng
    }

    // Xóa sản phẩm khỏi giỏ
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        cartService.remove(id);
        return "redirect:/cart";
    }

    // Hiển thị trang giỏ hàng (Câu 6)
    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("totalAmount", cartService.getAmount());
        return "cart/view"; // Lát nữa chúng ta sẽ tạo file view.html
    }
}