package phattrienungdungvoi2ee.bai4_qlsp.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @Min(value = 1, message = "Giá phải lớn hơn 0")
    private double price;
}
