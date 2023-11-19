package com.group2.aligranel.address.controller;

import com.group2.aligranel.address.dto.request.AddressRequestDTO;
import com.group2.aligranel.address.dto.response.AddressResponseDTO;
import com.group2.aligranel.address.model.Address;
import com.group2.aligranel.address.repository.AddressRepository;
import com.group2.aligranel.address.service.AddressService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    @GetMapping("/addresses")
    public ResponseEntity<List<AddressResponseDTO>> getAddresses(){
        return new ResponseEntity<>(addressService.getAllAddresses(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable String id){
        ObjectId oid = new ObjectId(id);
        return new ResponseEntity<>(addressService.getAddressById(oid), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/users/{userId}/addresses")
    public ResponseEntity<List<AddressResponseDTO>> getAddressesByUserId(@PathVariable ObjectId userId){
        return new ResponseEntity<>(addressService.getAddressesByUserId(userId), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/users/{userId}/addresses")
    public ResponseEntity<Address> createAddress(@RequestBody AddressRequestDTO addressRequest, @PathVariable ObjectId userId){
        return new ResponseEntity<>(addressService.createAddress(addressRequest, userId), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/addresses/{id}")
    public ResponseEntity updateAddress(@RequestBody AddressRequestDTO address, @PathVariable ObjectId id){
        addressService.updateAddress(address, id);
        return new ResponseEntity<>("Address updated successfully", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity deleteAddress(@PathVariable ObjectId id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Address with ID" + id + "deleted successfully", HttpStatus.OK);
    }
}
