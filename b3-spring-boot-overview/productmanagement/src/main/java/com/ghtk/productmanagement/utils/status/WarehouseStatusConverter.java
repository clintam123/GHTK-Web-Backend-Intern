package com.ghtk.productmanagement.utils.status;

import com.ghtk.productmanagement.constants.WarehouseStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class WarehouseStatusConverter implements AttributeConverter<WarehouseStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(WarehouseStatus warehouseStatus) {
        return warehouseStatus.getStatus();
    }

    @Override
    public WarehouseStatus convertToEntityAttribute(Integer integer) {
        if(integer == 1)
            return WarehouseStatus.ACTIVE;
        else
            return WarehouseStatus.INACTIVE;
    }
}
