import com.github.javafaker.Faker;
import dao.AddressDao;
import dao.EmployeeDao;
import dao.PhoneNumberDao;
import entities.Address;
import entities.Employee;
import entities.PhoneNumber;
import queries.MyQuery;

import javax.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

import static util.EntityManagerFactoryUtil.createEntityManagerFactory;
import static util.EntityManagerFactoryUtil.shutdown;


public class App {
    public static  EmployeeDao employeeDao;
    public static AddressDao addressDao;
    public static PhoneNumberDao phoneNumberDao;
    public static Faker faker = new Faker();

    public static void main(String[] args) {
        EntityManager entityManager = createEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        daoInitialization(entityManager);

//        //create employee1[ address1[phone1] , address2[phone1]]
//        Employee emp1 = new Employee();
//        emp1.setName("ali");
//        emp1.setEmpCode("12345");
//        emp1.setSalary(1000D);
//        employeeDao.save(emp1);
//
//        Address address1 = new Address();
//        address1.setCity("tehran");
//        address1.setPostalCode("1111");
//        address1.setEmployee(emp1);
//        addressDao.save(address1);
//
//        Address address2 = new Address();
//        address2.setCity("babol");
//        address2.setPostalCode("2222");
//        address2.setEmployee(emp1);
//        addressDao.save(address2);
//
//        PhoneNumber p1 = new PhoneNumber();
//        p1.setMobNumber("0938");
//        p1.setAddress(address1);
//        phoneNumberDao.save(p1);
//
//        PhoneNumber p2 = new PhoneNumber();
//        p2.setMobNumber("0912");
//        p2.setAddress(address2);
//        phoneNumberDao.save(p2);

        //==========================================

//        //create employee2[ address1[phone1]]
//        Employee emp2 = new Employee();
//        emp2.setName("reza");
//        emp2.setEmpCode("54321");
//        emp2.setSalary(2000D);
//        employeeDao.save(emp2);
//
//        Address address3 = new Address();
//        address3.setCity("kerman");
//        address3.setPostalCode("5555");
//        address3.setEmployeee(emp1);
//        address3.setEmployee(emp2);
//        addressDao.save(address3);
//
//        PhoneNumber p3 = new PhoneNumber();
//        p3.setMobNumber("0902");
//        p3.setAddress(address3);
//        phoneNumberDao.save(p3);


        //==========================================

//        MyQuery myQuery = new MyQuery(entityManager);
//        System.out.println(myQuery.mostEmployeeSalaryNative());
//        System.out.println(myQuery.mostEmployeeSalaryJPQL());
//        System.out.println(myQuery.mostSalaryJPQL());
//        System.out.println(myQuery.mostSalaryNative());

        //==================================================

//        // delete phone number if exist
//        System.out.println(employeeDao.load(1));
//        if (phoneNumberDao.load(1) != null){
//            phoneNumberDao.delete(phoneNumberDao.load(1));
//            System.out.println("phone number is deleted");
//        }
//
//        //delete address if exist
//        if (addressDao.load(3) != null){
//            addressDao.delete(addressDao.load(3));
//            System.out.println("address is deleted");
//        }
//
//        //delete employee if exist
//        if (employeeDao.load(1) != null){
//            employeeDao.delete(employeeDao.load(1));
//            System.out.println("employee is deleted");
//        }

        entityManager.getTransaction().commit();
        entityManager.close();
        shutdown();

    }
    public static void daoInitialization(EntityManager entityManager) {
        employeeDao = new EmployeeDao(entityManager);
        addressDao = new AddressDao(entityManager);
        phoneNumberDao = new PhoneNumberDao(entityManager);
    }
    public static Employee createEmployee1() {

        // create new employee[ address1[phone1,phone2], address2[]]
        Employee employee = new Employee();
        employee.setName(faker.name().firstName());
        employee.setEmpCode("1000");
        employee.setSalary(10_000_000D);
        employeeDao.save(employee);

        // add first address to employee
        Address a1 = new Address();
        a1.setPostalCode("98384");
        a1.setPostalAddress("32872365");
        a1.setCity(faker.address().city());
        a1.setEmployee(employee);
        addressDao.save(a1);

        // add second address to employee
        Address a2 = new Address();
        a2.setPostalCode("134854");
        a2.setPostalAddress("34343");
        a2.setCity(faker.address().city());
        a2.setEmployee(employee);
        addressDao.save(a2);

        // add first phone number to first address
        PhoneNumber p1 = new PhoneNumber();
        p1.setTelNumber(faker.phoneNumber().phoneNumber());
        p1.setMobNumber(faker.phoneNumber().cellPhone());
        p1.setAddress(a1);
        phoneNumberDao.save(p1);

        // add second phone number to first address
        PhoneNumber p2 = new PhoneNumber();
        p2.setTelNumber(faker.phoneNumber().phoneNumber());
        p2.setMobNumber(faker.phoneNumber().cellPhone());
        p2.setAddress(a1);
        phoneNumberDao.save(p2);

        return employee;
    }
    public static void createEmployee2(Employee emp) {

        // create new employee[ address1[phone1], address2[phone1]]
        Employee employee = new Employee();
        employee.setName(faker.name().firstName());
        employee.setEmpCode("1001");
        employee.setSalary(9_000_000D);
        employeeDao.save(employee);

        // add first address to employee
        Address a1 = new Address();
        a1.setPostalCode("11123");
        a1.setPostalAddress("541223");
        a1.setCity(faker.address().city());
        a1.setEmployee(employee);
        a1.setEmployeee(emp);
        addressDao.save(a1);

        // add second address to employee
        Address a2 = new Address();
        a2.setPostalCode("15676");
        a2.setPostalAddress("3232");
        a2.setCity(faker.address().city());
        a2.setEmployee(employee);
        a2.setEmployeee(emp);
        addressDao.save(a2);

        // add first phone number to first address
        PhoneNumber p1 = new PhoneNumber();
        p1.setTelNumber(faker.phoneNumber().phoneNumber());
        p1.setMobNumber(faker.phoneNumber().cellPhone());
        p1.setAddress(a1);
        phoneNumberDao.save(p1);

        // add second phone number to second address
        PhoneNumber p2 = new PhoneNumber();
        p2.setTelNumber(faker.phoneNumber().phoneNumber());
        p2.setMobNumber(faker.phoneNumber().cellPhone());
        p2.setAddress(a2);
        phoneNumberDao.save(p2);

    }
}
