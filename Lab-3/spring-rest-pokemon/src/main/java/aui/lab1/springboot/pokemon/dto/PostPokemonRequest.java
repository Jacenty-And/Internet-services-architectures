package aui.lab1.springboot.pokemon.dto;

import aui.lab1.springboot.pokemon.entity.Pokemon;
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
public class PostPokemonRequest {

    private Long id;
    private String name;
    private int level;
    private String trainer;

    public static Function<PostPokemonRequest, Pokemon> dtoToEntityMapper(
            Function<String, Trainer> trainerFunction) {
        return request -> Pokemon.builder()
                .id(request.getId())
                .name(request.getName())
                .level(request.getLevel())
                .trainer(trainerFunction.apply(request.getTrainer()))
                .build();
    }
}
