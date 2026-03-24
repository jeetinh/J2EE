package phattrienungdungvoi2ee.bai4_qlsp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phattrienungdungvoi2ee.bai4_qlsp.Model.CartItem;
import phattrienungdungvoi2ee.bai4_qlsp.Model.Order;
import phattrienungdungvoi2ee.bai4_qlsp.Model.OrderDetail;
import phattrienungdungvoi2ee.bai4_qlsp.Model.Product;
import phattrienungdungvoi2ee.bai4_qlsp.Repository.OrderDetailRepository;
import phattrienungdungvoi2ee.bai4_qlsp.Repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Transactional // Đảm bảo nếu lỗi thì không lưu dữ liệu bị thiếu
    public void checkout(Order order) {
        // 1. Lưu thông tin Order để lấy ID
        order.setTotalAmount(cartService.getAmount());
        Order savedOrder = orderRepository.save(order);

        // 2. Duyệt qua giỏ hàng để tạo các OrderDetail
        for (CartItem item : cartService.getItems()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);

            // Lấy product thực tế từ DB để gán vào (đảm bảo tính toàn vẹn)
            Product product = productService.findById(item.getProductId());
            detail.setProduct(product);

            detail.setPrice(item.getPrice());
            detail.setQuantity(item.getQuantity());

            // Lưu chi tiết đơn hàng
            orderDetailRepository.save(detail);
        }

        // 3. Xóa sạch giỏ hàng sau khi đặt thành công
        cartService.clear();
    }
}