package com.ghtk.productmanagement.models.entities;

import com.ghtk.productmanagement.constants.WarehouseStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseEntity extends BaseEntity{
    private String name;
    private String address;
    private WarehouseStatus status;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "province_id")
    private ProvinceEntity provinceEntity;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "district_id")
    private DistrictEntity districtEntity;

    @PrePersist
    public void prePersist() {
        this.address = this.address +
                ", " +
                this.districtEntity.getName() +
                ", " +
                this.provinceEntity.getName();
        this.status = WarehouseStatus.ACTIVE;
    }
}
