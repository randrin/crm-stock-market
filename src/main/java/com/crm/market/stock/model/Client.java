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
public class Client extends AbstractEntity {

    private String nom;

    private String prenom;

    @Embedded
    private Addresse addresse;

    private String photo;

    private String mail;

    private String numTel;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;
}
