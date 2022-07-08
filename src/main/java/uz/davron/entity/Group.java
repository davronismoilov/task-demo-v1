package uz.davron.entity;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
@Setter
@Getter
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne//MANY group TO ONE faculty
    private Faculty faculty;

    private int year;


}
