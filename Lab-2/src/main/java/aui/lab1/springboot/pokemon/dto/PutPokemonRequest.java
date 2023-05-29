package aui.lab1.springboot.pokemon.dto;

import aui.lab1.springboot.pokemon.entity.Pokemon;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutPokemonRequest {

    private Long id;
    private String name;
    private int level;

    public static BiFunction<Pokemon, PutPokemonRequest, Pokemon> dtoToEntityUpdater() {
        return (pokemon, request) -> {
            pokemon.setId(request.getId());
            pokemon.setName(request.getName());
            pokemon.setLevel(request.getLevel());
            return pokemon;
        };
    }
}
