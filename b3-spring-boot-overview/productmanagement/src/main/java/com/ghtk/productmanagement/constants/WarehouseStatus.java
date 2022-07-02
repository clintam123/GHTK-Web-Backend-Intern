package com.ghtk.productmanagement.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WarehouseStatus {
    ACTIVE(1),
    INACTIVE(0);

    private final int status;
}
