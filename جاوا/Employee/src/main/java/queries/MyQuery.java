package queries;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class MyQuery {

    private final EntityManager entityManager;

    private final String NATIVE_QUERY_MOST_SALARY_EMPLOYEE =
            "select * from employee where salary = (select max(salary) from employee)";

    private final String JPQL_QUERY_MOST_SALARY_EMPLOYEE =
            "select emp from Employee emp where emp.salary = (select max(emp.salary) from Employee emp)";

    private final String NATIVE_QUERY_MOST_SALARY =
            "select max(salary) from employee";

    private final String JPQL_QUERY_MOST_SALARY =
            "select max(salary) from Employee";


    public MyQuery(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //native query for employee with most salary
    public Employee mostEmployeeSalaryNative() {
        List list = entityManager.createNativeQuery(NATIVE_QUERY_MOST_SALARY_EMPLOYEE, Employee.class)
                .getResultList();

        if (list.isEmpty())
            return null;
        return (Employee) list.get(0);
    }

    //JPQL query for employee with most salary
    public Employee mostEmployeeSalaryJPQL() {
        TypedQuery<Employee> employeeTypedQuery = entityManager.createQuery(JPQL_QUERY_MOST_SALARY_EMPLOYEE, Employee.class);
        return employeeTypedQuery.getSingleResult();
    }

    //native query for employee with most salary
    public Double mostSalaryNative() {
        List list = entityManager.createNativeQuery(NATIVE_QUERY_MOST_SALARY)
                .getResultList();
        if (list.isEmpty())
            return null;
        return (Double) list.get(0);
    }

    //JPQL query for employee with most salary
    public Double mostSalaryJPQL() {
        TypedQuery<Double> doubleTypedQuery = entityManager.createQuery(JPQL_QUERY_MOST_SALARY,Double.class);
        return doubleTypedQuery.getSingleResult();
    }


}
