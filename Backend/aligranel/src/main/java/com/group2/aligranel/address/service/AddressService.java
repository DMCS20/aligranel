package com.group2.aligranel.address.service;

import com.group2.aligranel.address.dto.request.AddressRequestDTO;
import com.group2.aligranel.address.dto.response.AddressResponseDTO;
import com.group2.aligranel.address.model.Address;
import org.bson.types.ObjectId;

import java.util.List;

public interface AddressService {
    AddressResponseDTO getAddressById(ObjectId id);
    List<AddressResponseDTO> getAllAddresses();
    List<AddressResponseDTO> getAddressesByUserId(ObjectId userId);
    Address createAddress(AddressRequestDTO addressRequest, ObjectId userId);
    void updateAddress(AddressRequestDTO addressRequest, ObjectId id);
    void deleteAddress(ObjectId id);
}
