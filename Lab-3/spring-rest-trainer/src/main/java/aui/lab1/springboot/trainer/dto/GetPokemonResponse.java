package aui.lab1.springboot.trainer.dto;

import aui.lab1.springboot.trainer.entity.Pokemon;
import aui.lab1.springboot.trainer.entity.Trainer;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPokemonResponse {

    private Long id;
    private String name;
    private int level;
    private String trainer;

    public static Function<Pokemon, GetPokemonResponse> entityToDtoMapper() {
        return pokemon -> GetPokemonResponse.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .level(pokemon.getLevel())
                .trainer(pokemon.getTrainer().getName())
                .build();
    }
}
