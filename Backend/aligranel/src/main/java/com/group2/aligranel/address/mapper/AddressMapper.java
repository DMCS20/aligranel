package com.group2.aligranel.address.mapper;

import com.group2.aligranel.address.dto.request.AddressRequestDTO;
import com.group2.aligranel.address.dto.response.AddressResponseDTO;
import com.group2.aligranel.address.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressRequestDTO addressRequestDTO);
    AddressResponseDTO toAddressResponseDTO(Address address);
    List<Address> toAddressList(List<AddressRequestDTO> addressRequestDTOList);
    List<AddressResponseDTO> toAddressResponseDTOList(List<Address> addresses);
    void updateAddressFromDTO(AddressRequestDTO addressRequestDTO, @MappingTarget Address address);
}
