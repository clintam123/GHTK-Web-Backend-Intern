package com.ghtk.productmanagement.models.entities;

import com.ghtk.productmanagement.constants.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends BaseEntity{
    private String name;
    private Float price;
    private ProductStatus status;
    private String sku;
    private String description;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @PrePersist
    public void prePersist() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date =  LocalDateTime.now().format(formatter);
        this.sku = this.category.getCode() + ".sku." + date;
        this.status = ProductStatus.valueOf("ACTIVE");
    }

}
