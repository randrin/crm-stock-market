package com.crm.market.stock.model;

import com.crm.market.stock.model.common.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class LigneVente extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "idVente")
    private Ventes vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaite;
}
