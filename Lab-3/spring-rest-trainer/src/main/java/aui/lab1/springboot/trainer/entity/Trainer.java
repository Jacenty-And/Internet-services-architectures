package aui.lab1.springboot.trainer.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "TrainerTrainer")
@Table(name = "trainers")
public class Trainer implements Serializable {

    @Id
    private String name;

    private int age;

//    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "trainer")
//    @ToString.Exclude
//    private List<Pokemon> pokemons;
}
