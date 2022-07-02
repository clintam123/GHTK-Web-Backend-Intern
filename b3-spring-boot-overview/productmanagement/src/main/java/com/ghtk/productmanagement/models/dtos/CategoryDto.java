package com.ghtk.productmanagement.models.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class CategoryDto {
    private Integer id;
    @NotBlank(message = "Tên thể loại không được để trống")
    @Length(min = 1, max = 100, message = "Tên thể loại phải nhỏ hơn 100 kí tự")
    private String name;
    @NotBlank(message = "Tên code không được đẻ trông")
    private String code;
    private String description;
}
