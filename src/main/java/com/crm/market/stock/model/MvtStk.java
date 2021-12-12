package com.crm.market.stock.model;

import com.crm.market.stock.model.common.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class MvtStk extends AbstractEntity {

    private Instant dateMvt;

    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

    private TypeMvtStk typeMvtStk;

    private Integer idEntreprise;
}
