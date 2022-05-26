package com.muah.muahbackend.domain.user.dto;

import com.muah.muahbackend.domain.estimate.dto.ProposalDto;
import com.muah.muahbackend.domain.user.entity.Address;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.vo.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FuneralUserDto extends UserDto{


    private String funeralName;
    private Address address;
    private Image image;
    private List<ProposalDto> reservedProposals;
    private List<ProposalDto> completedProposals;

    public FuneralUserDto(User user){
        this.id = user.getId();
        this.role = user.getRole();
        this.funeralName = user.getFuneralName();
        this.address = user.getAddress();
        this.image = user.getImage();
    }
}
