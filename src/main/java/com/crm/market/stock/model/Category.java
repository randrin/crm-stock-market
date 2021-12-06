package com.crm.market.stock.model;

import com.crm.market.stock.model.common.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Category extends AbstractEntity {

    private String code;

    private String designation;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;
}
