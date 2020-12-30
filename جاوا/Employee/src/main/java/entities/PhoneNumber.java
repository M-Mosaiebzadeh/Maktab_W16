package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phone_number")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_number_id")
    private Integer id;

    @Column(name = "tel_name")
    private String telNumber;

    @Column(name = "mob_name")
    private String mobNumber;

    @ManyToOne()
    @JoinColumn(name = "fk_address")
    private Address address;

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "telNumber='" + telNumber + '\'' +
                ", mobNumber='" + mobNumber + '\'' +
                '}';
    }
}
