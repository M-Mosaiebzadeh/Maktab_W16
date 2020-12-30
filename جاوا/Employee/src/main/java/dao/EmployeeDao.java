package dao;

import entities.Employee;

import javax.persistence.EntityManager;

public class EmployeeDao  extends AbstractDao<Integer, Employee>{
    private final AddressDao addressDao = new AddressDao(entityManager);

    public EmployeeDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    public void delete(Employee entity) {
        if (entity.getAddresses().size() != 0){
            entity.getAddresses().forEach(address -> addressDao.delete(address));
        }
        super.delete(entity);
    }
}
