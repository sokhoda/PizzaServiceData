package pizzaservice;

import domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AddressRepository;
import java.util.List;

@Service("addressService")
public class SimpleAddressService implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address find(Long id) {
        return addressRepository.findOne(id);
    }

    @Override
    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    @Override
    @Transactional
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
