package com.crm.market.stock.model;

import com.crm.market.stock.model.common.AbstractEntity;
import com.crm.market.stock.model.common.Addresse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Fournisseur extends AbstractEntity {

    private String nom;

    private String prenom;

    @Embedded
    private Addresse addresse;

    private String photo;

    private String mail;

    private String numTel;

    @OneToMany(mappedBy = "fournisseur")
    private List<CommandeFournisseur> commandeFournisseurs;
}
