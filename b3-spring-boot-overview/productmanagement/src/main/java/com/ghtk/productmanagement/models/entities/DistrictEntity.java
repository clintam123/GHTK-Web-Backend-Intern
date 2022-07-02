package com.ghtk.productmanagement.models.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "district")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictEntity extends BaseEntity{
    private String code;
    private String name;

}
