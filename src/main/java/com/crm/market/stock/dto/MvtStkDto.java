package com.crm.market.stock.dto;

import com.crm.market.stock.model.MvtStk;
import com.crm.market.stock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class MvtStkDto {

    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto article;

    private TypeMvtStk typeMvtStk;

    public static MvtStkDto fromEntity(MvtStk mvtStk) {
        if(mvtStk == null) {
            return null;
            // TODO throws on ecxeption
        }
        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .article(ArticleDto.fromEntity(mvtStk.getArticle()))
                .typeMvtStk(mvtStk.getTypeMvtStk())
                .build();
    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto) {
        if(mvtStkDto == null) {
            return null;
            // TODO throws on ecxeption
        }
        MvtStk mvtStk = new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticle()));
        mvtStk.setTypeMvtStk(mvtStkDto.getTypeMvtStk());

        return mvtStk;
    }
}
