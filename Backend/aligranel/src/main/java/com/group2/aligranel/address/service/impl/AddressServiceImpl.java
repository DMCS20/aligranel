package com.group2.aligranel.address.service.impl;

import com.group2.aligranel.address.dto.request.AddressRequestDTO;
import com.group2.aligranel.address.dto.response.AddressResponseDTO;
import com.group2.aligranel.address.mapper.AddressMapper;
import com.group2.aligranel.address.model.Address;
import com.group2.aligranel.address.repository.AddressRepository;
import com.group2.aligranel.address.service.AddressService;
import com.group2.aligranel.user.repository.UserRepository;
import com.group2.aligranel.user.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressResponseDTO getAddressById(ObjectId id) {
        Optional<Address> addressOptional = addressRepository.findById(id);

        if(!addressOptional.isPresent()){
            //TODO: NotFoundException
        }

        AddressResponseDTO response = addressMapper.toAddressResponseDTO(addressOptional.get());
        return response;
    }

    @Override
    public List<AddressResponseDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressResponseDTO> response = addressMapper.toAddressResponseDTOList(addresses);
        return response;
    }

    @Override
    public List<AddressResponseDTO> getAddressesByUserId(ObjectId userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        List<AddressResponseDTO> response = addressMapper.toAddressResponseDTOList(addresses);
        return response;
    }

    @Override
    public Address createAddress(AddressRequestDTO addressRequest, ObjectId userId) {
        if(addressRepository.existsByName(addressRequest.getName())){
            //TODO DuplicatedTitleException
        }
        if(!userRepository.existsById(userId)){
            //TODO: NotFoundException
        }
        Address address = addressMapper.toAddress(addressRequest);
        address.setUserId(userId);
        addressRepository.insert(address);
        return address;
    }

    @Override
    public void updateAddress(AddressRequestDTO addressRequest, ObjectId id) {
        Optional<Address> addressToBeUpdated = addressRepository.findById(id);
        if(!addressToBeUpdated.isPresent()){
            //TODO: NotFoundException
        }

        addressMapper.updateAddressFromDTO(addressRequest, addressToBeUpdated.get());
        addressRepository.save(addressToBeUpdated.get());
    }

    @Override
    public void deleteAddress(ObjectId id) {
        Optional<Address> addressToBeDeleted = addressRepository.findById(id);
        if(!addressToBeDeleted.isPresent()){
            //TODO: NotFoundException
        }
        addressRepository.delete(addressToBeDeleted.get());

    }
}
