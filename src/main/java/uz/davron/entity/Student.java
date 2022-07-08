package uz.davron.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)//ONE student TO ONE address*** ONE address TO ONE student
    private Address address;

    @ManyToOne
    private Group group;


    public Student(String firstName, String lastName, Address address, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.group = group;

    }
}
