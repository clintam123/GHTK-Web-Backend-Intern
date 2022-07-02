package com.ghtk.productmanagement.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ghtk.productmanagement.constants.CategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private CategoryStatus status;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

//    @OneToMany(mappedBy = "categoryEntity",
//            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JsonBackReference
//    private List<ProductEntity> productEntityList;

}
