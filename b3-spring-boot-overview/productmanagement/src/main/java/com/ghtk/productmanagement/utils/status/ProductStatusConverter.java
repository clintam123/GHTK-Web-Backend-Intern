package com.ghtk.productmanagement.utils.status;

import com.ghtk.productmanagement.constants.ProductStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProductStatusConverter implements AttributeConverter<ProductStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProductStatus productStatus) {
        return productStatus.getStatus();
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer integer) {
        if(integer == 1)
            return ProductStatus.ACTIVE;
        else
            return ProductStatus.INACTIVE;
    }
}
