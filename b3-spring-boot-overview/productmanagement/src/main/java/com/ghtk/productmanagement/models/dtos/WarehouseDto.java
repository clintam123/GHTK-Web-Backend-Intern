package com.ghtk.productmanagement.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class WarehouseDto {
    private Integer id;
    @NotBlank(message = "Tên kho không được để trống")
    @Length(min = 1, max = 100, message = "Tên kho phải nhỏ hơn 100 kí tự")
    private String name;

    private String address;
    @JsonProperty("province_id")
    private Integer provinceId;

    @JsonProperty("district_id")
    private Integer districtId;

}
