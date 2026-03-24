package phattrienungdungvoi2ee.bai4_qlsp.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import phattrienungdungvoi2ee.bai4_qlsp.Model.CartItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartService {
    // Dùng HashMap để lưu giỏ hàng, Key là ProductID, Value là CartItem
    private Map<Long, CartItem> map = new HashMap<>();

    // Thêm sản phẩm vào giỏ
    public void add(CartItem item) {
        CartItem existedItem = map.get(item.getProductId());
        if (existedItem != null) {
            // Nếu đã có trong giỏ, cộng dồn số lượng
            existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
        } else {
            // Nếu chưa có, thêm mới
            map.put(item.getProductId(), item);
        }
    }

    // Xóa sản phẩm khỏi giỏ
    public void remove(Long productId) {
        map.remove(productId);
    }

    // Lấy danh sách sản phẩm trong giỏ
    public Collection<CartItem> getItems() {
        return map.values();
    }

    // Xóa sạch giỏ hàng
    public void clear() {
        map.clear();
    }

    // Cập nhật số lượng
    public void update(Long productId, int quantity) {
        CartItem item = map.get(productId);
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    // Tính tổng tiền
    public double getAmount() {
        return map.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }

    // Tính tổng số lượng mặt hàng
    public int getCount() {
        return map.values().stream().mapToInt(item -> item.getQuantity()).sum();
    }
}