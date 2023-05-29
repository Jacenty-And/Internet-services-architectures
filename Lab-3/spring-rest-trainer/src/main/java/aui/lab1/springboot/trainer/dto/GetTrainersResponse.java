package aui.lab1.springboot.trainer.dto;

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
public class GetTrainersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Trainer {

        private String name;
        private int age;
//        private List<Pokemon> pokemons;
//        private String pokemons;
    }

    @Singular
    private List<Trainer> trainers;

    public static Function<Collection<aui.lab1.springboot.trainer.entity.Trainer>, GetTrainersResponse> entityToDtoMapper() {
        return trainers -> {
            GetTrainersResponse.GetTrainersResponseBuilder response = GetTrainersResponse.builder();
            trainers.stream()
                    .map(trainer -> Trainer.builder()
                            .name(trainer.getName())
                            .age(trainer.getAge())
//                            .pokemons(trainer.getPokemons().toString())
                            .build())
                    .forEach(response::trainer);
            return response.build();
        };
    }
}
