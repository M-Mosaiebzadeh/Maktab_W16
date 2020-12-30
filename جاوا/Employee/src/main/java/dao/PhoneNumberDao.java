package dao;

import entities.PhoneNumber;

import javax.persistence.EntityManager;

public class PhoneNumberDao extends AbstractDao<Integer, PhoneNumber>{

    public PhoneNumberDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<PhoneNumber> getEntityClass() {
        return PhoneNumber.class;
    }
}
