package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "emp_code")
    private String empCode;

    @Column(name = "salary")
    private Double salary;

    @OneToMany(mappedBy = "employee")
    private Set<Address> addresses;

    @OneToOne(mappedBy = "employeee")
    private Address address;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", empCode='" + empCode + '\'' +
                ", salary=" + salary +
                ", addresses=" + addresses +
                '}';
    }
}
