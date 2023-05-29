package aui.lab1.springboot.pokemon.dto;

import aui.lab1.springboot.pokemon.entity.Pokemon;
import aui.lab1.springboot.pokemon.entity.Trainer;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostTrainerRequest {

    private String name;
    private int age;

    public static Function<PostTrainerRequest, Trainer> dtoToEntityMapper() {
        return request -> Trainer.builder()
                .name(request.getName())
                .age(request.getAge())
                .build();
    }

}
