package aui.lab1.springboot.trainer.dto;

import aui.lab1.springboot.trainer.entity.Trainer;
import lombok.*;

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
