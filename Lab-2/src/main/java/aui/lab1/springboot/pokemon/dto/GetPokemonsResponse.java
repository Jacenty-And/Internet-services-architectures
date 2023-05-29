package aui.lab1.springboot.pokemon.dto;

import aui.lab1.springboot.pokemon.entity.Pokemon;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPokemonsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Pokemon {

        private Long id;
        private String name;
        private int level;
        private String trainer;
    }

    @Singular
    private List<Pokemon> pokemons;

    public static Function<Collection<aui.lab1.springboot.pokemon.entity.Pokemon>, GetPokemonsResponse> entityToDtoMapper() {
        return pokemons -> {
            GetPokemonsResponseBuilder response = GetPokemonsResponse.builder();
            pokemons.stream()
                    .map(pokemon -> Pokemon.builder()
                            .id(pokemon.getId())
                            .name(pokemon.getName())
                            .level(pokemon.getLevel())
                            .trainer(pokemon.getTrainer().getName())
                            .build())
                    .forEach(response::pokemon);
            return response.build();
        };
    }
}
