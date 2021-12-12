package com.crm.market.stock.model;

import com.crm.market.stock.model.common.AbstractEntity;
import com.crm.market.stock.model.common.Addresse;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Utilisateur extends AbstractEntity {

    private String nom;

    private String prenom;

    private String email;

    private String dateDeNaissance;

    private String motDePasse;

    @Embedded
    private Addresse addresse;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "utilisateur")
    private List<Roles> roles;

}
