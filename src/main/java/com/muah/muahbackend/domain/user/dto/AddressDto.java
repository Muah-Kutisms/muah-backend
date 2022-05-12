package com.muah.muahbackend.domain.user.dto;

import com.muah.muahbackend.domain.user.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    private String city;
    private String county;
    private String district;

    public AddressDto(Address address) {
        this.city = address.getCity();
        this.county = address.getCounty();
        this.district = address.getDistrict();
    }

    public Address convert() {
        return Address.builder()
                .City(city)
                .County(county)
                .District(district)
                .build();
    }
}
