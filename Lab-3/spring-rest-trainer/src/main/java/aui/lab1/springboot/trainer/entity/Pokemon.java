package aui.lab1.springboot.trainer.entity;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "pokemons")
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    private int level;

    @ManyToOne
    @JoinColumn(name = "trainers")
    private Trainer trainer;

}
