package aui.lab1.springboot.pokemon.dto;

import aui.lab1.springboot.pokemon.entity.Trainer;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTrainerResponse {

    private String name;
    private int age;
//    private String pokemons;

    public static Function<Trainer, GetTrainerResponse> entityToDtoMapper() {
        return trainer -> GetTrainerResponse.builder()
                .name(trainer.getName())
                .age(trainer.getAge())
                .build();
    }
}
