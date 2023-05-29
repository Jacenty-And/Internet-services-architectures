package aui.lab1.springboot.trainer.event.dto;

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

    public static Function<Trainer, PostTrainerRequest>  entityToDtoMapper() {
        return entity -> PostTrainerRequest.builder()
                .name(entity.getName())
                .build();
    }
}
