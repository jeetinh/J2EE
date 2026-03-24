package phattrienungdungvoi2ee.bai4_qlsp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import phattrienungdungvoi2ee.bai4_qlsp.Model.Order;
import phattrienungdungvoi2ee.bai4_qlsp.Service.CartService;
import phattrienungdungvoi2ee.bai4_qlsp.Service.OrderService;

@Controller
@RequestMapping("/checkout")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    // Hiển thị form điền thông tin khách hàng
    @GetMapping
    public String checkoutForm(Model model) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart"; // Giỏ trống thì quay lại giỏ
        }
        model.addAttribute("order", new Order());
        model.addAttribute("totalAmount", cartService.getAmount());
        return "cart/checkout";
    }

    // Xử lý khi nhấn nút Đặt Hàng
    @PostMapping
    public String processCheckout(@ModelAttribute("order") Order order) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        orderService.checkout(order);
        return "cart/success"; // Chuyển đến trang thông báo thành công
    }
}