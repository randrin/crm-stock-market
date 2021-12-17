package com.crm.market.stock.dto;

import com.crm.market.stock.model.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class ArticleDto {

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto category;

//    private List<LigneVenteDto> ligneVentes;
//
//    private List<LigneCommandeClientDto> ligneCommandeClients;
//
//    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;
//
//    private List<MvtStkDto> mvtStks;

    private Integer idEntreprise;

    public static ArticleDto fromEntity(Article article) {
        if(article == null) {
            return null;
            // TODO throws on ecxeption
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .category(CategoryDto.fromEntity(article.getCategory()))
//                .ligneVentes(article.getLigneVentes() != null ?
//                        article.getLigneVentes().stream().map(LigneVenteDto::fromEntity).collect(Collectors.toList()) : null)
//                .ligneCommandeClients(article.getLigneCommandeClients() != null ?
//                        article.getLigneCommandeClients().stream().map(LigneCommandeClientDto::fromEntity).collect(Collectors.toList()) : null)
//                .ligneCommandeFournisseurs(article.getLigneCommandeFournisseurs() != null ?
//                        article.getLigneCommandeFournisseurs().stream().map(LigneCommandeFournisseurDto::fromEntity).collect(Collectors.toList()) : null)
//                .mvtStks(article.getMvtStks() != null ?
//                        article.getMvtStks().stream().map(MvtStkDto::fromEntity).collect(Collectors.toList()) : null)
                .idEntreprise(article.getIdEntreprise())
                .build();
    }

    public static Article toEntity(ArticleDto articleDto) {
        if(articleDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setPhoto(articleDto.getPhoto());
        article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));
        /*
 TODO
        article.setLigneVentes(article.getLigneVentes() != null ?
                article.getLigneVentes().stream().map(LigneVenteDto::toEntity).collect(Collectors.toList()) : null);
        article.setLigneCommandeClients(article.getLigneCommandeClients() != null ?
                article.getLigneCommandeClients().stream().map(LigneCommandeClientDto::toEntity).collect(Collectors.toList()) : null);
        article.setLigneCommandeFournisseurs(article.getLigneCommandeFournisseurs() != null ?
                article.getLigneCommandeFournisseurs().stream().map(LigneCommandeFournisseurDto::toEntity).collect(Collectors.toList()) : null);
        article.setMvtStks(article.getMvtStks() != null ?
                article.getMvtStks().stream().map(MvtStkDto::toEntity).collect(Collectors.toList()) : null);
*/
        article.setId(articleDto.getIdEntreprise());

        return article;
    }
}
