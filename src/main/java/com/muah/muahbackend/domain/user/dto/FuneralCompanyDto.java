package com.muah.muahbackend.domain.user.dto;


import com.muah.muahbackend.domain.estimate.dto.ProposalDto;
import com.muah.muahbackend.domain.user.entity.Address;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.vo.Image;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class FuneralCompanyDto {

    private Long id;
    private String funeralName;
    private Address address;
    private Image image;

    public FuneralCompanyDto(User user){
        this.id = user.getId();
        this.funeralName = user.getFuneralName();
        this.address = user.getAddress();
        this.image = user.getImage();
    }
}
