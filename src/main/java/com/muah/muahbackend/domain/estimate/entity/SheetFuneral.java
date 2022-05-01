package com.muah.muahbackend.domain.estimate.entity;

import com.muah.muahbackend.global.entity.Base;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "sheet_funerals")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SheetFuneral extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sheet_funerals_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sheet_id")
    private Sheet sheet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funeral_id")
    private Funeral funeral;

    public SheetFuneral(Sheet sheet, Funeral funeral){
        this.funeral = funeral;
        this.sheet = sheet;
    }
}
