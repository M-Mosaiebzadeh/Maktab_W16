package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "address")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "postal_address")
    private String postalAddress;

    @Column(name = "city")
    private String city;

    @OneToOne()
    @JoinColumn(name = "employee" )
    private Employee employeee;

    @ManyToOne()
    @JoinColumn(name = "fk_employee")
    private Employee employee;

    @OneToMany(mappedBy = "address")
    private Set<PhoneNumber> phoneNumbers;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
