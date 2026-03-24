package phattrienungdungvoi2ee.bai4_qlsp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String name;
    private double price;
    private int quantity = 1; // Mặc định số lượng là 1
}