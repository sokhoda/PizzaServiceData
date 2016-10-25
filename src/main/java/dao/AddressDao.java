package dao;

import domain.Address;

public interface AddressDao {
    Address find(Long id);

    Long save(Address customer);
}
