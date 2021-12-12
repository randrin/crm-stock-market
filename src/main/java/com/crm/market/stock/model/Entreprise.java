package com.crm.market.stock.model;

import com.crm.market.stock.model.common.AbstractEntity;
import com.crm.market.stock.model.common.Addresse;
import lombok.*;

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
public class Entreprise extends AbstractEntity {

    private String nom;

    private String description;

    @Embedded
    private Addresse addresse;

    private String codeFiscale;

    private String photo;

    private String email;

    private String numTel;

    private String siteWeb;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;

    private Integer idEntreprise;
}
