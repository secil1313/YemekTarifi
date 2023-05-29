package com.secil.service;

import com.secil.repository.IAddressRepository;
import com.secil.repository.entity.Address;
import com.secil.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService extends ServiceManager<Address,Long> {
    private final IAddressRepository addressRepository;

    public AddressService(IAddressRepository addressRepository) {
        super(addressRepository);
        this.addressRepository=addressRepository;
    }

}
