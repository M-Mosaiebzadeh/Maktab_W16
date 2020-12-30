package dao;

import entities.IEntity;
import entities.User;

import javax.persistence.EntityManager;

public class UserDao extends AbstractDao<Integer, User>{

    private final ContractDao contractDao = new ContractDao(entityManager);

    public UserDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public void delete(User entity) {
        entity.getContracts().forEach(contractDao::delete);
        super.delete(entity);
    }
}
