package dao;

import entities.Address;

import javax.persistence.EntityManager;

public class AddressDao extends AbstractDao<Integer, Address> {

    public final PhoneNumberDao phoneNumberDao = new PhoneNumberDao(entityManager);

    public AddressDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Address> getEntityClass() {
        return Address.class;
    }

    @Override
    public void delete(Address entity) {
        if (entity.getPhoneNumbers().size() != 0){
            entity.getPhoneNumbers().forEach(phoneNumber -> phoneNumberDao.delete(phoneNumber));
        }
        super.delete(entity);
    }
}
