package com.ghtk.productmanagement.models.dtos;

import com.ghtk.productmanagement.constants.ProductStatus;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class ProductDto {
    private Integer id;
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Length(min = 1, max = 100, message = "Tên sản phẩm phải nhỏ hơn 100 kí tự")
    private String name;
    @Positive(message = "Giá sản phẩm phải lớn hơn 0")
    private Float price;

    private String sku;
    private String description;
    private CategoryDto category;
}
