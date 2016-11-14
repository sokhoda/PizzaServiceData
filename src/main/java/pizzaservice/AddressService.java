package pizzaservice;

import domain.Address;

import java.util.List;

public interface AddressService {
    Address find(Long id);

    List<Address> findByCity(String city);

    Address save(Address address);
}